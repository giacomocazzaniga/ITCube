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
import itcube.consulting.monitoraggioClient.entities.Sedi;
import itcube.consulting.monitoraggioClient.entities.TipologieLicenze;
import itcube.consulting.monitoraggioClient.entities.database.LicenzaShallow;
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
import itcube.consulting.monitoraggioClient.repositories.SediRepository;
import itcube.consulting.monitoraggioClient.repositories.TipologieClientRepository;
import itcube.consulting.monitoraggioClient.repositories.TipologieLicenzeRepository;
import itcube.consulting.monitoraggioClient.response.GeneralResponse;
import itcube.consulting.monitoraggioClient.response.GetClientTypesResponse;
import itcube.consulting.monitoraggioClient.response.GetNSediResponse;
import itcube.consulting.monitoraggioClient.response.ResponseLogin;
import itcube.consulting.monitoraggioClient.response.ShallowClientsResponse;
import itcube.consulting.monitoraggioClient.response.LicenzeShallowResponse;
import itcube.consulting.monitoraggioClient.response.NomiSediResponse;
import itcube.consulting.monitoraggioClient.response.LicenzeDeepResponse;
import itcube.consulting.monitoraggioClient.response.DeepClientResponse;
import itcube.consulting.monitoraggioClient.services.Services;
import itcube.consulting.monitoraggioClient.response.ClientLicenseListResponse;
import net.minidev.json.JSONArray;
import org.apache.commons.lang3.RandomStringUtils;

@RestController
@RequestMapping(path="/be/main")
public class CompanyController {
	
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

			id_company= (Integer)body.get("id_company");
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
	
	
	@PostMapping(path="/getNSedi",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GetNSediResponse> getNSedi(@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		Integer id_company;
		String token;
		Integer NSedi;
		GetNSediResponse response = new GetNSediResponse();
		
		try {
			id_company= Integer.parseInt( (String) body.get("id_company") );
			token = (String) body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid()) {
				
				NSedi = elencoCompaniesRepository.getNSedi(id_company);
				
				response.setNSedi(NSedi);
				
				response.setMessage("OK");
				response.setMessageCode(0);
				response.setToken(validToken.getToken());
				return ResponseEntity.ok(response);
				
			} else {
				response.setMessage("Autenticazione fallita");
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
	
	@PostMapping(path="/modificaSedeClient",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> modificaSedeClient(@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		Integer id_company;
		Integer id_client;
		String token;
		String nuovaSede;
		String vecchiaSede;
		
		try
		{
			id_company= Integer.parseInt( (String) body.get("id_company") );
			token = (String) body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				vecchiaSede=(String) body.get("vecchiaSede");
				nuovaSede= (String) body.get("nuovaSede");
				String id_vecchia_sede = "";
				if(!vecchiaSede.equals("")) {
					id_vecchia_sede = Integer.toString(sediRepository.nomeToId(id_company, vecchiaSede));
				}
				
				id_client=Integer.parseInt( (String) body.get("id_client") );
			
				String id_nuova_sede =  Integer.toString(sediRepository.nomeToId(id_company, nuovaSede)) ;

				if(id_client==-1)
				{
					elencoClientsRepository.modificaAllVecchieSediClient(id_company,id_nuova_sede, id_vecchia_sede);
				}
				else
				{
					elencoClientsRepository.modificaSingolaSedeClient(id_client, id_nuova_sede);
				}
				
				generalResponse.setMessage("OK");
				generalResponse.setMessageCode(0);
				generalResponse.setToken(validToken.getToken());
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
	
	@PostMapping(path="/inserimentoSede",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> inserimentoSede(@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		Integer id_company;
		String token;
		String nome;
		Sedi sede=new Sedi();
		
		try
		{
			id_company= Integer.parseInt( (String) body.get("id_company") );
			token = (String) body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				nome=(String) body.get("nome");
				
				sede.setId_company(id_company);
				sede.setNome(nome);
				
				sediRepository.save(sede);
				
				generalResponse.setMessage("OK");
				generalResponse.setMessageCode(0);
				generalResponse.setToken(validToken.getToken());
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
	
	@PostMapping(path="/cancellazioneSede",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> cancellazioneSede(@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		Integer id_company;
		String token;
		String nome;
		List<Integer> clients=new ArrayList<>();
		Sedi sede=new Sedi();
		try
		{
			id_company= Integer.parseInt( (String) body.get("id_company") );
			token = (String) body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				nome=(String) body.get("nome");
				if(nome.equals("Senza sede")) {
					generalResponse.setMessage("Impossibile eliminare 'Senza sede'");
					generalResponse.setMessageCode(1);
					generalResponse.setToken(validToken.getToken());
					return ResponseEntity.ok(generalResponse);
				}
				
				sede=sediRepository.isPresent(nome, id_company);
				clients=elencoClientsRepository.getClientsInSede(sede.getId(), id_company);

				System.out.println(sede.getNome());
				System.out.println(clients.size());
				
				if(clients.size()==0 && sede!=null)
				{
					sediRepository.delete(sede);
					
					generalResponse.setMessage("OK");
					generalResponse.setMessageCode(0);
					generalResponse.setToken(validToken.getToken());
					return ResponseEntity.ok(generalResponse);
				}
				else
				{
					generalResponse.setMessage("Impossibile completare l'operazione");
					generalResponse.setMessageCode(-3);
					generalResponse.setToken(validToken.getToken());
					return ResponseEntity.ok(generalResponse);
				}
			}
			generalResponse.setMessage("Autenticazione fallita");
			generalResponse.setMessageCode(-2);
			return ResponseEntity.ok(generalResponse);
		}
		catch(Exception e)
		{
			generalResponse.setMessage(e.getMessage());
//			generalResponse.setMessage("Impossibile completare l'operazione");
			generalResponse.setMessageCode(-1);
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(generalResponse);
		}
	}
	
	@PostMapping(path="/getNomiSedi",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> getNomiSedi(@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		NomiSediResponse response=new NomiSediResponse();
		ValidToken validToken=new ValidToken();
		Integer id_company;
		String token;
		List<String> nomi=new ArrayList<>();
		
		try
		{
			id_company= Integer.parseInt( (String) body.get("id_company") );
			token = (String) body.get("token");
			validToken= Services.checkToken(id_company, token);
			
			if(validToken.isValid())
			{
				nomi=sediRepository.getNomiSedi(id_company);
				
				response.setSedi(nomi);
				response.setMessage("OK");
				response.setMessageCode(0);
				response.setToken(validToken.getToken());
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
}
