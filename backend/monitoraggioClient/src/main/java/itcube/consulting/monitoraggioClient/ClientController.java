package itcube.consulting.monitoraggioClient;

import java.io.File;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import itcube.consulting.monitoraggioClient.entities.Alert;
import itcube.consulting.monitoraggioClient.entities.AlertConfigurazione;
import itcube.consulting.monitoraggioClient.entities.ConfTotalFreeDiscSpace;
import itcube.consulting.monitoraggioClient.entities.ConfWindowsServices;
import itcube.consulting.monitoraggioClient.entities.ElencoClients;
import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;
import itcube.consulting.monitoraggioClient.entities.ElencoLicenze;
import itcube.consulting.monitoraggioClient.entities.Sedi;
import itcube.consulting.monitoraggioClient.entities.TipologiaClient;
import itcube.consulting.monitoraggioClient.entities.TipologieLicenze;
import itcube.consulting.monitoraggioClient.entities.database.LicenzaShallow;
import itcube.consulting.monitoraggioClient.entities.database.ShallowClient;
import itcube.consulting.monitoraggioClient.entities.database.TipologiaClientCount;
import itcube.consulting.monitoraggioClient.entities.database.ValidToken;
import itcube.consulting.monitoraggioClient.entities.database.LicenzeDeep;
import itcube.consulting.monitoraggioClient.repositories.AlertConfigurazioneRepository;
import itcube.consulting.monitoraggioClient.repositories.ConfTotalFreeDiscSpaceRepository;
import itcube.consulting.monitoraggioClient.repositories.ConfWindowsServicesRepository;
import itcube.consulting.monitoraggioClient.repositories.ConfigRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoAlertRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoClientsRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoCompaniesRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoLicenzeRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoOperazioniRepository;
import itcube.consulting.monitoraggioClient.repositories.RealTimeRepository;
import itcube.consulting.monitoraggioClient.repositories.SediRepository;
import itcube.consulting.monitoraggioClient.repositories.TipologieClientRepository;
import itcube.consulting.monitoraggioClient.repositories.TipologieLicenzeRepository;
import itcube.consulting.monitoraggioClient.response.GeneralResponse;
import itcube.consulting.monitoraggioClient.response.GetAllClientOfTierResponse;
import itcube.consulting.monitoraggioClient.response.GetClientTypesResponse;
import itcube.consulting.monitoraggioClient.response.GetTypesFromClientResponse;
import itcube.consulting.monitoraggioClient.response.ResponseLogin;
import itcube.consulting.monitoraggioClient.response.ShallowClientsResponse;
import itcube.consulting.monitoraggioClient.response.ConfTotalFreeDiscSpaceResponse.ConfTotalFreeDiscSpaceDTO;
import itcube.consulting.monitoraggioClient.response.LicenzeShallowResponse;
import itcube.consulting.monitoraggioClient.response.LicenzeDeepResponse;
import itcube.consulting.monitoraggioClient.response.DeepClientResponse;
import itcube.consulting.monitoraggioClient.services.Services;
import itcube.consulting.monitoraggioClient.response.AgentResponse;
import itcube.consulting.monitoraggioClient.response.ClientHistoryResponse;
import itcube.consulting.monitoraggioClient.response.ClientHistoryResponseSub;
import itcube.consulting.monitoraggioClient.response.ClientLicenseListResponse;
import itcube.consulting.monitoraggioClient.response.ClientOverviewResponseSub;
import itcube.consulting.monitoraggioClient.response.ConfTotalFreeDiscSpaceResponse;
import net.minidev.json.JSONArray;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.jni.Local;

@RestController
@RequestMapping(path="/be/main")
public class ClientController {
	
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
	private SediRepository sediRepository;
	
	@Autowired
	private ElencoAlertRepository elencoAlertRepository;
	
	@Autowired
	private AlertConfigurazioneRepository alertConfigurazioneRepository;
	
