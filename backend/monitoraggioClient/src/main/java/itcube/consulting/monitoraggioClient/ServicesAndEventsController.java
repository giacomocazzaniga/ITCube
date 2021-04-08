package itcube.consulting.monitoraggioClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import itcube.consulting.monitoraggioClient.entities.ConfWindowsServices;
import itcube.consulting.monitoraggioClient.entities.Monitoraggio;
import itcube.consulting.monitoraggioClient.entities.VisualizzazioneEventi;
import itcube.consulting.monitoraggioClient.entities.database.ValidToken;
import itcube.consulting.monitoraggioClient.repositories.ConfTotalFreeDiscSpaceRepository;
import itcube.consulting.monitoraggioClient.repositories.ConfWindowsServicesRepository;
import itcube.consulting.monitoraggioClient.repositories.ConfigRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoClientsRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoCompaniesRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoLicenzeRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoOperazioniRepository;
import itcube.consulting.monitoraggioClient.repositories.MonitoraggioRepository;
import itcube.consulting.monitoraggioClient.repositories.RealTimeRepository;
import itcube.consulting.monitoraggioClient.repositories.TipologieClientRepository;
import itcube.consulting.monitoraggioClient.repositories.TipologieLicenzeRepository;
import itcube.consulting.monitoraggioClient.repositories.VisualizzazioneEventiRepository;
import itcube.consulting.monitoraggioClient.response.EventiOverviewResponse;
import itcube.consulting.monitoraggioClient.response.GeneralResponse;
import itcube.consulting.monitoraggioClient.response.OperazioniOverviewResponse;
import itcube.consulting.monitoraggioClient.response.ServiziMonitoratiResponse;
import itcube.consulting.monitoraggioClient.response.ServiziResponse;
import itcube.consulting.monitoraggioClient.response.ServiziOverviewResponse;
import itcube.consulting.monitoraggioClient.response.ShallowClientsResponse;
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
	
	@PostMapping(path="/inserimentoServizi",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> inserimentoServizi (@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		int id_client;
		String token;
		List<ConfWindowsServices> servizi=new ArrayList<ConfWindowsServices>();
		List<Monitoraggio> monitoraggio=new ArrayList<Monitoraggio>();
		ConfWindowsServices confWindowsServices=new ConfWindowsServices();
		LocalDateTime timestamp=java.time.LocalDateTime.now();
		
		try
		{
			id_client=Integer.parseInt((String)body.get("id_client"));
			servizi=(List<ConfWindowsServices>)body.get("servizi");
			System.out.println(servizi.get(0));
			if(servizi.size()!=0)
			{
				
				for(int i=0; i < servizi.size(); i++)
				{
					ConfWindowsServices tmp = new ConfWindowsServices();
					tmp.setId_client(id_client);
					tmp.setNome_servizio((String) ((Map<String, Object>) servizi.get(i)).get("nome_servizio"));
					tmp.setDisplay_name((String) ((Map<String, Object>) servizi.get(i)).get("display_name"));
					tmp.setStato((int) ((Map<String, Object>) servizi.get(i)).get("stato"));
					tmp.setStart_type((int) ((Map<String, Object>) servizi.get(i)).get("start_type"));
					tmp.setService_type((int) ((Map<String, Object>) servizi.get(i)).get("service_type"));
					tmp.setDate_and_time(timestamp);
					confWindowsServicesRepository.save(tmp);
				}
				
				id_client=Integer.parseInt((String) body.get("id_client"));
				monitoraggio=monitoraggioRepository.getServiziClient(id_client);
				System.out.println(monitoraggio.get(0).getNome_servizio());
				if(monitoraggio!=null)
				{
					
					
					for(int i=0; i < servizi.size(); i++)
					{
						ConfWindowsServices tmp = new ConfWindowsServices();
						tmp.setId_client(id_client);
						tmp.setNome_servizio((String) ((Map<String, Object>) servizi.get(i)).get("nome_servizio"));
						tmp.setDisplay_name((String) ((Map<String, Object>) servizi.get(i)).get("display_name"));
						tmp.setStato((int) ((Map<String, Object>) servizi.get(i)).get("stato"));
						tmp.setStart_type((int) ((Map<String, Object>) servizi.get(i)).get("start_type"));
						tmp.setService_type((int) ((Map<String, Object>) servizi.get(i)).get("service_type"));
						tmp.setDate_and_time(timestamp);
						
					
						if(!monitoraggio.contains(tmp.toMonitoraggio(tmp)))
						{
							monitoraggioRepository.save(tmp.toMonitoraggio(tmp));
						}
					}
				}
				else
				{
					
					for(int i=0; i < servizi.size(); i++)
					{
						ConfWindowsServices tmp = new ConfWindowsServices();
						tmp.setId_client(id_client);
						tmp.setNome_servizio((String) ((Map<String, Object>) servizi.get(i)).get("nome_servizio"));
						tmp.setDisplay_name((String) ((Map<String, Object>) servizi.get(i)).get("display_name"));
						tmp.setStato((int) ((Map<String, Object>) servizi.get(i)).get("stato"));
						tmp.setStart_type((int) ((Map<String, Object>) servizi.get(i)).get("start_type"));
						tmp.setService_type((int) ((Map<String, Object>) servizi.get(i)).get("service_type"));
						tmp.setDate_and_time(timestamp);
					
				
						monitoraggioRepository.save(tmp.toMonitoraggio(tmp));
					}
				}
			
				generalResponse.setMessage("Operazione effettuata con successo");
				generalResponse.setMessageCode(0);
			
				return ResponseEntity.ok(generalResponse); 
			}
			else
			{
				generalResponse.setMessage("Lista dei servizi vuota");
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
	public ResponseEntity<GeneralResponse> inserimentoEventi (@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		int id_client;
		List<VisualizzazioneEventi> eventi=new ArrayList<VisualizzazioneEventi>();
		LocalDateTime timestamp=java.time.LocalDateTime.now();
		
		try
		{
			id_client=Integer.parseInt((String) body.get("id_client"));
			eventi=(List<VisualizzazioneEventi>)body.get("eventi");
			if(eventi!=null)
			{
				for(int i=0; i < eventi.size(); i++)
				{
					
					//sottocategoria, level, source, id_event, task_category, info, date_and_time
					VisualizzazioneEventi tmp = new VisualizzazioneEventi();
					tmp.setId_client(id_client);
					tmp.setSottocategoria((int) ((Map<String, Object>) eventi.get(i)).get("sottocategoria"));
					tmp.setLevel((int) ((Map<String, Object>) eventi.get(i)).get("level"));
					tmp.setSource((String) ((Map<String, Object>) eventi.get(i)).get("source"));
					tmp.setId_event((int) ((Map<String, Object>) eventi.get(i)).get("id_event"));
					tmp.setTask_category((String) ((Map<String, Object>) eventi.get(i)).get("task_category"));
					tmp.setInfo((String) ((Map<String, Object>) eventi.get(i)).get("info"));
					tmp.setDate_and_time(timestamp);
					tmp.setDate_and_time_evento((LocalDateTime) ((Map<String, Object>) eventi.get(i)).get("date_and_time_evento"));
					visualizzazioneEventiRepository.save(tmp);
				}
				generalResponse.setMessage("Operazione effettuata con successo");
				generalResponse.setMessageCode(0);
			
				return ResponseEntity.ok(generalResponse); 
			}
			else
			{
				generalResponse.setMessage("Lista degli eventi vuota");
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
				serviziMonitorati=confWindowsServicesRepository.getServizi(id_client);
				
				response.setConfWindowsServices(serviziMonitorati);
				
				String newToken=Services.checkThreshold(id_company, token);
				
				response.setMessage("Operazione effettuata con successo");
				response.setMessageCode(0);
				response.setToken(newToken);
				
				return ResponseEntity.ok(response); 
				
			}
			generalResponse.setMessage("Autenticazione fallita");
			generalResponse.setMessageCode(-2);
			return ResponseEntity.badRequest().body(generalResponse);
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
				//ho assunto: 1=running, 2=stopped
				response.setN_totali((confWindowsServicesRepository.getTotServizi(id_client)));
				response.setN_running(confWindowsServicesRepository.getNumStato(id_client, 1));
				response.setN_stopped(confWindowsServicesRepository.getNumStato(id_client, 2));
				response.setN_monitorati(monitoraggioRepository.getNServiziMonitorati(id_client));
				
				
				String newToken=Services.checkThreshold(id_company, token);
				
				response.setMessage("Operazione effettuata con successo");
				response.setMessageCode(0);
				response.setToken(newToken);
				
				return ResponseEntity.ok(response); 
				
			}
			generalResponse.setMessage("Autenticazione fallita");
			generalResponse.setMessageCode(-2);
			return ResponseEntity.badRequest().body(generalResponse);
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
		HashMap<Integer, Integer> tot=new HashMap<Integer, Integer>();
		
		try
		{
			id_client=(Integer)body.get("id_client");
			id_company=elencoClientsRepository.getIdCompany(id_client);
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				int problemi_oggi=visualizzazioneEventiRepository.getProblemiOggi(id_client);
				int warning_oggi=visualizzazioneEventiRepository.getWarningOggi(id_client);
				List<Integer> sottocategorie=visualizzazioneEventiRepository.getSottocategorie(id_client);
				for(Integer i:sottocategorie)
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
			return ResponseEntity.badRequest().body(generalResponse);
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
			return ResponseEntity.badRequest().body(generalResponse);
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
			return ResponseEntity.badRequest().body(generalResponse);
		
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
			//assumo che in stato ci siano tutte le info
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
			return ResponseEntity.badRequest().body(generalResponse);
		
		}
		catch(Exception e)
		{
			generalResponse.setMessage(e.getMessage());
			generalResponse.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(generalResponse);
		}
	}
	
}
