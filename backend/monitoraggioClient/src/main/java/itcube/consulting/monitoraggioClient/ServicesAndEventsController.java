package itcube.consulting.monitoraggioClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itcube.consulting.monitoraggioClient.entities.Alert;
import itcube.consulting.monitoraggioClient.entities.ConfTotalFreeDiscSpace;
import itcube.consulting.monitoraggioClient.entities.ConfWindowsServices;
import itcube.consulting.monitoraggioClient.entities.ElencoClients;
import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;
import itcube.consulting.monitoraggioClient.entities.Monitoraggio;
import itcube.consulting.monitoraggioClient.entities.VisualizzazioneEventi;
import itcube.consulting.monitoraggioClient.entities.database.InfoOperazioneMail;
import itcube.consulting.monitoraggioClient.entities.database.InfoOperazioneMailEvents;
import itcube.consulting.monitoraggioClient.entities.database.InfoOperazioneMailServices;
import itcube.consulting.monitoraggioClient.entities.database.ValidToken;
import itcube.consulting.monitoraggioClient.repositories.AlertConfigurazioneRepository;
import itcube.consulting.monitoraggioClient.repositories.ConfTotalFreeDiscSpaceRepository;
import itcube.consulting.monitoraggioClient.repositories.ConfWindowsServicesRepository;
import itcube.consulting.monitoraggioClient.repositories.ConfigRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoAlertRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoClientsRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoCompaniesRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoLicenzeRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoOperazioniRepository;
import itcube.consulting.monitoraggioClient.repositories.MonitoraggioRepository;
import itcube.consulting.monitoraggioClient.repositories.RealTimeRepository;
import itcube.consulting.monitoraggioClient.repositories.TipologieClientRepository;
import itcube.consulting.monitoraggioClient.repositories.TipologieLicenzeRepository;
import itcube.consulting.monitoraggioClient.repositories.VisualizzazioneEventiRepository;
import itcube.consulting.monitoraggioClient.response.AgentResponse;
import itcube.consulting.monitoraggioClient.response.ClientHistoryResponse;
import itcube.consulting.monitoraggioClient.response.ClientHistoryResponseSub;
import itcube.consulting.monitoraggioClient.response.ClientOverviewResponse;
import itcube.consulting.monitoraggioClient.response.ClientOverviewResponseSub;
import itcube.consulting.monitoraggioClient.response.CompanyOverviewResponse;
import itcube.consulting.monitoraggioClient.response.EventiOverviewResponse;
import itcube.consulting.monitoraggioClient.response.EventiResponse;
import itcube.consulting.monitoraggioClient.response.GeneralResponse;
import itcube.consulting.monitoraggioClient.response.GetClientNameOfCompanyResponse;
import itcube.consulting.monitoraggioClient.response.MaxDateResponse;
import itcube.consulting.monitoraggioClient.response.OperazioniOverviewResponse;
import itcube.consulting.monitoraggioClient.response.ServiziMonitoratiResponse;
import itcube.consulting.monitoraggioClient.response.ServiziOverviewResponse;
import itcube.consulting.monitoraggioClient.response.ServiziResponse;
import itcube.consulting.monitoraggioClient.services.EmailService;
import itcube.consulting.monitoraggioClient.services.Services;

@RestController
@RequestMapping(path="/be/main")
public class ServicesAndEventsController {
	
	@Autowired
	private ConfigRepository configRepository;
	
	@Autowired
	private ConfTotalFreeDiscSpaceRepository confTotalFreeDiscSpaceRepository;
	
	@Autowired
	private ConfWindowsServicesRepository confWindowsServicesRepository;
	
	@Autowired
	private ElencoClientsRepository elencoClientsRepository;
	
	@Autowired
	private ElencoCompaniesRepository elencoCompaniesRepository;
	
	@Autowired
	private ElencoLicenzeRepository elencoLicenzeRepository;
	
	@Autowired
	private ElencoOperazioniRepository elencoOperazioniRepository;
	
	@Autowired
	private RealTimeRepository realTimeRepository;
	
	@Autowired
	private TipologieLicenzeRepository tipologieLicenzeRepository;
	
	@Autowired
	private TipologieClientRepository tipologieClientRepository;
	
	@Autowired
	private MonitoraggioRepository monitoraggioRepository;
	
	@Autowired
	private VisualizzazioneEventiRepository visualizzazioneEventiRepository;
	
	@Autowired
	private ElencoAlertRepository elencoAlertRepository;
	
	@Autowired
	private AlertConfigurazioneRepository alertConfigurazioneRepository;
	