	@PostMapping(path="/shallowClients",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> shallowClientsList (@RequestBody Map<String,Object> body) {
		GeneralResponse generalResponse=new GeneralResponse();
		ShallowClientsResponse shallowClientsResponse = new ShallowClientsResponse();
		ValidToken validToken=new ValidToken();
		int id_company;
		String token;
		
		try
		{
			id_company=Integer.parseInt((String)body.get("id_company"));
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				
				ElencoCompanies company = elencoCompaniesRepository.getInfoCompany(id_company);
				List<ElencoClients> elencoClients = elencoClientsRepository.getElencoClients(company);
				
				shallowClientsResponse.setShallowClients(ShallowClient.getShallowClients(elencoClients));
				
				String newToken=Services.checkThreshold(id_company, token);
				
				shallowClientsResponse.setMessage("Operazione effettuata con successo");
				shallowClientsResponse.setMessageCode(0);
				shallowClientsResponse.setToken(newToken);
				
				return ResponseEntity.ok(shallowClientsResponse); 
				
			}
			generalResponse.setMessage("Autenticazione fallita");
			generalResponse.setMessageCode(-2);
			return ResponseEntity.badRequest().body(generalResponse);
		}
		catch (Exception e)
		{
			generalResponse.setMessage(e.getMessage());
			generalResponse.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(generalResponse);
		}
	}
	
	@PostMapping(path="/deepClient",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> deepClient (@RequestBody Map<String,Object> body) {
		
		GeneralResponse generalResponse=new GeneralResponse();
		DeepClientResponse deepClientResponse=new DeepClientResponse();
		ValidToken validToken=new ValidToken();
		String token;
		int id_company;
		int id_client;
		List<String> codiciLicenze=new ArrayList<String>();
		List<Integer> classiLicenze=new ArrayList<Integer>();
		
		try 
		{
			id_company=Integer.parseInt((String)body.get("id_company"));
			id_client=Integer.parseInt((String)body.get("id_client"));
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				ElencoClients deepClient = elencoClientsRepository.getDeepClient(id_client);
				
				deepClientResponse.setNome(deepClient.getNome());
				deepClientResponse.setNome_tipologia(deepClient.getTipologiaClient().getNome_tipologia());
				deepClientResponse.setMac_address(deepClient.getMac_address());
				deepClientResponse.setSede(deepClient.getSede());
				for(ElencoLicenze i : deepClient.getElencoLicenze())
				{
					codiciLicenze.add(i.getCodice());
					classiLicenze.add(elencoLicenzeRepository.getClasseLicenza(i.getTipologieLicenze().getId()));
				}
				deepClientResponse.setDescrizione(deepClient.getDescrizione());
				deepClientResponse.setCodice_licenza(codiciLicenze);
				deepClientResponse.setClasse_licenza(classiLicenze);
				
				//deepClientResponse.setServizi_problemi(confWindowsServicesRepository.countTipoAlert(1));
				//deepClientResponse.setServizi_warning(confWindowsServicesRepository.countTipoAlert(2));
				//deepClientResponse.setServizi_attivi(confWindowsServicesRepository.countAttivo(true));
				//deepClientResponse.setServizi_esecuzione(confWindowsServicesRepository.countEsecuzione(true));
				
				String newToken=Services.checkThreshold(id_company, token);

				deepClientResponse.setMessage("Operazione effettuata con successo");
				deepClientResponse.setMessageCode(0);
				deepClientResponse.setToken(newToken);
				
				return ResponseEntity.ok(deepClientResponse); 
			}
			generalResponse.setMessage("Autenticazione fallita");
			generalResponse.setMessageCode(-2);
			return ResponseEntity.ok(generalResponse);
		}
		catch (Exception e)
		{
			generalResponse.setMessage(e.getMessage());
			generalResponse.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(generalResponse);
		}
	}
	
