package itcube.consulting.monitoraggioClient;

import java.io.File;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
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
import itcube.consulting.monitoraggioClient.entities.ConfTotalFreeDiscSpace;
import itcube.consulting.monitoraggioClient.entities.ConfWindowsServices;
import itcube.consulting.monitoraggioClient.entities.ElencoClients;
import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;
import itcube.consulting.monitoraggioClient.entities.ElencoLicenze;
import itcube.consulting.monitoraggioClient.entities.TipologieLicenze;
import itcube.consulting.monitoraggioClient.entities.database.LicenzaShallow;
import itcube.consulting.monitoraggioClient.entities.database.OperationsPerClient;
import itcube.consulting.monitoraggioClient.entities.database.ShallowClient;
import itcube.consulting.monitoraggioClient.entities.database.ValidToken;
import itcube.consulting.monitoraggioClient.entities.database.LicenzeDeep;
import itcube.consulting.monitoraggioClient.repositories.ConfTotalFreeDiscSpaceRepository;
import itcube.consulting.monitoraggioClient.repositories.ConfWindowsServicesRepository;
import itcube.consulting.monitoraggioClient.repositories.ConfigRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoClientsRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoCompaniesRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoLicenzeRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoOperazioniRepository;
import itcube.consulting.monitoraggioClient.repositories.RealTimeRepository;
import itcube.consulting.monitoraggioClient.repositories.TipologieClientRepository;
import itcube.consulting.monitoraggioClient.repositories.TipologieLicenzeRepository;
import itcube.consulting.monitoraggioClient.response.GeneralResponse;
import itcube.consulting.monitoraggioClient.response.GetClientTypesResponse;
import itcube.consulting.monitoraggioClient.response.ResponseLogin;
import itcube.consulting.monitoraggioClient.response.ResponseOperationsPerClient;
import itcube.consulting.monitoraggioClient.response.ShallowClientsResponse;
import itcube.consulting.monitoraggioClient.response.LicenzeShallowResponse;
import itcube.consulting.monitoraggioClient.response.LicenzeDeepResponse;
import itcube.consulting.monitoraggioClient.response.DeepClientResponse;
import itcube.consulting.monitoraggioClient.services.Services;
import itcube.consulting.monitoraggioClient.response.ClientLicenseListResponse;
import net.minidev.json.JSONArray;
import org.apache.commons.lang3.RandomStringUtils;

@RestController
@RequestMapping(path="/be/main")
public class MainController {
	
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
	
