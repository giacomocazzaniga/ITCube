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
public class LoginController {
	
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
				
				//salva la licenza
				do
				{
					codice=Services.getLicenseCode();
				}while(elencoLicenzeRepository.countCodes(codice)!=0);
				
				elencoLicenze=new ElencoLicenze();
				elencoLicenze.setCodice(codice);
				elencoLicenze.setElencoClients(null);
				elencoLicenze.setTipologieLicenze(tipologieLicenzeRepository.getLicenza("1"));
				elencoLicenze.setElencoCompanies(company);
				elencoLicenze.setScadenza(Services.getScadenza());
				
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
	
	@PostMapping(path="/hello",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public String helloWorld(@RequestBody Map<String,Object> body) {
		if(Services.isValid(Integer.parseInt((String)body.get("id_company")), (String)body.get("token")))
		{
			String newToken=Services.checkThreshold((Integer)body.get("id_company"),(String)body.get("token"));
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

}