	@PostMapping(path="/inserimentoServizi",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<AgentResponse> inserimentoServizi (@RequestBody Map<String,Object> body)
	{
		AgentResponse generalResponse=new AgentResponse();
		int id_client;
		List<ConfWindowsServices> servizi=new ArrayList<ConfWindowsServices>();
		List<Monitoraggio> monitoraggio=new ArrayList<Monitoraggio>();
		ConfWindowsServices confWindowsServices=new ConfWindowsServices();
		LocalDateTime timestamp=java.time.LocalDateTime.now();
		List<ConfTotalFreeDiscSpace> disco=new ArrayList<ConfTotalFreeDiscSpace>();
		boolean serviziNotNull;
		boolean dischiNotNull;
		String nome_servizio=null;
		String alert=null;
		int stato;
		boolean monitora=false;
		Integer idMonitoraggio;
		String tipo=null;
		
		try
		{
			id_client=Integer.parseInt((String)body.get("MyID"));
			servizi=(List<ConfWindowsServices>)body.get("MyServices");
			disco=(List<ConfTotalFreeDiscSpace>)body.get("MyDrives");
			
			//Check if license type 1 is valid
			
			if(servizi.size()!=0)
				serviziNotNull=true;
			else
				serviziNotNull=false;
			
			if(disco.size()!=0)
				dischiNotNull=true;
			else
				dischiNotNull=false;
			
			if(serviziNotNull)
			{
				for(int i=0; i < servizi.size(); i++)
				{
					ConfWindowsServices tmp = new ConfWindowsServices();
					tmp.setId_client(id_client);
					tmp.setNome_servizio((String) ((Map<String, Object>) servizi.get(i)).get("ServiceName"));
					tmp.setDisplay_name((String) ((Map<String, Object>) servizi.get(i)).get("DisplayName"));
					tmp.setStato(Integer.parseInt((String) ((Map<String, Object>) servizi.get(i)).get("Status")));
					tmp.setStart_type(Integer.parseInt((String) ((Map<String, Object>) servizi.get(i)).get("StartType")));
					tmp.setService_type((String) ((Map<String, Object>) servizi.get(i)).get("ServiceType"));
					tmp.setDate_and_time(timestamp);
//					confWindowsServicesRepository.save(tmp);
					
					
					if(confWindowsServicesRepository.isPresent(tmp.getNome_servizio(), id_client)==null)
					{
						confWindowsServicesRepository.save(tmp);
					}
					else
					{
						confWindowsServicesRepository.updateService(id_client, tmp.getStato(), timestamp, tmp.getNome_servizio());
					}
				}
				monitoraggio=monitoraggioRepository.getServiziClient(id_client);

				if(monitoraggio.size()!=0)
				{
					
					
					for(int i=0; i < servizi.size(); i++)
					{
						Monitoraggio temp=new Monitoraggio();
						temp.setId_client(id_client);
						temp.setNome_servizio((String) ((Map<String, Object>) servizi.get(i)).get("ServiceName"));
						temp.setMonitora(true);
						
						idMonitoraggio=monitoraggioRepository.containsServizio((String) ((Map<String, Object>) servizi.get(i)).get("ServiceName"), id_client);

						if(idMonitoraggio==null)
						{
							monitoraggioRepository.save(temp);
						}
					}
				}
				else
				{
					for(int i=0; i < servizi.size(); i++)
					{
						Monitoraggio temp=new Monitoraggio();
						temp.setId_client(id_client);
						temp.setNome_servizio((String) ((Map<String, Object>) servizi.get(i)).get("ServiceName"));
						temp.setMonitora(true);
				
						monitoraggioRepository.save(temp);
					}
				}
				
				
				for(int i=0; i < servizi.size(); i++)
				{
					nome_servizio=(String) ((Map<String, Object>) servizi.get(i)).get("ServiceName");
					stato = Integer.parseInt((String) ((Map<String, Object>) servizi.get(i)).get("Status"));
					
					if(stato == 1)
						alert = "ERROR";
					if(stato == 4)
						alert = "OK";
					if(stato == 2 || stato == 3 || stato == 5 || stato == 6 || stato == 7 )
						alert = "WARNING";
					
					if(elencoAlertRepository.lastAlertStatus(id_client, nome_servizio) == null || (monitoraggioRepository.getMonitora(id_client, nome_servizio) && !elencoAlertRepository.lastAlertStatus(id_client, nome_servizio).equals(alert))) {
						if(alertConfigurazioneRepository.isOperazioneMonitorata(id_client, "WINDOWS_SERVICES")) {
							Alert newAlert=new Alert();		
							newAlert.setDate_and_time_alert(timestamp);
							newAlert.setId_client(id_client);
							newAlert.setId_company(elencoClientsRepository.getIdCompany(id_client));
							newAlert.setCategoria(2);
							newAlert.setTipo(alert);
							if(stato == 1)
								newAlert.setCorpo_messaggio("Il servizio "+nome_servizio+" presenta un errore");
							else if(stato == 4)
								newAlert.setCorpo_messaggio("Il servizio "+nome_servizio+" funziona correttamente");
							else
								newAlert.setCorpo_messaggio("Il servizio "+nome_servizio+" è in stato di warning");
							
							Alert insertedAlert = elencoAlertRepository.save(newAlert);
							
							int id_company = elencoClientsRepository.getIdCompany(id_client);
							ElencoCompanies company = elencoCompaniesRepository.getInfoCompany(id_company);
							ElencoClients client = elencoClientsRepository.getClientFromId(id_client);
							EmailService service = new EmailService();
							
							String token_mail = Services.addTokenToAuthenticationMail(company.getRagione_sociale(),id_client);
							
							InfoOperazioneMailServices info = new InfoOperazioneMailServices("WINDOWS_SERVICES",insertedAlert.getDate_and_time_alert().toString(),client.getNome(),company.getRagione_sociale(),nome_servizio, insertedAlert.getTipo(), token_mail, id_client, Services.address);

							EmailService.sendEmail("Alert windows service", service.getEmailContent(company, info) , company.getEmail_alert());
							elencoAlertRepository.updateMailTimestamp(insertedAlert.getId());
						
						}
					}
				}
			}
			
			if(dischiNotNull)
			{
				Long total_size_long;
				Long total_free_disc_space_long;
				//VolumeName, VolumeLabel, TotalSize, TotalFreeSpace
				for(int i=0; i < disco.size(); i++)
				{
					ConfTotalFreeDiscSpace tmp = new ConfTotalFreeDiscSpace();
					
					String drive=(String) ((Map<String, Object>) disco.get(i)).get("VolumeName");
					String descrizione=(String) ((Map<String, Object>) disco.get(i)).get("VolumeLabel");
					String total_size=(String) ((Map<String, Object>) disco.get(i)).get("TotalSize");
					String total_free_disc_space=(String) ((Map<String, Object>) disco.get(i)).get("TotalFreeSpace");

					tmp.setId_client(id_client);
					tmp.setDrive(drive);
					tmp.setDescrizione(descrizione);
					tmp.setTotal_size(total_size);
					tmp.setTotal_free_disc_space(total_free_disc_space);
					
					try {
						total_size_long = Long.parseLong( total_size );
						total_free_disc_space_long = Long.parseLong( total_free_disc_space );
					} catch (Exception e) {
						generalResponse.setMessage(e.getMessage());
						generalResponse.setMessageCode(-1);
						System.out.println(e.getMessage());	
						return ResponseEntity.badRequest().body(generalResponse);
					}
					
					
					double perc_free_disc_space=tmp.setPerc_free_disc_space(total_size_long,total_free_disc_space_long);
					tmp.setDate_and_time(timestamp);
	
					if(confTotalFreeDiscSpaceRepository.isPresent(drive, id_client)==null)
					{
						confTotalFreeDiscSpaceRepository.save(tmp);
					}
					else
					{
						confTotalFreeDiscSpaceRepository.updateDisk(drive, id_client, total_size, total_free_disc_space, perc_free_disc_space, timestamp);
					}
					
					AlertController.inserimentoAlertDischi(id_client, perc_free_disc_space, drive, elencoClientsRepository, elencoAlertRepository, alertConfigurazioneRepository, elencoCompaniesRepository);
				}
			}

			if(dischiNotNull || serviziNotNull)
			{
				generalResponse.setMessage("Operazione effettuata con successo");
				generalResponse.setMessageCode(0);
			
				return ResponseEntity.ok(generalResponse); 
			}
			else
			{
				generalResponse.setMessage("Liste dei servizi vuote");
				generalResponse.setMessageCode(-2);
			
				return ResponseEntity.ok(generalResponse); 
			}
		}
		catch(Exception e)
		{
			generalResponse.setMessage(e.getMessage());
			generalResponse.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(generalResponse);
		}
	}
	

	@PostMapping(path="/inserimentoEventi",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<AgentResponse> inserimentoEventi (@RequestBody Map<String,Object> body)
	{
		AgentResponse generalResponse=new AgentResponse();
		int id_client;
		List<VisualizzazioneEventi> MyApplicationsLogs=new ArrayList<VisualizzazioneEventi>();
		List<VisualizzazioneEventi> MyHardwareLogs=new ArrayList<VisualizzazioneEventi>();
		List<VisualizzazioneEventi> MySystemLogs=new ArrayList<VisualizzazioneEventi>();
		List<VisualizzazioneEventi> MySecurityLogs=new ArrayList<VisualizzazioneEventi>();
		LocalDateTime timestamp=java.time.LocalDateTime.now();
		String alert = null;
		String alertEmail = null;
		int level;
		String nome = null;
		Alert newAlert = null;
		
		try
		{
			id_client=Integer.parseInt((String) body.get("MyID"));
			MyApplicationsLogs=(List<VisualizzazioneEventi>)body.get("MyApplicationLogs");
			MyHardwareLogs=(List<VisualizzazioneEventi>)body.get("MyHardwareLogs");
			MySystemLogs=(List<VisualizzazioneEventi>)body.get("MySystemLogs");
			MySecurityLogs=(List<VisualizzazioneEventi>)body.get("MySecurityLogs");
			
			if(MyApplicationsLogs!=null)
			{
				for(int i=0; i < MyApplicationsLogs.size(); i++)
				{
					
					//sottocategoria, level, source, id_event, task_category, info, date_and_time
					VisualizzazioneEventi tmp = new VisualizzazioneEventi();
					tmp.setId_client(id_client);
					tmp.setSottocategoria("A");
					tmp.setLevel(Integer.parseInt((String) ((Map<String, Object>) MyApplicationsLogs.get(i)).get("EntryType")));
					tmp.setSource((String) ((Map<String, Object>) MyApplicationsLogs.get(i)).get("LogOrigin"));
					tmp.setId_event(Integer.parseInt((String) ((Map<String, Object>) MyApplicationsLogs.get(i)).get("LogEventID")));
					tmp.setInfo((String) ((Map<String, Object>) MyApplicationsLogs.get(i)).get("LogMessage"));
					tmp.setDate_and_time(timestamp);
					
					String dateTime = (String) ((Map<String, Object>) MyApplicationsLogs.get(i)).get("LogDate");
			        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			        LocalDateTime formatDateTime = LocalDateTime.parse(dateTime, FORMATTER);
			        
					tmp.setDate_and_time_evento(formatDateTime);
					visualizzazioneEventiRepository.save(tmp);
					

					level = Integer.parseInt((String) ((Map<String, Object>) MyApplicationsLogs.get(i)).get("EntryType"));
					
					if(level==1) {
						alert="ERROR";
						alertEmail="errore";
					}

					if(level==2) {
						alert="WARNING";
						alertEmail="warning";
					}
						

					
					nome=(String) ((Map<String, Object>) MyApplicationsLogs.get(i)).get("LogMessage");
					
					if(alertConfigurazioneRepository.isOperazioneMonitorata(id_client,"WINDOWS_EVENTS")) {

						newAlert=new Alert();
						newAlert.setCorpo_messaggio("Si è verificato un "+ alertEmail +": " + nome);
						newAlert.setDate_and_time_alert(timestamp);
	//					newAlert.setDate_and_time_mail(timestamp);
						newAlert.setId_client(id_client);
						newAlert.setId_company(elencoClientsRepository.getIdCompany(id_client));
						newAlert.setTipo(alert);
						newAlert.setCategoria(3);
						
						Alert insertedAlert = elencoAlertRepository.save(newAlert);
						
						//Se operazione events monitorata invio mail
					
					
						int id_company = elencoClientsRepository.getIdCompany(id_client);
						ElencoCompanies company = elencoCompaniesRepository.getInfoCompany(id_company);
						ElencoClients client = elencoClientsRepository.getClientFromId(id_client);
						EmailService service = new EmailService();
						String tipo_alert;
						tipo_alert = alert;
						
						String token_mail = Services.addTokenToAuthenticationMail(company.getRagione_sociale(),id_client);
						
						InfoOperazioneMailEvents info = new InfoOperazioneMailEvents("WINDOWS_EVENTS", insertedAlert.getDate_and_time_alert().toString() , client.getNome(), company.getRagione_sociale(), nome, insertedAlert.getTipo(), token_mail, id_client,Services.address);
						
						EmailService.sendEmail("Alert windows events", service.getEmailContent(company, info) , company.getEmail_alert());
						elencoAlertRepository.updateMailTimestamp(insertedAlert.getId());
					}
					
				}
			}
			
			if(MyHardwareLogs!=null)
			{
				for(int i=0; i < MyHardwareLogs.size(); i++)
				{
					
					//sottocategoria, level, source, id_event, task_category, info, date_and_time
					VisualizzazioneEventi tmp = new VisualizzazioneEventi();
					tmp.setId_client(id_client);
					tmp.setSottocategoria("H");
					tmp.setLevel(Integer.parseInt((String) ((Map<String, Object>) MyHardwareLogs.get(i)).get("EntryType")));
					tmp.setSource((String) ((Map<String, Object>) MyHardwareLogs.get(i)).get("LogOrigin"));
					tmp.setId_event(Integer.parseInt((String) ((Map<String, Object>) MyHardwareLogs.get(i)).get("LogEventID")));
					tmp.setInfo((String) ((Map<String, Object>) MyHardwareLogs.get(i)).get("LogMessage"));
					tmp.setDate_and_time(timestamp);
					
					String dateTime = (String) ((Map<String, Object>) MyHardwareLogs.get(i)).get("LogDate");
			        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			        LocalDateTime formatDateTime = LocalDateTime.parse(dateTime, FORMATTER);
			        
					tmp.setDate_and_time_evento(formatDateTime);
					visualizzazioneEventiRepository.save(tmp);
					
					level = Integer.parseInt((String) ((Map<String, Object>) MyHardwareLogs.get(i)).get("EntryType"));
					
					if(level==1) {
						alert="ERROR";
						alertEmail="errore";
					}

					if(level==2) {
						alert="WARNING";
						alertEmail="warning";
					}
					
					nome=(String) ((Map<String, Object>) MyHardwareLogs.get(i)).get("LogMessage");

					if(alertConfigurazioneRepository.isOperazioneMonitorata(id_client,"WINDOWS_EVENTS")) {
						
						newAlert=new Alert();
						newAlert.setCorpo_messaggio("Si è verificato un "+ alertEmail +": " + nome);
						newAlert.setDate_and_time_alert(timestamp);
	//					newAlert.setDate_and_time_mail(timestamp);
						newAlert.setId_client(id_client);
						newAlert.setId_company(elencoClientsRepository.getIdCompany(id_client));
						newAlert.setTipo(alert);
						newAlert.setCategoria(3);
						
						Alert insertedAlert = elencoAlertRepository.save(newAlert);
					
					
						int id_company = elencoClientsRepository.getIdCompany(id_client);
						ElencoCompanies company = elencoCompaniesRepository.getInfoCompany(id_company);
						ElencoClients client = elencoClientsRepository.getClientFromId(id_client);
						EmailService service = new EmailService();
						String tipo_alert;
						tipo_alert = alert;
						
						String token_mail = Services.addTokenToAuthenticationMail(company.getRagione_sociale(),id_client);
						
						InfoOperazioneMailEvents info = new InfoOperazioneMailEvents("WINDOWS_EVENTS", insertedAlert.getDate_and_time_alert().toString() , client.getNome(), company.getRagione_sociale(), nome, insertedAlert.getTipo(), token_mail, id_client, Services.address);
						
						EmailService.sendEmail("Alert windows events", service.getEmailContent(company, info) , company.getEmail_alert());
						elencoAlertRepository.updateMailTimestamp(insertedAlert.getId());
					}
				}
			}
			
			if(MySystemLogs!=null)
			{
				for(int i=0; i < MySystemLogs.size(); i++)
				{
					
					//sottocategoria, level, source, id_event, task_category, info, date_and_time
					VisualizzazioneEventi tmp = new VisualizzazioneEventi();
					tmp.setId_client(id_client);
					tmp.setSottocategoria("S");
					tmp.setLevel(Integer.parseInt((String) ((Map<String, Object>) MySystemLogs.get(i)).get("EntryType")));
					tmp.setSource((String) ((Map<String, Object>) MySystemLogs.get(i)).get("LogOrigin"));
					tmp.setId_event(Integer.parseInt((String) ((Map<String, Object>) MySystemLogs.get(i)).get("LogEventID")));
					tmp.setInfo((String) ((Map<String, Object>) MySystemLogs.get(i)).get("LogMessage"));
					tmp.setDate_and_time(timestamp);
					
					String dateTime = (String) ((Map<String, Object>) MySystemLogs.get(i)).get("LogDate");
			        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			        LocalDateTime formatDateTime = LocalDateTime.parse(dateTime, FORMATTER);
			        
					tmp.setDate_and_time_evento(formatDateTime);
					visualizzazioneEventiRepository.save(tmp);
					
					level = Integer.parseInt((String) ((Map<String, Object>) MySystemLogs.get(i)).get("EntryType"));
					
					if(level==1) {
						alert="ERROR";
						alertEmail="errore";
					}

					if(level==2) {
						alert="WARNING";
						alertEmail="warning";
					}
					
					nome=(String) ((Map<String, Object>) MySystemLogs.get(i)).get("LogMessage");

					if(alertConfigurazioneRepository.isOperazioneMonitorata(id_client,"WINDOWS_EVENTS")) {
						newAlert=new Alert();
						newAlert.setCorpo_messaggio("Si è verificato un "+ alertEmail +": " + nome);
						newAlert.setDate_and_time_alert(timestamp);
	//					newAlert.setDate_and_time_mail(timestamp);
						newAlert.setId_client(id_client);
						newAlert.setId_company(elencoClientsRepository.getIdCompany(id_client));
						newAlert.setTipo(alert);
						newAlert.setCategoria(3);
						
						Alert insertedAlert = elencoAlertRepository.save(newAlert);
						
					
						int id_company = elencoClientsRepository.getIdCompany(id_client);
						ElencoCompanies company = elencoCompaniesRepository.getInfoCompany(id_company);
						ElencoClients client = elencoClientsRepository.getClientFromId(id_client);
						EmailService service = new EmailService();
						String tipo_alert;
						tipo_alert = alert;
						
						String token_mail = Services.addTokenToAuthenticationMail(company.getRagione_sociale(),id_client);
						
						InfoOperazioneMailEvents info = new InfoOperazioneMailEvents("WINDOWS_EVENTS", insertedAlert.getDate_and_time_alert().toString() , client.getNome(), company.getRagione_sociale(), nome, insertedAlert.getTipo(), token_mail, id_client,Services.address);
						
						EmailService.sendEmail("Alert windows events", service.getEmailContent(company, info) , company.getEmail_alert());
						elencoAlertRepository.updateMailTimestamp(insertedAlert.getId());
					}
				}
			}
			
			if(MySecurityLogs!=null)
			{
				for(int i=0; i < MySecurityLogs.size(); i++)
				{
					
					//sottocategoria, level, source, id_event, task_category, info, date_and_time
					VisualizzazioneEventi tmp = new VisualizzazioneEventi();
					tmp.setId_client(id_client);
					tmp.setSottocategoria("C");
					tmp.setLevel(Integer.parseInt((String) ((Map<String, Object>) MySecurityLogs.get(i)).get("EntryType")));
					tmp.setSource((String) ((Map<String, Object>) MySecurityLogs.get(i)).get("LogOrigin"));
					tmp.setId_event(Integer.parseInt((String) ((Map<String, Object>) MySecurityLogs.get(i)).get("LogEventID")));
					tmp.setInfo((String) ((Map<String, Object>) MySecurityLogs.get(i)).get("LogMessage"));
					tmp.setDate_and_time(timestamp);
					
					String dateTime = (String) ((Map<String, Object>) MySecurityLogs.get(i)).get("LogDate");
			        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			        LocalDateTime formatDateTime = LocalDateTime.parse(dateTime, FORMATTER);
			        
					tmp.setDate_and_time_evento(formatDateTime);
					visualizzazioneEventiRepository.save(tmp);
					
					level = Integer.parseInt((String) ((Map<String, Object>) MySecurityLogs.get(i)).get("EntryType"));
					
					if(level==1) {
						alert="ERROR";
						alertEmail="errore";
					}

					if(level==2) {
						alert="WARNING";
						alertEmail="warning";
					}
					
					nome=(String) ((Map<String, Object>) MySecurityLogs.get(i)).get("LogMessage");

					if(alertConfigurazioneRepository.isOperazioneMonitorata(id_client,"WINDOWS_EVENTS")) {
						
						newAlert=new Alert();
						newAlert.setCorpo_messaggio("Si è verificato un "+ alertEmail +": " + nome);
						newAlert.setDate_and_time_alert(timestamp);
	//					newAlert.setDate_and_time_mail(timestamp);
						newAlert.setId_client(id_client);
						newAlert.setId_company(elencoClientsRepository.getIdCompany(id_client));
						newAlert.setTipo(alert);
						newAlert.setCategoria(3);
						
						Alert insertedAlert = elencoAlertRepository.save(newAlert);
						
					
						int id_company = elencoClientsRepository.getIdCompany(id_client);
						ElencoCompanies company = elencoCompaniesRepository.getInfoCompany(id_company);
						ElencoClients client = elencoClientsRepository.getClientFromId(id_client);
						EmailService service = new EmailService();
						String tipo_alert;
						tipo_alert = alert;
						
						String token_mail = Services.addTokenToAuthenticationMail(company.getRagione_sociale(),id_client);
						
						InfoOperazioneMailEvents info = new InfoOperazioneMailEvents("WINDOWS_EVENTS", insertedAlert.getDate_and_time_alert().toString() , client.getNome(), company.getRagione_sociale(),nome, insertedAlert.getTipo(), token_mail, id_client,Services.address);
						
						EmailService.sendEmail("Alert windows events", service.getEmailContent(company, info) , company.getEmail_alert());
						elencoAlertRepository.updateMailTimestamp(insertedAlert.getId());
					}
				}
			}
			
			generalResponse.setMessage("Operazione effettuata con successo");
			generalResponse.setMessageCode(0);
		
			return ResponseEntity.ok(generalResponse); 
		}
		catch(Exception e)
		{
			generalResponse.setMessage(e.getMessage());
			generalResponse.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(generalResponse);
		}
	}
	
	@PostMapping(path="/getServiziAll",produces=MediaType.APPLICATION_JSON_VALUE) //ritorna tutti indipendentemente se sono monitorati o no
	@CrossOrigin
	public ResponseEntity<GeneralResponse> getServiziAll (@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		int id_company;
		int id_client;
		String token;
		ServiziResponse response=new ServiziResponse();
		List<ConfWindowsServices> serviziMonitorati=new ArrayList<ConfWindowsServices>();
		
		try
		{
			id_client=Integer.parseInt((String) body.get("id_client"));
			id_company=elencoClientsRepository.getIdCompany(id_client);
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				int limite = confWindowsServicesRepository.getTotServizi(id_client);
				serviziMonitorati=confWindowsServicesRepository.getServizi(id_client, limite);
				
				response.setConfWindowsServices(serviziMonitorati);
				
				String newToken=Services.checkThreshold(id_company, token);
				
				response.setMessage("Operazione effettuata con successo");
				response.setMessageCode(0);
				response.setToken(newToken);
				
				return ResponseEntity.ok(response); 
				
			}
			generalResponse.setMessage("Autenticazione fallita");
			generalResponse.setMessageCode(-2);
			return ResponseEntity.ok(generalResponse);
		}
		catch(Exception e)
		{
			generalResponse.setMessage(e.getMessage());
			generalResponse.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(generalResponse);
		}
	}
	

	@PostMapping(path="/getServiziOverview",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> getServiziOverview (@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		int id_company;
		int id_client;
		String token;
		ServiziOverviewResponse response=new ServiziOverviewResponse();
		
		try 
		{
			id_client=Integer.parseInt((String)body.get("id_client"));
			id_company=elencoClientsRepository.getIdCompany(id_client);
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				//Stopped=1, StartPending=2, StopPending=3, Running=4, ContinuePending=5, PausePending=6, Paused=7
				response.setN_totali(confWindowsServicesRepository.getTotServizi(id_client));
//				int limite = confWindowsServicesRepository.getTotServizi(id_client);
				response.setN_running(confWindowsServicesRepository.getNumStatoAll(id_client, 4));
				response.setN_stopped(confWindowsServicesRepository.getNumStatoAll(id_client, 1));
				response.setN_monitorati(monitoraggioRepository.getNServiziMonitorati(id_client));
				
				
				String newToken=Services.checkThreshold(id_company, token);
				
				response.setMessage("Operazione effettuata con successo");
				response.setMessageCode(0);
				response.setToken(newToken);
				
				return ResponseEntity.ok(response); 
				
			}
			generalResponse.setMessage("Autenticazione fallita");
			generalResponse.setMessageCode(-2);
			return ResponseEntity.ok(generalResponse);
		}
		catch(Exception e)
		{
			generalResponse.setMessage(e.getMessage());
			generalResponse.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(generalResponse);
		}
	}
	
	@PostMapping(path="/getEventiOverview",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> getEventiOverview (@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		int id_company;
		int id_client;
		String token;
		EventiOverviewResponse response=new EventiOverviewResponse();
		HashMap<String, Integer> tot=new HashMap<String, Integer>();
		
		try
		{
			id_client=Integer.parseInt((String) body.get("id_client"));
			id_company=elencoClientsRepository.getIdCompany(id_client);
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				int problemi_oggi=visualizzazioneEventiRepository.getProblemiOggi(id_client);
				int warning_oggi=visualizzazioneEventiRepository.getWarningOggi(id_client);
				List<String> sottocategorie=visualizzazioneEventiRepository.getSottocategorie(id_client);
				for(String i:sottocategorie)
				{
					int num=visualizzazioneEventiRepository.contaSottocategoria(id_client, i);
					tot.put(i, num);
				}
				
				response.setProblemi_oggi(problemi_oggi);
				response.setWarning_oggi(warning_oggi);
				response.setTot_per_sottocategoria(tot);
				
				String newToken=Services.checkThreshold(id_company, token);
				
				response.setMessage("Operazione effettuata con successo");
				response.setMessageCode(0);
				response.setToken(newToken);
				
				return ResponseEntity.ok(response); 
			}
			generalResponse.setMessage("Autenticazione fallita");
			generalResponse.setMessageCode(-2);
			return ResponseEntity.ok(generalResponse);
		}
		catch(Exception e)
		{
			generalResponse.setMessage(e.getMessage());
			generalResponse.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(generalResponse);
		}
	}
	
	@PostMapping(path="/getOperazioniOverview",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> getOperazioniOverview (@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		int id_company;
		int id_client;
		String token;
		OperazioniOverviewResponse response=new OperazioniOverviewResponse();
		try
		{
			//assumo che in stato ci siano tutte le info
			id_client=(Integer)body.get("id_client");
			id_company=elencoClientsRepository.getIdCompany(id_client);
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				int attive= elencoOperazioniRepository.countStato("attiva", id_client);
				int esecuzione= elencoOperazioniRepository.countStato("esecuzione", id_client);
				int problemi= elencoOperazioniRepository.countStato("error", id_client);
				int warning= elencoOperazioniRepository.countStato("warning", id_client);
				
				response.setAttive(attive);
				response.setEsecuzione(esecuzione);
				response.setProblemi(problemi);
				response.setWarning(warning);
				
				String newToken=Services.checkThreshold(id_company, token);
				
				response.setMessage("Operazione effettuata con successo");
				response.setMessageCode(0);
				response.setToken(newToken);
				
				return ResponseEntity.ok(response); 
			}
			
			generalResponse.setMessage("Autenticazione fallita");
			generalResponse.setMessageCode(-2);
			return ResponseEntity.ok(generalResponse);
		}
		catch(Exception e)
		{
			generalResponse.setMessage(e.getMessage());
			generalResponse.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(generalResponse);
		}
	}
	
	@PostMapping(path="/getServiziMonitorati",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> getServiziMonitorati (@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		int id_company;
		int id_client;
		String token;
		ServiziMonitoratiResponse response=new ServiziMonitoratiResponse();	
		
		try
		{
			//assumo che in stato ci siano tutte le info
			id_client=Integer.parseInt((String) body.get("id_client"));
			id_company=elencoClientsRepository.getIdCompany(id_client);
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid()) {
				List<Monitoraggio> monitorati= monitoraggioRepository.getListaMonitorati(id_client);
				
				
				response.setMonitoraggi(monitorati);
				
				String newToken=Services.checkThreshold(id_company, token);
				
				response.setMessage("Operazione effettuata con successo");
				response.setMessageCode(0);
				response.setToken(newToken);
				
				return ResponseEntity.ok(response); 
			}
			
			generalResponse.setMessage("Autenticazione fallita");
			generalResponse.setMessageCode(-2);
			return ResponseEntity.ok(generalResponse);
		
		}
		catch(Exception e)
		{
			generalResponse.setMessage(e.getMessage());
			generalResponse.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(generalResponse);
		}
	}
	
	@PostMapping(path="/modificaMonitoraggioServizio",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> modificaMonitoraggioServizio (@RequestBody Map<String,Object> body) {
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		int id_company;
		int id_client;
		String token;
		String nome_servizio;
		boolean monitora;
		
		try
		{
			id_client=Integer.parseInt((String) body.get("id_client"));
			nome_servizio = (String) body.get("nome_servizio");
			monitora = Boolean.parseBoolean((String) body.get("monitora"));
			id_company=elencoClientsRepository.getIdCompany(id_client);
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid()) {
				
				monitoraggioRepository.updateMonitora(monitora, nome_servizio);
				
				String newToken=Services.checkThreshold(id_company, token);
				
				generalResponse.setMessage("Operazione effettuata con successo");
				generalResponse.setMessageCode(0);
				generalResponse.setToken(newToken);
				
				return ResponseEntity.ok(generalResponse); 
			}
			
			generalResponse.setMessage("Autenticazione fallita");
			generalResponse.setMessageCode(-2);
			return ResponseEntity.ok(generalResponse);
		
		}
		catch(Exception e)
		{
			generalResponse.setMessage(e.getMessage());
			generalResponse.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(generalResponse);
		}
	}
	
	//ritorna n oggetti appartenenti alla sottocategoria in posizione slot, cioè se ho n=50,
	//slot=1 e sottocategoria=”A”, ritorna i primi 50*1 elementi con sottocategoria=”A” (da 0 a 49), 
	//se lo slot è 2 i secondi 50 (da 50 a 99) e così via. 
	//Se la sottocategoria è “*” allora restituisci gli elementi indipendentemente dalla loro sottocategoria, 
	//quindi qualsiasi evento rispettando n e slot.
	
	@PostMapping(path="/getEventi",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> getEventi (@RequestBody Map<String,Object> body) {
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		int id_company;
		int id_client;
		String token;
		String sottocategoria;
		int slot;
		int n;
		List<VisualizzazioneEventi> eventi = new ArrayList<VisualizzazioneEventi>();
		List<VisualizzazioneEventi> eventiSlot = new ArrayList<VisualizzazioneEventi>();
		EventiResponse response = new EventiResponse();
		
		try
		{
			id_client=Integer.parseInt((String) body.get("id_client"));
			sottocategoria = (String) body.get("sottocategoria");
			slot = Integer.parseInt((String) body.get("slot"));
			n = Integer.parseInt((String) body.get("n"));
			
			id_company=elencoClientsRepository.getIdCompany(id_client);
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid()) {
				
//				monitoraggioRepository.updateMonitora(monitora, nome_servizio);
				eventi = visualizzazioneEventiRepository.getEventi(id_client, sottocategoria);
				int Npartenza = n*(slot-1);
				int Narrivo = (n*slot)-1;
				if(Narrivo > eventi.size())
					Narrivo = eventi.size();
				
				for(int i=Npartenza; i<= Narrivo; i++) {
					try {
						eventiSlot.add(eventi.get(i));
					} catch (Exception e) {
						break;
					}
					
				}
				
				response.setEventi(eventiSlot);
				String newToken=Services.checkThreshold(id_company, token);
				
				response.setMessage("Operazione effettuata con successo");
				response.setMessageCode(0);
				response.setToken(newToken);
				
				return ResponseEntity.ok(response); 
			}
			
			generalResponse.setMessage("Autenticazione fallita");
			generalResponse.setMessageCode(-2);
			return ResponseEntity.ok(generalResponse);
		
		}
		catch(Exception e)
		{
			generalResponse.setMessage(e.getMessage());
			generalResponse.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(generalResponse);
		}
	}
	
	@PostMapping(path="/getClientOverview",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> getClientOverviewServices(@RequestBody Map<String,Object> body) {
		
		ClientOverviewResponse response = new ClientOverviewResponse();
		
		ClientOverviewResponseSub responseServices=new ClientOverviewResponseSub();
		ClientOverviewResponseSub responseEventi=new ClientOverviewResponseSub();
		ClientOverviewResponseSub responseDrives=new ClientOverviewResponseSub();

		ValidToken validToken=new ValidToken();
		int id_company;
		int id_client;
		String token;
		int n_errori;
		int n_warning;
		int n_ok = 0;
		int limite;
		
		try
		{
			id_client = Integer.parseInt( (String) body.get("id_client"));
			id_company=elencoClientsRepository.getIdCompany(id_client);
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid()) {
				
				//----------------SERVIZI--------------------------------
				
				limite = confWindowsServicesRepository.getTotServizi(id_client);
				n_errori = confWindowsServicesRepository.getNumStato(id_client, 1);
				n_ok = confWindowsServicesRepository.getNumStato(id_client, 4);
				n_warning = limite - n_errori - n_ok;
				
				responseServices.setErrori(n_errori);
				responseServices.setWarning(n_warning);
				responseServices.setOk(n_ok);
				
				//-----------------EVENTI-------------------------------
				
				limite = visualizzazioneEventiRepository.getTotEventiToday(id_client);
				n_errori = visualizzazioneEventiRepository.getNumStato(id_client, 1, limite);
				n_warning = visualizzazioneEventiRepository.getNumStato(id_client, 2, limite);
				
				responseEventi.setErrori(n_errori);
				responseEventi.setWarning(n_warning);
				
				//--------------DRIVES------------------------------------
				
				limite = confTotalFreeDiscSpaceRepository.getTotDrives(id_client);
				n_errori = confTotalFreeDiscSpaceRepository.getNumError(id_client, limite);
				n_warning = confTotalFreeDiscSpaceRepository.getNumWarning(id_client, limite);
				n_ok = limite - n_errori - n_warning;
				
				responseDrives.setErrori(n_errori);
				responseDrives.setWarning(n_warning);
				responseDrives.setOk(n_ok);
				
				
				String newToken=Services.checkThreshold(id_company, token);
				
				response.setDrivesResponse(responseDrives);
				response.setEventiResponse(responseEventi);
				response.setServicesResponse(responseServices);
				response.setMessage("Operazione effettuata con successo");
				response.setMessageCode(0);
				response.setToken(newToken);
				
				return ResponseEntity.ok(response); 
			}
			
			response.setMessage("Autenticazione fallita");
			response.setMessageCode(-2);
			return ResponseEntity.ok(response);
		
		}
		catch(Exception e)
		{
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping(path="/getCompanyOverview",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> getCompanyOverview(@RequestBody Map<String,Object> body) {
		CompanyOverviewResponse response=new CompanyOverviewResponse();
		ValidToken validToken=new ValidToken();
		int id_company;
		String token;
		List<Integer> clients_with_service = new ArrayList<Integer>();
		List<Integer> clients_with_event = new ArrayList<Integer>();
		List<Integer> clients_with_drive = new ArrayList<Integer>();
		List<Integer> clients_with_error = new ArrayList<Integer>();
		List<Integer> clients_with_warning = new ArrayList<Integer>();
		List<Integer> clients_with_ok = new ArrayList<Integer>();
		
		try
		{
			id_company = Integer.parseInt( (String) body.get("id_company"));
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid()) {
				
				clients_with_service = elencoCompaniesRepository.getIdsOfClientsWithServicesErrors(id_company);
				clients_with_error.addAll(clients_with_service);
				
				clients_with_event = elencoCompaniesRepository.getIdsOfClientsWithEventsErrors(id_company);
				clients_with_event.forEach(client -> {
					if(!clients_with_error.contains(client))
						clients_with_error.add(client);
				});
				
				clients_with_drive = elencoCompaniesRepository.getIdsOfClientsWithDrivesErrors(id_company);
				clients_with_drive.forEach(client -> {
					if(!clients_with_error.contains(client))
						clients_with_error.add(client);
				});
				
				clients_with_service = new ArrayList<Integer>();
				clients_with_event = new ArrayList<Integer>();
				clients_with_drive = new ArrayList<Integer>();
				
				clients_with_service = elencoCompaniesRepository.getIdsOfClientsWithServicesWarning(id_company);
				clients_with_warning.addAll(clients_with_service);
				
				clients_with_event = elencoCompaniesRepository.getIdsOfClientsWithEventsWarning(id_company);
				clients_with_event.forEach(client -> {
					if(!clients_with_warning.contains(client))
						clients_with_warning.add(client);
				});
				
				clients_with_drive = elencoCompaniesRepository.getIdsOfClientsWithDrivesWarning(id_company);
				clients_with_drive.forEach(client -> {
					if(!clients_with_warning.contains(client))
						clients_with_warning.add(client);
				});
				
				clients_with_ok = elencoCompaniesRepository.getIdsClientOfCompany(id_company);
				
				for(Integer client: clients_with_error)
					if(clients_with_ok.contains(client))
						clients_with_ok.remove(client);

				for(Integer client: clients_with_warning)
					if(clients_with_ok.contains(client))
						clients_with_ok.remove(client);		
				
				response.setErrori(clients_with_error);
				response.setWarning(clients_with_warning);
				response.setOk(clients_with_ok);
				
				String newToken=Services.checkThreshold(id_company, token);
				
				response.setMessage("Operazione effettuata con successo");
				response.setMessageCode(0);
				response.setToken(newToken);
				
				return ResponseEntity.ok(response); 
			}
			
			response.setMessage("Autenticazione fallita");
			response.setMessageCode(-2);
			return ResponseEntity.ok(response);
		
		}
		catch(Exception e)
		{
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping(path="/getLastDate",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<MaxDateResponse> getLastDate(@RequestBody Map<String,Object> body) {
		MaxDateResponse response=new MaxDateResponse();
		ValidToken validToken=new ValidToken();
		int id_client;
		int id_company;
		String token;
		LocalDateTime lastServicesDate;
		LocalDateTime lastEventsDate;
		LocalDateTime lastDrivesDate;
		
		try
		{
			id_client = Integer.parseInt( (String) body.get("id_client"));
			id_company=elencoClientsRepository.getIdCompany(id_client);
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid()) {	
				
				lastEventsDate = visualizzazioneEventiRepository.getMaxDateAndTimeEvents(id_client);
				lastServicesDate = confWindowsServicesRepository.getMaxDateAndTimeServices(id_client);
				lastDrivesDate = confTotalFreeDiscSpaceRepository.getMaxDateAndTimeDrives(id_client);
				
				if(lastDrivesDate == null)
					lastDrivesDate = LocalDateTime.parse("0000-01-01T00:00:00");
				
				if(lastServicesDate == null)
					lastServicesDate = LocalDateTime.parse("0000-01-01T00:00:00");
				
				if(lastEventsDate == null)
					lastEventsDate = LocalDateTime.parse("0000-01-01T00:00:00");
				
				if(!lastDrivesDate.equals(LocalDateTime.parse("0000-01-01T00:00:00")) || !lastEventsDate.equals(LocalDateTime.parse("0000-01-01T00:00:00")) || !lastServicesDate.equals(LocalDateTime.parse("0000-01-01T00:00:00"))) {
					if(lastEventsDate.isAfter(lastServicesDate) && lastEventsDate.isAfter(lastDrivesDate)) {
						response.setMaxDate(lastEventsDate);
					}
					else if (lastDrivesDate.isAfter(lastEventsDate) && lastDrivesDate.isAfter(lastServicesDate)) {
						response.setMaxDate(lastDrivesDate);
					}
					else {
						response.setMaxDate(lastServicesDate);
					}
				}
				
				String newToken=Services.checkThreshold(id_company, token);
				
				response.setMessage("Operazione effettuata con successo");
				response.setMessageCode(0);
				response.setToken(newToken);
				
				return ResponseEntity.ok(response); 
			}
			
			response.setMessage("Autenticazione fallita");
			response.setMessageCode(-2);
			return ResponseEntity.ok(response);
		
		}
		catch(Exception e)
		{
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping(path="/getAllServicesOfCompany",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GetClientNameOfCompanyResponse> getAllServicesOfCompany(@RequestBody Map<String,Object> body) {
		GetClientNameOfCompanyResponse response=new GetClientNameOfCompanyResponse();
		ValidToken validToken=new ValidToken();
		int id_company;
		String token;
		List<String> allServices = new ArrayList<String>();
		
		try
		{
			id_company = Integer.parseInt( (String) body.get("id_company"));
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid()) {	
				
				allServices= confWindowsServicesRepository.getAllServicesOfCompany(id_company);
				
				
				String newToken=Services.checkThreshold(id_company, token);
				
				response.setNomi_servizi(allServices);
				response.setMessage("Operazione effettuata con successo");
				response.setMessageCode(0);
				response.setToken(newToken);
				
				return ResponseEntity.ok(response); 
			}
			
			response.setMessage("Autenticazione fallita");
			response.setMessageCode(-2);
			return ResponseEntity.ok(response);
		
		}
		catch(Exception e)
		{
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PostMapping(path="/updateMonitoraAllServices",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> updateMonitoraAllServices(@RequestBody Map<String,Object> body) {
		GeneralResponse response=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		Integer id_company;
		String token;
		Integer tipologia;
		Integer licenza;
		Integer sede;
		boolean monitora;
		String nome_servizio;
		
		try
		{
			tipologia= ((String)body.get("tipologia")==null) ? -1 : Integer.parseInt((String)body.get("tipologia")); 
			licenza= (body.get("licenza")==null) ? -1 : Integer.parseInt((String)body.get("licenza"));
			sede= ((String)body.get("sede")==null) ? -1 : Integer.parseInt((String)body.get("sede"));
			monitora=  (boolean) body.get("monitora");
			nome_servizio = (String) body.get("nome_servizio");
			id_company = Integer.parseInt( (String) body.get("id_company"));
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			
			if(validToken.isValid()) {	
				
				monitoraggioRepository.updateFilteredServices(monitora, nome_servizio, tipologia, licenza, sede, id_company);			
				
				String newToken=Services.checkThreshold(id_company, token);
				
				response.setMessage("Operazione effettuata con successo");
				response.setMessageCode(0);
				response.setToken(newToken);
				
				return ResponseEntity.ok(response); 
			}
			
			response.setMessage("Autenticazione fallita");
			response.setMessageCode(-2);
			return ResponseEntity.ok(response);
		
		}
		catch(Exception e)
		{
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
}