	//http://localhost:8080/be/main/registrazione
	@PostMapping(path="/registrazione",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> Registrazione(@RequestBody Map<String,Object> body){
		GeneralResponse responseRegistrazione=new GeneralResponse();
		String email;
		String password;
		String email_alert;
		String ragione_sociale;
		List<String> elencoCompanies;
		ElencoCompanies company;
		ElencoLicenze elencoLicenze;
		String codice;

		try {
			email=body.get("email").toString();
			password=body.get("password").toString();
			email_alert=body.get("email_alert").toString();
			ragione_sociale=body.get("ragione_sociale").toString();
			
			System.out.println(email);
			System.out.println(password);
			System.out.println(email_alert);
			System.out.println(ragione_sociale);
			
			elencoCompanies=elencoCompaniesRepository.getCompanies(email, ragione_sociale);
	
			if(elencoCompanies.isEmpty())
			{
				//salva la company
				company=new ElencoCompanies();
				company.setEmail(email);
				company.setPassword(password);
				company.setEmail_alert(email_alert);
				company.setRagione_sociale(ragione_sociale);
				
				elencoCompaniesRepository.save(company);
				
				//salva la licenza --> FIX
				do
				{
					codice=Services.getLicenseCode();
				}while(elencoLicenzeRepository.countCodes(codice)!=0);
				
				elencoLicenze=new ElencoLicenze();
				elencoLicenze.setCodice(codice);
				elencoLicenze.setElencoClients(null);
				elencoLicenze.setTipologieLicenze(tipologieLicenzeRepository.getLicenza("1"));
				elencoLicenze.setElencoCompanies(company);
				elencoLicenze.setScadenza(new Date(System.currentTimeMillis()+500000));
				
				elencoLicenzeRepository.save(elencoLicenze);
				
				responseRegistrazione.setMessage("Registrazione avvenuta con successo");
				responseRegistrazione.setMessageCode(0);
				return ResponseEntity.ok(responseRegistrazione);
			}
			else
			{
				responseRegistrazione.setMessage("Utente già registrato");
				responseRegistrazione.setMessageCode(1);
				return ResponseEntity.ok(responseRegistrazione);
			}
		}
		catch (Exception e) {
			responseRegistrazione.setMessage(e.getMessage());
			responseRegistrazione.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(responseRegistrazione);
		}
	}
	
	//http://localhost:8080/be/main/login
		@PostMapping(path="/login",produces=MediaType.APPLICATION_JSON_VALUE)
		@CrossOrigin
		public ResponseEntity<GeneralResponse> Login(@RequestBody Map<String,Object> body){
			ResponseLogin responseLogin=new ResponseLogin();
			GeneralResponse generalResponse=new GeneralResponse();;
			String email;
			String password;
			int numCompany;
			ElencoCompanies company;
			String token;
			List<ElencoClients> elencoClients;
			
			try {

				email=body.get("email").toString();
				password=body.get("password").toString();
				
				numCompany=elencoCompaniesRepository.Login(email, password);
				
		
				if(numCompany<1)
				{
					generalResponse.setMessage("Password sbagliata o utente non presente");
					generalResponse.setMessageCode(1);
					System.out.println(Services.getCurrentDate()+" /login FAILED");
					return ResponseEntity.ok(generalResponse);
				}
				else
				{
					company=elencoCompaniesRepository.getInfoCompany(email);
					elencoClients=elencoClientsRepository.getElencoClients(company);
					
					String _tokenCompany=Services.tokenCompany(company.getId());
					if(_tokenCompany==null)
						token = Services.getJWTToken(company.getRagione_sociale());
					else
						token=_tokenCompany;
					
					Services.putToken(company.getId(),token);

					responseLogin.setMessage("Login avvenuto con successo");
					responseLogin.setMessageCode(0);
					responseLogin.setRagione_sociale(company.getRagione_sociale());
					responseLogin.setId_company(company.getId());
					responseLogin.setEmailNotify(company.getEmail_alert());
					responseLogin.setToken(token);
					System.out.println(Services.getCurrentDate()+" /login SUCCESS "+email);
					return ResponseEntity.ok(responseLogin);
				}
			}
			catch (Exception e) {
				generalResponse.setMessage(e.getMessage());
				generalResponse.setMessageCode(-1);
				System.out.println(Services.getCurrentDate()+"\n"+e.getMessage());
				return ResponseEntity.badRequest().body(generalResponse);
			}
		}
		
		@PostMapping(path="/operationsPerClient",produces=MediaType.APPLICATION_JSON_VALUE)
		@CrossOrigin
		public ResponseEntity<GeneralResponse> Operations(@RequestBody Map<String,Object> body) {
			
			GeneralResponse generalResponse=new GeneralResponse();
			ResponseOperationsPerClient response=new ResponseOperationsPerClient();
			OperationsPerClient operationsPerClient=new OperationsPerClient();
			int id_company;
			int id_client;
			String codice_licenza;
			String token;
			
			try
			{
				id_company=(int)body.get("id_company");
				id_client=(int)body.get("id_client");
				codice_licenza=body.get("codice_licenza").toString();
				token=body.get("token").toString();
				
				if(Services.isValid(id_company, token))
				{
					operationsPerClient= elencoClientsRepository.getOperationsPerClient(id_client); 
					
					response.setId_config(operationsPerClient.getId_config());
					response.setId_operazione(operationsPerClient.getId_operazione());
					response.setNome_operazione(operationsPerClient.getNome_operazione());
					response.setTempo(operationsPerClient.getTempo());
					
					String newToken=Services.checkThreshold(id_company, token);
					generalResponse.setMessage("Ok");
					generalResponse.setMessageCode(0);
					response.setToken(newToken);
					return ResponseEntity.ok(response);
				}
				generalResponse.setMessage("Client non autenticato");
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
		
		@PostMapping(path="/hello",produces=MediaType.APPLICATION_JSON_VALUE)
		@CrossOrigin
		public String helloWorld(@RequestBody Map<String,Object> body) {
			if(Services.isValid(Integer.parseInt((String)body.get("id_company")), (String)body.get("token")))
			{
				String newToken=Services.checkThreshold(Integer.parseInt((String)body.get("id_company")),(String)body.get("token"));
				if(newToken==null)
				{		
					return "Autenticato ("+body.get("token")+")";
				}
				else
				{
					return "Autenticato ( New Token: "+newToken+")";
				}
				
			}
			return "Autenticazione fallita ("+body.get("token")+")";
		}
				
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
					deepClientResponse.setCodice_licenza(codiciLicenze);
					deepClientResponse.setClasse_licenza(classiLicenze);
					
					deepClientResponse.setServizi_problemi(confWindowsServicesRepository.countTipoAlert(1));
					deepClientResponse.setServizi_warning(confWindowsServicesRepository.countTipoAlert(2));
					deepClientResponse.setServizi_attivi(confWindowsServicesRepository.countAttivo(true));
					deepClientResponse.setServizi_esecuzione(confWindowsServicesRepository.countEsecuzione(true));
					
					String newToken=Services.checkThreshold(id_company, token);
					
					deepClientResponse.setMessage("Operazione effettuata con successo");
					deepClientResponse.setMessageCode(0);
					deepClientResponse.setToken(newToken);
					
					return ResponseEntity.ok(deepClientResponse); 
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
		
		@PostMapping(path="/getLicenzeShallow",produces=MediaType.APPLICATION_JSON_VALUE)
		@CrossOrigin
		public ResponseEntity<GeneralResponse> getLicenzeShallow (@RequestBody Map<String,Object> body) {
			GeneralResponse generalResponse=new GeneralResponse();
			LicenzeShallowResponse licenzeShallowResponse = new LicenzeShallowResponse();
			ValidToken validToken=new ValidToken();
			Integer id_company;
			String token;
			
			try
			{
				id_company=(Integer) (body.get("id_company"));
				token=(String)body.get("token");
				validToken= Services.checkToken(id_company, token);
				
				if(validToken.isValid())
				{
					ElencoCompanies company = elencoCompaniesRepository.getInfoCompany(id_company);
					List<ElencoLicenze> licenze = elencoLicenzeRepository.getLicenze(company);
					
					licenzeShallowResponse.setLicenzeShallow(LicenzaShallow.getLicenzeShallow(licenze));
					
					String newToken=Services.checkThreshold(id_company, token);
					
					licenzeShallowResponse.setMessage("Operazione effettuata con successo");
					licenzeShallowResponse.setMessageCode(0);
					licenzeShallowResponse.setToken(newToken);
					System.out.println(Services.getCurrentDate()+" /getLicenzeShallow SUCCESS "+id_company);
					return ResponseEntity.ok(licenzeShallowResponse);
					
				}
				generalResponse.setMessage("Autenticazione fallita");
				generalResponse.setMessageCode(-2);
				System.out.println(Services.getCurrentDate()+" /getLicenzeShallow FAILED ");
				return ResponseEntity.badRequest().body(generalResponse);
			}
			catch (Exception e)
			{
				generalResponse.setMessage(e.getMessage());
				generalResponse.setMessageCode(-1);
				System.out.println(e.getMessage());
				System.out.println(Services.getCurrentDate()+" /getLicenzeShallow FAILED ");
				return ResponseEntity.badRequest().body(generalResponse);
			}
		}
		
		@PostMapping(path="/getLicenzeDeep",produces=MediaType.APPLICATION_JSON_VALUE)
		@CrossOrigin
		public ResponseEntity<GeneralResponse> getLicenzeDeep (@RequestBody Map<String,Object> body) {
			GeneralResponse generalResponse=new GeneralResponse();
			LicenzeDeepResponse licenzeDeepResponse = new LicenzeDeepResponse();
			ValidToken validToken=new ValidToken();
			Integer id_company;
			String token;
			
			try
			{
				id_company=(Integer) (body.get("id_company"));
				token=(String)body.get("token");
				validToken= Services.checkToken(id_company, token);
				
				if(validToken.isValid())
				{
					ElencoCompanies company = elencoCompaniesRepository.getInfoCompany(id_company);
					List<ElencoLicenze> licenze = elencoLicenzeRepository.getLicenze(company);
					
					licenzeDeepResponse.setElencoLicenze(LicenzeDeep.createLicenzeDeep(licenze));
					
					String newToken=Services.checkThreshold(id_company, token);
					
					licenzeDeepResponse.setMessage("Operazione effettuata con successo");
					licenzeDeepResponse.setMessageCode(0);
					licenzeDeepResponse.setToken(newToken);
					System.out.println(Services.getCurrentDate()+" /getLicenzeDeep SUCCESS "+id_company);
					return ResponseEntity.ok(licenzeDeepResponse);
				}
				generalResponse.setMessage("Autenticazione fallita");
				generalResponse.setMessageCode(-2);
				System.out.println(Services.getCurrentDate()+" /getLicenzeDeep FAILED ");
				return ResponseEntity.badRequest().body(generalResponse);
			}
			catch (Exception e)
			{
				generalResponse.setMessage(e.getMessage());
				generalResponse.setMessageCode(-1);
				System.out.println(e.getMessage());
				System.out.println(Services.getCurrentDate()+" /getLicenzeDeep FAILED ");
				return ResponseEntity.badRequest().body(generalResponse);
			}
			
		}
		
		@PostMapping(path="/editCompanyData",produces=MediaType.APPLICATION_JSON_VALUE)
		@CrossOrigin
		public ResponseEntity<GeneralResponse> editCompanyData(@RequestBody Map<String,Object> body)
		{
			GeneralResponse generalResponse=new GeneralResponse();
			ValidToken validToken=new ValidToken();
			Integer id_company;
			String token;
			String email;
			String email_alert;
			String ragione_sociale;
			ElencoCompanies company;
			
			try
			{

				id_company= (Integer) body.get("id_company");
				System.out.println(id_company);
				token=(String)body.get("token");
				validToken= Services.checkToken(id_company, token);
				email=(String)body.get("email");
				email_alert=(String)body.get("emailAlert");
				ragione_sociale=(String)body.get("ragioneSociale");
				
				if(validToken.isValid())
				{
					
					company=elencoCompaniesRepository.getInfoCompany(id_company);
					if(company!=null)
					{
						company.setEmail(email);
						company.setEmail_alert(email_alert);
						company.setRagione_sociale(ragione_sociale);
						
						String newToken=Services.checkThreshold(id_company, token);
						
						generalResponse.setMessage("Modifica company effettuata");
						generalResponse.setMessageCode(0);
						generalResponse.setToken(newToken);
						elencoCompaniesRepository.save(company);
						System.out.println(Services.getCurrentDate()+" /editCompanyData SUCCESS "+ragione_sociale);
						return ResponseEntity.ok(generalResponse);
					}
					else
					{
						generalResponse.setMessage("Company inesistente");
						generalResponse.setMessageCode(-3);
						System.out.println(Services.getCurrentDate()+" /editCompanyData FAILED "+ragione_sociale);
						return ResponseEntity.badRequest().body(generalResponse);
					}
					
				}
				generalResponse.setMessage("Autenticazione fallita");
				generalResponse.setMessageCode(-2);
				System.out.println(Services.getCurrentDate()+" /editCompanyData SUCCESS "+ragione_sociale);
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
				id_company=Integer.parseInt((String)body.get("id_company"));
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
		
		
		@PostMapping(path="/getClientTypes",produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<GeneralResponse> getClientTypes(@RequestBody Map<String,Object> body)
		{
			GeneralResponse generalResponse=new GeneralResponse();
			ValidToken validToken=new ValidToken();
			int id_company;
			String token;
			HashMap<String, Integer> map=new HashMap<String, Integer>();
			List<ElencoClients> elencoClients=new ArrayList();
			ElencoCompanies company;
			String nome;
			int num;
			GetClientTypesResponse response=new GetClientTypesResponse();
			
			try 
			{
				id_company=Integer.parseInt((String)body.get("id_company"));
				token=(String)body.get("token");
				validToken= Services.checkToken(id_company, token);
				
				if(validToken.isValid())
				{
					company=elencoCompaniesRepository.getInfoCompany(id_company);
					elencoClients=elencoClientsRepository.getElencoClients(company);
					map.put("Client", 0);
					map.put("Server", 0);
					for(ElencoClients client:elencoClients)
					{
						nome=client.getTipologiaClient().getNome_tipologia();
						num=map.get(nome)+1;
						map.put(nome, num);
					}
					
					String newToken=Services.checkThreshold(id_company, token);

					response.setCategories(map);
					response.setMessage("Operazione avvenuta con successo");
					response.setMessageCode(0);
					response.setToken(newToken);
					return ResponseEntity.ok(response);
				}
				else
				{
					generalResponse.setMessage("Autenticazione fallita ");
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
		
		
		
	
		
		
		
}