	@PostMapping(path="/clientLicenseList",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> getClientLicenseList(@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		int id_company;
		String token;
		ElencoCompanies company;
		List<ElencoClients> elencoClients=new ArrayList<>();
		List<TipologieLicenze> tipologieLicenze = new ArrayList<>();
		ClientLicenseListResponse response=new ClientLicenseListResponse();
		
		try
		{
			id_company=(Integer)body.get("id_company");
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				
				company=elencoCompaniesRepository.getInfoCompany(id_company);
				elencoClients=elencoClientsRepository.getElencoClients(company);
				tipologieLicenze = tipologieLicenzeRepository.getLicenze();
				HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
				
				for(TipologieLicenze i : tipologieLicenze) {
					int count = 0;
					for (ElencoClients client : elencoClients) {
						if (client.hasLicenza(i))
							count++;
					}
					map.put(i.getClasse(),count);
				}
				
				String newToken=Services.checkThreshold(id_company, token);

				response.setMessage("Operazione avvenuta con successo");
				response.setMessageCode(0);
				response.setToken(newToken);
				response.setSedi(map);
				
				return ResponseEntity.ok(response);
			}
			else
			{
				generalResponse.setMessage("Autenticazione fallita ");
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
	
	
	@PostMapping(path="/getClientTypes",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> getClientTypes(@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		int id_company;
		String token;
		List<TipologiaClientCount> categories_list=new ArrayList<>();
		List<ElencoClients> elencoClients=new ArrayList<>();
		ElencoCompanies company;
		String nome;
		int num;
		GetClientTypesResponse response=new GetClientTypesResponse();
		List<TipologiaClient> types; 
		int idx;
		
		try 
		{
			id_company=(Integer)body.get("id_company");
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				company=elencoCompaniesRepository.getInfoCompany(id_company);
				elencoClients=elencoClientsRepository.getElencoClients(company);
				types = tipologieClientRepository.getTipologie();

				for(TipologiaClient type: types) {
					idx=0;
					for(ElencoClients client: elencoClients) {
						if(client.getTipologiaClient().getNome_tipologia() == type.getNome_tipologia()) {
							idx++;
						}
					}
					categories_list.add(new TipologiaClientCount(type.getNome_tipologia(),idx));
				}
				

//				types.forEach(type -> map.put(type.getNome_tipologia(), 0));
//
//				for(ElencoClients client:elencoClients)
//				{
//					nome=client.getTipologiaClient().getNome_tipologia();
//					num=map.get(nome)+1;
//					map.put(nome, num);
//				}
				
				String newToken=Services.checkThreshold(id_company, token);

				response.setCategories(categories_list);
				response.setMessage("Operazione avvenuta con successo");
				response.setMessageCode(0);
				response.setToken(newToken);
				return ResponseEntity.ok(response);
			}
			else
			{
				generalResponse.setMessage("Autenticazione fallita ");
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
	
	
	@PostMapping(path="/newClient",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> newClient(@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ElencoLicenze licenza;
		Set<ElencoLicenze> el=new HashSet<ElencoLicenze>();
		try
		{
			//token agent
			String mac_address=(String)body.get("mac_address");
			String nome=(String)body.get("nome");
			String licenza_in_uso=(String)body.get("licenza_in_uso");
			String tipologia_client=(String)body.get("tipologia_client");
			String sede=(String)body.get("sede");
				
			ElencoClients newClient=new ElencoClients();
			newClient.setMac_address(mac_address);
			newClient.setSede(sede);
			newClient.setNome(nome);
			newClient.setTipologiaClient(tipologieClientRepository.getSpecificType(tipologia_client));
			
			licenza=elencoLicenzeRepository.getLicenza(licenza_in_uso);

			if(licenza!=null)
			{
				el.add(licenza);
				
				newClient.setElencoCompanies(licenza.getElencoCompanies());
				newClient.setElencoLicenze(el);
				
				System.out.println("Stampa prima del save:");
				for(ElencoLicenze i: newClient.getElencoLicenze())
				{
					System.out.println(i.getId());
					System.out.println(i.getCodice());
				}
				
				elencoClientsRepository.save(newClient);
				
				System.out.println("Stampa dopo il save:");
				for(ElencoLicenze i: elencoClientsRepository.getDeepClient(newClient.getId()).getElencoLicenze())
				{
					System.out.println(i.getId());
					System.out.println(i.getCodice());
				}
				
				generalResponse.setMessage("Client registrato con successo");
				generalResponse.setMessageCode(0);
				return ResponseEntity.ok(generalResponse);
			}
			else
			{
				generalResponse.setMessage("Licenza inesistente");
				generalResponse.setMessageCode(-2);
				return ResponseEntity.badRequest().body(generalResponse);
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
	
	@PostMapping(path="/pingToClient",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> pingToClient(@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		String token;
		Integer id_client;
		ElencoClients client;
		Integer id_company;
		
		try
		{
			id_company=(Integer)body.get("id_company");
			id_client=Integer.parseInt((String)body.get("id_client"));
			token=(String)body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid()) {
				
				client = elencoClientsRepository.getDeepClient(id_client);
				
				//INOLTRO RICHIESTA ALL'AGENT
				/*
				 * 
				 * ATTENDO LA RISPOSTA DELL'AGENT
				 * 
				 * if (response == OK)
				 */
				
				String newToken=Services.checkThreshold(id_company, token);

				generalResponse.setMessage("L'agent ha risposto correttamente");
				generalResponse.setMessageCode(0);
				generalResponse.setToken(newToken);			
				
				/* else */
				
				newToken=Services.checkThreshold(id_company, token);

				generalResponse.setMessage("L'agent non ha risposto");
				generalResponse.setMessageCode(-1);
				generalResponse.setToken(newToken);	
	
				return ResponseEntity.ok(generalResponse);
			}
			else
			{
				generalResponse.setMessage("Autenticazione fallita ");
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
	
	@PostMapping(path="/MyInfo",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<AgentResponse> MyInfo(@RequestBody Map<String,Object> body)
	{
		AgentResponse response=new AgentResponse();
		
		try
		{
			//MyID, LicenseKey, MachineName, MachineLabel, Type, MACAddress): MyID, Message, MessageCode
			Integer id_client=Integer.parseInt((String)body.get("MyID"));
			String chiave=(String)body.get("LicenseKey");
			String nome=(String)body.get("MachineName");
			String descrizione=(String)body.get("MachineLabel");
			int tipologiaClient=Integer.parseInt((String)body.get("Type"));
			String mac_address=(String)body.get("MACAddress");
			
			if(id_client==0)
			{
				if(elencoClientsRepository.getIdFromInfo(nome, mac_address) == null) {
					//salva nel db
					ElencoClients newClient=new ElencoClients();
					newClient.setNome(nome);
					newClient.setDescrizione(descrizione);
					
					TipologiaClient tipo=new TipologiaClient();
					tipo=tipologieClientRepository.getNomeFromNum(tipologiaClient);
					System.out.println(tipo);
					newClient.setTipologiaClient(tipo);
					newClient.setMac_address(mac_address);
					//id_company
					Integer id_company = elencoCompaniesRepository.getIdCompanyFromChiave(chiave);
					Integer idSenzaSede = elencoCompaniesRepository.getSenzaSedeOfCompany(id_company);
					
					if(idSenzaSede != null) {
						newClient.setSede(idSenzaSede.toString());
					} else {
						Sedi newSede = sediRepository.save(new Sedi("Senza sede", id_company));
						newClient.setSede( ((Integer) newSede.getId()).toString() );
					}
					
					if(id_company!=null)
					{
						ElencoCompanies company=new ElencoCompanies();
						company=elencoCompaniesRepository.getInfoCompany(id_company);
						newClient.setElencoCompanies(company);
						//licenza in uso
						Set<ElencoLicenze> elencoLicenze=new HashSet<ElencoLicenze>();
						
						ElencoLicenze licenza = elencoLicenzeRepository.getLicenze(company).get(0);
						elencoLicenze.add(licenza);
						newClient.setElencoLicenze(elencoLicenze);
						
						//SetAlertMonitoraggio('WINDOWS_SERVICES',1)
						//SetAlertMonitoraggio('WINDOWS_EVENTS',1) 
						//SetAlertMonitoraggio('DRIVES',1)
						
//						System.out.println(newClient.getElencoLicenze().get(0).getId());
						//sede
						
						ElencoClients client = elencoClientsRepository.save(newClient);
						
						alertConfigurazioneRepository.save(new AlertConfigurazione("WINDOWS_SERVICES",client,true,2));
						alertConfigurazioneRepository.save(new AlertConfigurazione("WINDOWS_EVENTS",client,true,3));
						alertConfigurazioneRepository.save(new AlertConfigurazione("DRIVES",client,true,1));
						
						response.setMyID(elencoClientsRepository.getIdFromInfo(nome, mac_address));
						response.setMessage("OK");
						response.setMessageCode(0);
						return ResponseEntity.ok(response);
					}
					else
					{
						response.setMyID(null);
						response.setMessage("Company non registrata");
						response.setMessageCode(-1);
						return ResponseEntity.badRequest().body(response);
					}
				} else {
					response.setMyID(elencoClientsRepository.getIdFromInfo(nome, mac_address));
					response.setMessage("OK");
					response.setMessageCode(0);
					return ResponseEntity.ok(response);
				}
			}
			else
			{
				//ping --> return
				response.setMyID(elencoClientsRepository.getIdFromInfo(nome, mac_address));
				response.setMessage("OK");
				response.setMessageCode(0);
				return ResponseEntity.ok(response);
			}
		}
		catch(Exception e)
		{
			response.setMyID(null);
			response.setMessage("KO");
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	/*@PostMapping(path="/modificaSedeClient",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> modificaSedeClient(@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		Integer id_client;
		Integer id_company;
		String token;
		String nuova_sede;
		String vecchia_sede;
		
		try {
			id_client = Integer.parseInt((String) body.get("id_client"));
			token = (String)body.get("token");
			nuova_sede = (String) body.get("nuova_sede");
			vecchia_sede = (String) body.get("vecchia_sede");
			id_company =Integer.parseInt((String) body.get("id_company"));
			validToken = Services.checkToken(id_company, token);
			
			
			if(validToken.isValid()) {
				if(id_client != -1)
				{
					elencoClientsRepository.modificaSingolaSedeClient(id_client, nuova_sede);
				} else {
					elencoClientsRepository.modificaAllVecchieSediClient(id_company, nuova_sede, vecchia_sede);
				}
			} 
			else
			{
				generalResponse.setMessage("Autenticazione fallita ");
				generalResponse.setMessageCode(-2);
				return ResponseEntity.badRequest().body(generalResponse);
			}

			generalResponse.setToken(validToken.getToken());
			generalResponse.setMessage("OK");
			generalResponse.setMessageCode(0);
			return ResponseEntity.ok(generalResponse);
			
		} catch (Exception e) {
			generalResponse.setMessage(e.getMessage());
			generalResponse.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(generalResponse);
		}
	}*/
	
	@PostMapping(path="/getDrives",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<ConfTotalFreeDiscSpaceResponse> getDrives(@RequestBody Map<String,Object> body)
	{
		ValidToken validToken=new ValidToken();
		Integer id_client;
		Integer id_company;
		String token;
		ConfTotalFreeDiscSpaceResponse response = new ConfTotalFreeDiscSpaceResponse(); 
		
		try {
			id_client = Integer.parseInt((String) body.get("id_client"));
			token = (String)body.get("token");
			id_company=elencoClientsRepository.getIdCompany(id_client);
			validToken = Services.checkToken(id_company, token);
			
			if(validToken.isValid()) {
				List<ConfTotalFreeDiscSpace> freeDiscSpaceList = confTotalFreeDiscSpaceRepository.getDrives(id_client);
				List<ConfTotalFreeDiscSpaceDTO> freeDiscSpaceDTOList = new ArrayList<ConfTotalFreeDiscSpaceDTO>();
				
				for(ConfTotalFreeDiscSpace freeDiscSpace : freeDiscSpaceList) {
					ConfTotalFreeDiscSpaceDTO freeDiscSpaceDTO = new ConfTotalFreeDiscSpaceDTO();
					freeDiscSpaceDTO.setDriveLabel(freeDiscSpace.getDrive());
					freeDiscSpaceDTO.setLastUpdate(freeDiscSpace.getDate_and_time());
					freeDiscSpaceDTO.setOccupiedSpace( String.valueOf( freeDiscSpace.getTotal_free_disc_space() ) );
					freeDiscSpaceDTO.setTotalSpace( String.valueOf( freeDiscSpace.getTotal_size() ) );
					freeDiscSpaceDTOList.add(freeDiscSpaceDTO);
				}
				
				response.setDrives(freeDiscSpaceDTOList);
				
			} else {
				response.setMessage("Autenticazione fallita ");
				response.setMessageCode(-2);
				return ResponseEntity.ok(response);
			}
			
			response.setToken(validToken.getToken());
			response.setMessage("OK");
			response.setMessageCode(0);
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	
	@PostMapping(path="/getClientHistory",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<ClientHistoryResponse> getClientHistory(@RequestBody Map<String,Object> body)
	{
		ValidToken validToken=new ValidToken();
		Integer id_client;
		Integer id_company;
		String token;
		ClientHistoryResponse response = new ClientHistoryResponse();
		LocalDate date;
		List<Alert> alerts = new ArrayList<Alert>();
		ClientHistoryResponseSub responseItem;
		List<ClientHistoryResponseSub> responseList = new ArrayList<ClientHistoryResponseSub>();
		int days;
		
		try {
			id_client = Integer.parseInt((String) body.get("id_client"));
			token = (String)body.get("token");
			id_company=elencoClientsRepository.getIdCompany(id_client);
			validToken = Services.checkToken(id_company, token);
			days = Integer.parseInt((String) body.get("slot"));
			
			if(validToken.isValid()) {
				
				LocalDate today = LocalDate.now();
			
				alerts = elencoAlertRepository.getAlertOfDay(id_client, 1);
				
				for(int i=9; i>=0; i--) {
					date = today.minusDays(i);
					int n_errori= 0; 
					int n_warnings= 0;
					for(Alert alert: alerts) {
						if(alert.getDate_and_time_alert().toLocalDate().equals(date)) {
							if(alert.getTipo().equals("ERROR")) 
								n_errori++;
							else	
								n_warnings++;
						}
					}
						
					responseItem = new ClientHistoryResponseSub(date, n_errori, n_warnings);
					responseList.add(responseItem);
				}
				
				response.setHistory(responseList);
				response.setLast_update(LocalDateTime.now().withNano(0).toString().replace("T", " "));
				
			} else {
				response.setMessage("Autenticazione fallita ");
				response.setMessageCode(-2);
				return ResponseEntity.ok(response);
			}
			
			response.setToken(validToken.getToken());
			response.setMessage("OK");
			response.setMessageCode(0);
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping(path="/getCompanyHistory",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<ClientHistoryResponse> getCompanyHistory(@RequestBody Map<String,Object> body)
	{
		ValidToken validToken=new ValidToken();
		Integer id_company;
		String token;
		ClientHistoryResponse response = new ClientHistoryResponse();
		LocalDate date;
		List<Alert> alerts = new ArrayList<Alert>();
		ClientHistoryResponseSub responseItem;
		List<ClientHistoryResponseSub> responseList = new ArrayList<ClientHistoryResponseSub>();
		int days;
		
		try {
			token = (String)body.get("token");
			id_company=Integer.parseInt( (String) body.get("id_company"));
			validToken = Services.checkToken(id_company, token);
			days = Integer.parseInt((String) body.get("slot"));
			
			if(validToken.isValid()) {
				
				LocalDate today = LocalDate.now();
			
				alerts = elencoAlertRepository.getAlertOfCompanyOfDay(id_company, 1);
				
				for(int i=9; i>=0; i--) {
					date = today.minusDays(i);
					int n_client_errori= 0; 
					int n_client_warnings= 0;
					List<Integer> list_id_client_warning = new ArrayList<Integer>();
					List<Integer> list_id_client_error = new ArrayList<Integer>();
					for(Alert alert: alerts) {
						if(alert.getDate_and_time_alert().toLocalDate().equals(date)) {
							if(alert.getTipo().equals("ERROR") && !list_id_client_error.contains(alert.getId_client())) {
								n_client_errori++;
								list_id_client_error.add(alert.getId_client());
							}
								
							if(alert.getTipo().equals("WARNING") && !list_id_client_warning.contains(alert.getId_client())) {
								n_client_warnings++;
								list_id_client_warning.add(alert.getId_client());
							}
						}
					}
						
					responseItem = new ClientHistoryResponseSub(date, n_client_errori, n_client_warnings);
					responseList.add(responseItem);
				}
			
				response.setHistory(responseList);
				response.setLast_update(LocalDateTime.now().withNano(0).toString().replace("T", " "));
				
			} else {
				response.setMessage("Autenticazione fallita ");
				response.setMessageCode(-2);
				return ResponseEntity.ok(response);
			}
			
			response.setToken(validToken.getToken());
			response.setMessage("OK");
			response.setMessageCode(0);
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping(path="/addTier",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> addTier(@RequestBody Map<String,Object> body)
	{
		ValidToken validToken=new ValidToken();
		Integer id_company;
		String token;
		GeneralResponse response = new GeneralResponse();
		String tier_name;
		
		try {
			token = (String)body.get("token");
			id_company=Integer.parseInt( (String) body.get("id_company"));
			validToken = Services.checkToken(id_company, token);
			tier_name = (String)body.get("tier_name");
			
			if(validToken.isValid()) {
				
				tipologieClientRepository.addTier(tier_name, id_company);
				
			} else {
				response.setMessage("Autenticazione fallita ");
				response.setMessageCode(-2);
				return ResponseEntity.ok(response);
			}
			
			response.setMessage("OK");
			response.setMessageCode(0);
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping(path="/removeTier",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> removeTier(@RequestBody Map<String,Object> body)
	{
		ValidToken validToken=new ValidToken();
		Integer id_company;
		String token;
		GeneralResponse response = new GeneralResponse();
		String tier_name;

		try {
			token = (String)body.get("token");
			id_company=Integer.parseInt( (String) body.get("id_company"));
			validToken = Services.checkToken(id_company, token);
			tier_name = (String)body.get("tier_name");
			
			if(validToken.isValid()) {
				
				if(tipologieClientRepository.getNumOfClientFromType(tier_name, id_company) > 0) {
					response.setMessageCode(-3);
					response.setMessage("Impossibile eliminare");
					return ResponseEntity.ok(response);
				} else {
					tipologieClientRepository.removeTier(tier_name, id_company);
					response.setMessageCode(0);
					response.setMessage("Tier rimosso correttamente");
					return ResponseEntity.ok(response);
				}
				
			} else {
				response.setMessage("Autenticazione fallita ");
				response.setMessageCode(-2);
				return ResponseEntity.ok(response);
			}
			
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping(path="/getTierFromClient",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> getTierFromClient(@RequestBody Map<String,Object> body)
	{
		ValidToken validToken=new ValidToken();
		Integer id_client;
		Integer id_company;
		String token;
		GetTypesFromClientResponse response = new GetTypesFromClientResponse();
		String tier_name;

		try {
			token = (String)body.get("token");
			id_client=Integer.parseInt( (String) body.get("id_client"));
			id_company = elencoClientsRepository.getIdCompany(id_client);
			validToken = Services.checkToken(id_company, token);
			
			if(validToken.isValid()) {
				
				tier_name = tipologieClientRepository.getTypeFromClient(id_client);
				
				response.setMessage("OK");
				response.setMessageCode(0);
				response.setNome_tipologia(tier_name);
				return ResponseEntity.ok(response);
				
			} else {
				response.setMessage("Autenticazione fallita ");
				response.setMessageCode(-2);
				return ResponseEntity.ok(response);
			}
			
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping(path="/getClientOfTier",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GetAllClientOfTierResponse> getClientOfTier(@RequestBody Map<String,Object> body)
	{
		ValidToken validToken=new ValidToken();
		Integer id_company;
		String token;
		GetAllClientOfTierResponse response = new GetAllClientOfTierResponse();
		int tier_id;
		String tier_name = null;
		List<ElencoClients> elencoClients = new ArrayList<ElencoClients>();

		try {
			
			token = (String)body.get("token");
			id_company=Integer.parseInt( (String) body.get("id_company"));
			validToken = Services.checkToken(id_company, token);
			tier_name= (String) body.get("tier_name");

			if(validToken.isValid()) {
				
				tier_id = tipologieClientRepository.getTypeIdFromClient(id_company, tier_name);
				elencoClients = elencoClientsRepository.getClientFromTier(id_company,tier_id);
				System.out.println(elencoClients);
				
				response.setClientsOfTier(elencoClients);
				response.setMessage("OK");
				response.setMessageCode(0);
				return ResponseEntity.ok(response);
				
			} else {
				response.setMessage("Autenticazione fallita ");
				response.setMessageCode(-2);
				return ResponseEntity.ok(response);
			}
			
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping(path="/updateTier",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> updateTier(@RequestBody Map<String,Object> body)
	{
		ValidToken validToken=new ValidToken();
		List<String> ids_client = new ArrayList<String>();
		String token;
		GeneralResponse response = new GeneralResponse();
		int tier_id;
		String tier_name = null;
		Integer id_company;

		try {
			
			token = (String)body.get("token");
			ids_client= (List<String>) body.get("ids_client");
			if (ids_client.size() > 0) {
				id_company = elencoClientsRepository.getIdCompany(Integer.parseInt(ids_client.get(0)));
				validToken = Services.checkToken(id_company, token);
				tier_name= (String) body.get("tier_name");
	
				if(validToken.isValid()) {
					tier_id = tipologieClientRepository.getTypeIdFromClient(id_company, tier_name);
					ids_client.forEach(client -> {
						int client_int;
						client_int = Integer.parseInt(client);
						tipologieClientRepository.updateTier(tier_id, client_int);
					});
	
					response.setMessage("OK");
					response.setMessageCode(0);
					return ResponseEntity.ok(response);
					
				} else {
					response.setMessage("Autenticazione fallita ");
					response.setMessageCode(-2);
					return ResponseEntity.ok(response);
				}
			} else {
				response.setMessage("Selezionare almeno un elemento");
				response.setMessageCode(-3);
				return ResponseEntity.ok(response);
			}
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
}
