package itcube.consulting.monitoraggioClient;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itcube.consulting.monitoraggioClient.entities.Alert;
import itcube.consulting.monitoraggioClient.entities.AlertConfigurazione;
import itcube.consulting.monitoraggioClient.entities.ElencoClients;
import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;
import itcube.consulting.monitoraggioClient.entities.database.InfoOperazioneMail;
import itcube.consulting.monitoraggioClient.entities.database.InfoOperazioneMailDrives;
import itcube.consulting.monitoraggioClient.entities.database.ShallowClient;
import itcube.consulting.monitoraggioClient.entities.database.ValidToken;
import itcube.consulting.monitoraggioClient.repositories.AlertConfigurazioneRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoAlertRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoClientsRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoCompaniesRepository;
import itcube.consulting.monitoraggioClient.response.AlertConfigurazioneResponse;
import itcube.consulting.monitoraggioClient.response.AlertResponse;
import itcube.consulting.monitoraggioClient.response.GeneralResponse;
import itcube.consulting.monitoraggioClient.response.NumAlertResponse;
import itcube.consulting.monitoraggioClient.response.ShallowClientsResponse;
import itcube.consulting.monitoraggioClient.services.EmailService;
import itcube.consulting.monitoraggioClient.services.Services;

@RestController
@RequestMapping(path="/be/main")
public class AlertController {
	
	@Autowired
	ElencoClientsRepository elencoClientsRepository;

	@Autowired
	ElencoCompaniesRepository elencoCompaniesRepository;
	
	@Autowired
	ElencoAlertRepository elencoAlertRepository;
	
	@Autowired
	AlertConfigurazioneRepository alertConfigurazioneRepository;
	
