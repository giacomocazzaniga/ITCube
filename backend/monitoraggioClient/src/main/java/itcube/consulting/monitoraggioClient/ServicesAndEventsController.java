package itcube.consulting.monitoraggioClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import itcube.consulting.monitoraggioClient.entities.ConfTotalFreeDiscSpace;
import itcube.consulting.monitoraggioClient.entities.ConfWindowsServices;
import itcube.consulting.monitoraggioClient.entities.ElencoClients;
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
import itcube.consulting.monitoraggioClient.response.AgentResponse;
import itcube.consulting.monitoraggioClient.response.EventiOverviewResponse;
import itcube.consulting.monitoraggioClient.response.EventiResponse;
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
	public ResponseEntity<AgentResponse> inserimentoServizi (@RequestBody Map<String,Object> body)
	{
		AgentResponse generalResponse=new AgentResponse();
		ValidToken validToken=new ValidToken();
		int id_client;
		String token;
		List<ConfWindowsServices> servizi=new ArrayList<ConfWindowsServices>();
		List<Monitoraggio> monitoraggio=new ArrayList<Monitoraggio>();
		ConfWindowsServices confWindowsServices=new ConfWindowsServices();
		LocalDateTime timestamp=java.time.LocalDateTime.now();
		List<ConfTotalFreeDiscSpace> disco=new ArrayList<ConfTotalFreeDiscSpace>();
		boolean serviziNotNull;
		boolean dischiNotNull;
		try
		{
			id_client=Integer.parseInt((String)body.get("MyID"));
			servizi=(List<ConfWindowsServices>)body.get("Services");
			disco=(List<ConfTotalFreeDiscSpace>)body.get("MyDrives");
			
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
					tmp.setService_type(Integer.parseInt((String) ((Map<String, Object>) servizi.get(i)).get("ServiceType")));
					tmp.setDate_and_time(timestamp);
					confWindowsServicesRepository.save(tmp);
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
						
						Integer idMonitoraggio=monitoraggioRepository.containsServizio((String) ((Map<String, Object>) servizi.get(i)).get("ServiceName"));
						
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
			
			}
			
			if(dischiNotNull)
			{
				//VolumeName, VolumeLabel, TotalSize, TotalFreeSpace
				for(int i=0; i < disco.size(); i++)
				{
					ConfTotalFreeDiscSpace tmp = new ConfTotalFreeDiscSpace();
					
					String drive=(String) ((Map<String, Object>) disco.get(i)).get("VolumeName");
					String descrizione=(String) ((Map<String, Object>) disco.get(i)).get("VolumeLabel");
					long total_size=Integer.parseInt((String) ((Map<String, Object>) disco.get(i)).get("TotalSize"));
					long total_free_disc_space=Integer.parseInt((String) ((Map<String, Object>) disco.get(i)).get("TotalFreeSpace"));

					tmp.setId_client(id_client);
					tmp.setDrive(drive);
					tmp.setDescrizione(descrizione);
					tmp.setTotal_size(total_size);
					tmp.setTotal_free_disc_space(total_free_disc_space);
					double perc_free_disc_space=tmp.setPerc_free_disc_space(total_size,total_free_disc_space);
					tmp.setDate_and_time(timestamp);
					
					if(confTotalFreeDiscSpaceRepository.isPresent(drive, id_client)==null)
					{
						confTotalFreeDiscSpaceRepository.save(tmp);
					}
					else
					{

						confTotalFreeDiscSpaceRepository.updateDisk(drive, id_client, total_size, total_free_disc_space, perc_free_disc_space);
					}
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
		
		try
		{
			id_client=Integer.parseInt((String) body.get("MyID"));
			MyApplicationsLogs=(List<VisualizzazioneEventi>)body.get("MyApplicationLogs");
			MyHardwareLogs=(List<VisualizzazioneEventi>)body.get("MyHardwareLogs");
			MySystemLogs=(List<VisualizzazioneEventi>)body.get("MySystemLogs");
			MySecurityLogs=(List<VisualizzazioneEventi>)body.get("MySecurityLogs");
			
			System.out.println(MyApplicationsLogs.get(0));
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
				//Stopped=1, StartPending=2, StopPending=3, Running=4, ContinuePending=5, PausePending=6, Paused=7
				response.setN_totali((confWindowsServicesRepository.getTotServizi(id_client)));
				response.setN_running(confWindowsServicesRepository.getNumStato(id_client, 4));
				response.setN_stopped(confWindowsServicesRepository.getNumStato(id_client, 1));
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
				System.out.println(eventi.size());
				int Npartenza = n*(slot-1);
				int Narrivo = (n*slot)-1;
				System.out.println(Npartenza);
				System.out.println(Narrivo);
				if(Narrivo > eventi.size())
					Narrivo = eventi.size();
				
				for(int i=Npartenza; i< Narrivo; i++) {
					eventiSlot.add(eventi.get(i));
					System.out.println("el N: " + i + " " + eventi);
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
