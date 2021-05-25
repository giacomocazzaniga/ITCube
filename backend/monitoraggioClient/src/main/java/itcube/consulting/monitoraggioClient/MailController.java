package itcube.consulting.monitoraggioClient;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itcube.consulting.monitoraggioClient.entities.Alert;
import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;
import itcube.consulting.monitoraggioClient.entities.database.EmailResocontoHandler;
import itcube.consulting.monitoraggioClient.entities.database.ResocontoErroriMacchina;
import itcube.consulting.monitoraggioClient.entities.database.ValidToken;
import itcube.consulting.monitoraggioClient.repositories.ElencoClientsRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoCompaniesRepository;
import itcube.consulting.monitoraggioClient.response.AgentResponse;
import itcube.consulting.monitoraggioClient.response.GeneralResponse;
import itcube.consulting.monitoraggioClient.response.GetEmailIntervalResponse;
import itcube.consulting.monitoraggioClient.services.EmailService;
import itcube.consulting.monitoraggioClient.services.Services;

@RestController
@RequestMapping(path="/be/main")
public class MailController {
	
	private EmailResocontoHandler handler = new EmailResocontoHandler();		
	
	@Autowired
	ElencoClientsRepository elencoClientsRepository;
	
	@Autowired
	ElencoCompaniesRepository elencoCompaniesRepository;

	@PostMapping(path="/changeMailInterval",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> changeMailInterval(@RequestBody Map<String,Object> body)
	{
		Integer id_company;
		ValidToken validToken=new ValidToken();
		GeneralResponse response = new GeneralResponse();
		String token;
		Long fixedRate;
		boolean stopTimer;
		
		try {
			
			id_company=Integer.parseInt((String) body.get("id_company"));
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			fixedRate = Long.parseLong((String) body.get("fixedRate"));
			stopTimer = (boolean) body.get("stopTimer"); 
			
			if(validToken.isValid())
			{
				ElencoCompanies company = elencoCompaniesRepository.getInfoCompany(id_company);
			
				handler.scheduledEmail(company, fixedRate, stopTimer, elencoClientsRepository);
				elencoCompaniesRepository.updateMailInterval(id_company, fixedRate);
	
				response.setMessageCode(0);
				response.setMessage("Operazione avvenuta con successo");
				
				return ResponseEntity.ok(response);
			}
			
			response.setMessage("Autenticazione fallita");
			response.setMessageCode(-2);
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessageCode(-1);
			response.setMessage("KO");
			
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	
	@PostMapping(path="/getMailInterval",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GetEmailIntervalResponse> getMailInterval(@RequestBody Map<String,Object> body)
	{
		Integer id_company;
		ValidToken validToken=new ValidToken();
		GetEmailIntervalResponse response = new GetEmailIntervalResponse();
		String token;
		
		try {
			
			id_company=Integer.parseInt((String) body.get("id_company"));
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token); 
			
			if(validToken.isValid())
			{
				Long emailInterval = elencoCompaniesRepository.getMailInterval(id_company);
	
				response.setMailInterval(emailInterval);
				response.setMessageCode(0);
				response.setMessage("Operazione avvenuta con successo");
				
				return ResponseEntity.ok(response);
			}
			
			response.setMessage("Autenticazione fallita");
			response.setMessageCode(-2);
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessageCode(-1);
			response.setMessage("KO");
			
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
}