	public static int inserimentoAlertDischi(int id_client, double perc_free_space, String driveLabel, ElencoClientsRepository elencoClientsRepository, ElencoAlertRepository elencoAlertRepository,AlertConfigurazioneRepository alertConfigurazioneRepository, ElencoCompaniesRepository elencoCompaniesRepository)
	{
		int id_company;
		String disc_state = null;
		String tmp_drive_label = new String(driveLabel);
		
        if(tmp_drive_label.contains("\\")){
            tmp_drive_label= tmp_drive_label.replace("\\", "");
        }
		
		try
		{
			id_company=elencoClientsRepository.getIdCompany(id_client);
			
			if(perc_free_space <= 10)
				disc_state = "ERROR";
			if(perc_free_space > 10 && perc_free_space <= 20)
				disc_state = "WARNING";
			if(perc_free_space > 20)
				disc_state = "OK";
				
			
			Alert recentAlert = elencoAlertRepository.getRecentAlert(id_client, tmp_drive_label);
			
			if(recentAlert == null || !recentAlert.getTipo().equals(disc_state)) {
				if(alertConfigurazioneRepository.isOperazioneMonitorata(id_client, "DRIVES")) {
					Alert newAlert = new Alert();
					
	//					newAlert.setNome_disco(driveLabel);
	//					newAlert.setPerc_free_disc(perc_free_space);
					newAlert.setCorpo_messaggio("Il disco " + driveLabel + " ha lo spazio disponibile pari al " + perc_free_space + "%");
					newAlert.setDate_and_time_alert(java.time.LocalDateTime.now());
	//				newAlert.setDate_and_time_mail(java.time.LocalDateTime.now());
					newAlert.setId_client(id_client);
					newAlert.setId_company(id_company);
					newAlert.setTipo(disc_state);
					newAlert.setCategoria(1);
					
					Alert insertedAlert = elencoAlertRepository.save(newAlert);
					
					ElencoCompanies company = elencoCompaniesRepository.getInfoCompany(id_company);
					ElencoClients client = elencoClientsRepository.getClientFromId(id_client);
					EmailService service = new EmailService();
					String tipo_alert;
					if(perc_free_space <= 10)
						tipo_alert = "ERROR";
					else if(perc_free_space > 10 && perc_free_space <= 20)
						tipo_alert = "WARNING";
					else 
						tipo_alert = "OK";
					
					InfoOperazioneMailDrives info = new InfoOperazioneMailDrives("DRIVES", insertedAlert.getDate_and_time_alert().toString(),driveLabel,String.valueOf(perc_free_space)+"%",tipo_alert,client.getNome(),company.getRagione_sociale());
					
					EmailService.sendEmail("Alert drive", service.getEmailContent(company, info) , company.getEmail_alert());
					elencoAlertRepository.updateMailTimestamp(insertedAlert.getId());
				}
			}

			return 0;
			
		}
		catch (Exception e)
		{
			System.out.println(e);
			return -1;
		}
	}
	
	
	@PostMapping(path="/inserimentoAlertDischi",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> inserimentoAlertDischi1 (@RequestBody Map<String,Object> body) {
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		int id_client;
		int id_company;
		String driveLabel;
		int occupiedSpace;
		int totalSpace;
		float perc_free_space;
		String token;
		String disc_state = null;
		
		try
		{
			id_client=Integer.parseInt((String)body.get("id_client"));
			id_company=elencoClientsRepository.getIdCompany(id_client);
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				driveLabel = (String) body.get("driveLabel");
				occupiedSpace = Integer.parseInt( (String) body.get("occupiedSpace"));
				totalSpace = Integer.parseInt( (String) body.get("totalSpace"));
				perc_free_space = (float) (totalSpace - occupiedSpace) / totalSpace * 100;
				
				DecimalFormat df = new DecimalFormat();
				df.setMaximumFractionDigits(2);
				String free_space_string =  df.format(perc_free_space).replace(",", ".");
				perc_free_space = Float.parseFloat( free_space_string );
				
				
				if(perc_free_space < 10)
					disc_state = "ERROR";
				if(perc_free_space >= 10 && perc_free_space <= 20)
					disc_state = "WARNING";
				if(perc_free_space > 20)
					disc_state = "OK";
					
				
				Alert recentAlert = elencoAlertRepository.getRecentAlert(id_client, driveLabel);
				
				if(recentAlert == null || !recentAlert.getTipo().equals(disc_state)) {
					Alert newAlert = new Alert();
					
//					newAlert.setNome_disco(driveLabel);
//					newAlert.setPerc_free_disc(perc_free_space);
					newAlert.setCorpo_messaggio("Il disco " + driveLabel + " ha il " + perc_free_space + "% di spazio libero");
					newAlert.setDate_and_time_alert(java.time.LocalDateTime.now());
					newAlert.setDate_and_time_mail(java.time.LocalDateTime.now());
					newAlert.setId_client(id_client);
					newAlert.setId_company(id_company);
					newAlert.setTipo(disc_state);
					
					elencoAlertRepository.save(newAlert);
					
				}
				
				generalResponse.setMessage("Operazione effettuata con successo");
				generalResponse.setMessageCode(0);
				generalResponse.setToken(validToken.getToken());
				
				return ResponseEntity.ok(generalResponse); 
				
			} else {
				generalResponse.setMessage("Autenticazione fallita");
				generalResponse.setMessageCode(-2);
				return ResponseEntity.ok(generalResponse);
			}
			
		}
		catch (Exception e)
		{
			generalResponse.setMessage(e.getMessage());
			generalResponse.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(generalResponse);
		}
	}
	
	
	@PostMapping(path="/getLatestAlerts",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> getLatestAlerts(@RequestBody Map<String,Object> body) {
		AlertResponse response = new AlertResponse();
		ValidToken validToken=new ValidToken();
		int id_client;
		int id_company;
		String token;
		int n_settimane;
		boolean alert_servizi, alert_eventi, alert_drives;
		
		try
		{
			id_client=Integer.parseInt((String)body.get("id_client"));
			id_company=elencoClientsRepository.getIdCompany(id_client);
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				n_settimane = Integer.parseInt((String)body.get("n_settimane"));
				List<Alert> latestAlerts = elencoAlertRepository.getLatestAlerts(id_client,n_settimane);
				
				List<AlertConfigurazione> config = alertConfigurazioneRepository.getAlertConfigurazione(id_client);
				
				config.forEach(configurazione -> {
					latestAlerts.removeIf(alert -> (configurazione.getCategoria() == alert.getCategoria()) && !configurazione.isMonitora());
				});
				
				response.setAlerts(latestAlerts);
				response.setMessage("Operazione effettuata con successo");
				response.setMessageCode(0);
				response.setToken(validToken.getToken());
				
				return ResponseEntity.ok(response); 
				
			} else {
				response.setMessage("Autenticazione fallita");
				response.setMessageCode(-2);
				return ResponseEntity.ok(response);
			}
			
		}
		catch (Exception e)
		{
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	
	@PostMapping(path="/getMonitoraggioAlert",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> getMonitoraggioAlert(@RequestBody Map<String,Object> body) {
		AlertConfigurazioneResponse response = new AlertConfigurazioneResponse();
		ValidToken validToken=new ValidToken();
		int id_client;
		int id_company;
		String token;
		
		try
		{
			id_client=Integer.parseInt((String)body.get("id_client"));
			id_company=elencoClientsRepository.getIdCompany(id_client);
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				List<AlertConfigurazione> lista_conf = alertConfigurazioneRepository.getAlertConfigurazione(id_client);
				
				response.setListaOperazioni(lista_conf);
				response.setMessage("OK");
				response.setMessageCode(0);
				response.setToken(null);
				
				return ResponseEntity.ok(response); 
				
			} else {
				response.setMessage("Autenticazione fallita");
				response.setMessageCode(-2);
				return ResponseEntity.ok(response);
			}
			
		}
		catch (Exception e)
		{
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping(path="/updateMonitoraggioAlert",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> updateMonitoraggioAlert(@RequestBody Map<String,Object> body) {
		GeneralResponse response = new GeneralResponse();
		ValidToken validToken=new ValidToken();
		int id_client;
		int id_company;
		String token;
		boolean monitora;
		String operazione;
		
		try
		{
			id_client=Integer.parseInt((String)body.get("id_client"));
			id_company=elencoClientsRepository.getIdCompany(id_client);
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			monitora=(Boolean)body.get("monitora");
			operazione=(String)body.get("operazione");
			
			if(validToken.isValid())
			{
				alertConfigurazioneRepository.updateAlertConfigurazione(monitora,id_client,operazione);

				response.setMessage("OK");
				response.setMessageCode(0);
				response.setToken(null);
				
				return ResponseEntity.ok(response); 
				
			} else {
				response.setMessage("Autenticazione fallita");
				response.setMessageCode(-2);
				return ResponseEntity.ok(response);
			}
			
		}
		catch (Exception e)
		{
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	/*@PostMapping(path="/getNumAlert",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> getNumAlert(@RequestBody Map<String,Object> body) {
		NumAlertResponse response=new NumAlertResponse();
		ValidToken validToken=new ValidToken();
		Integer id_client;
		Integer id_company;
		String token;
		Integer n_giorni;
		int error;
		int warning;
		
		try
		{
			id_client=Integer.parseInt((String)body.get("id_client"));
			id_company=elencoClientsRepository.getIdCompany(id_client);
			token=(String)body.get("token");
			n_giorni=Integer.parseInt((String)body.get("n_giorni"));
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				if(n_giorni==null)
					n_giorni=1;
					
				if(id_client==-1)
				{
					error=elencoAlertRepository.countErrorCompany(id_company, n_giorni);
					warning=elencoAlertRepository.countWarningCompany(id_company, n_giorni);
				}
				else
				{
					error=elencoAlertRepository.countErrorClient(id_client, n_giorni);
					warning=elencoAlertRepository.countWarningClient(id_client, n_giorni);
				}
				
				response.setError(error);
				response.setWarning(warning);
				response.setMessage("Operazione effettuata con successo");
				response.setMessageCode(0);
				response.setToken(validToken.getToken());
				
				return ResponseEntity.ok(response); 
				
			} else {
				response.setMessage("Autenticazione fallita");
				response.setMessageCode(-2);
				return ResponseEntity.badRequest().body(response);
			}
			
		}
		catch (Exception e)
		{
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}*/
}
