package itcube.consulting.monitoraggioClient;

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
		
		try
		{
			id_client=(Integer)body.get("id_client");
			servizi=(List<ConfWindowsServices>)body.get("servizi");
			if(servizi.size()!=0)
			{
				
				for(ConfWindowsServices i:servizi)
				{
					i.setDate_and_time(Services.getCurrentDate());
					confWindowsServicesRepository.save(i);
				}
				
				/*for (Object servizio : servizi) {
					String[] words = ((String)servizio).split(",");

					for (int i = 0; i < words.length - 1; i++) {
						String word = words[i];
						System.out.println("Word: " + word);

						confWindowsServices.setNomeServizio(word);
						nomeServizio = confWindowsServices.getNomeServizio();
						System.out.println("\nNome servizio: " + nomeServizio);
						
						confWindowsServices.setStato(0);
						stato = confWindowsServices.getStato();
						System.out.println("Stato: " + stato);

						confWindowsServicesRepository.insertWindowsService(id, nomeServizio, stato, idClient);
						System.out.println("\nSALVATO/I!!!\n");

						System.out.println("Nome servizio: " + nomeServizio);
						System.out.println("Stato: " + stato);
						System.out.println("Id client: " + idClient);

						System.out.println("\n--------------------------------------------------\n");
					}
				}*/
				
				System.out.println("Prova 1");
				id_client=servizi.get(0).getElencoClients().getId();
				System.out.println("Prova 2");
				monitoraggio=monitoraggioRepository.getServiziClient(id_client);
				System.out.println("Prova 3");
				if(monitoraggio!=null)
				{
					for(ConfWindowsServices i: servizi)
					{
						if(!monitoraggio.contains(i.toMonitoraggio(i, confWindowsServicesRepository)))
						{
							monitoraggioRepository.save(i.toMonitoraggio(i, confWindowsServicesRepository));
						}
					}
				}
				else
				{
					for(ConfWindowsServices i: servizi)
					{
						monitoraggioRepository.save(i.toMonitoraggio(i, confWindowsServicesRepository));
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
		
		try
		{
			id_client=(Integer)body.get("id_client");
			eventi=(List<VisualizzazioneEventi>)body.get("eventi");
			if(eventi!=null)
			{
				for(VisualizzazioneEventi i:eventi)
				{
					i.setDate_and_time(Services.getCurrentDate());
					visualizzazioneEventiRepository.save(i);
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
				
				response.setConfWindowsSerives(serviziMonitorati);
				
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
}
