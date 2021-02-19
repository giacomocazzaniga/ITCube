package itcube.consulting.monitoraggioClient;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import itcube.consulting.monitoraggioClient.entities.ConfTotalFreeDiscSpace;
import itcube.consulting.monitoraggioClient.entities.ConfWindowsServices;
import itcube.consulting.monitoraggioClient.entities.ElencoClients;
import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;
import itcube.consulting.monitoraggioClient.entities.ElencoLicenze;
import itcube.consulting.monitoraggioClient.repositories.ConfTotalFreeDiscSpaceRepository;
import itcube.consulting.monitoraggioClient.repositories.ConfWindowsServicesRepository;
import itcube.consulting.monitoraggioClient.repositories.ConfigRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoClientsRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoCompaniesRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoLicenzeRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoOperazioniRepository;
import itcube.consulting.monitoraggioClient.repositories.RealTimeRepository;
import itcube.consulting.monitoraggioClient.repositories.TipologieLicenzeRepository;
import itcube.consulting.monitoraggioClient.response.GeneralResponse;
import itcube.consulting.monitoraggioClient.response.ResponseLogin;
import org.apache.commons.lang3.RandomStringUtils;

@RestController
@RequestMapping(path="/be/main")
public class MainController {
	
	//private  AuthenticationManager authenticationManager;

	private HashMap<String, Date> AuthenticationManager= new HashMap<String, Date>();
	private int milliSecLenghtToken=30000;
	private double threshold=0.1*milliSecLenghtToken;
	
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
	
	//http://localhost:8080/be/main/registrazione
	@PostMapping(path="/registrazione",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse> Registrazione(@RequestBody Map<String,Object> body){
		GeneralResponse responseRegistrazione=new GeneralResponse();
		String email;
		String password;
		String email_alert;
		String ragione_sociale;
		List<String> elencoCompanies;
		ElencoCompanies company;
		ElencoLicenze licenza;
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
			
			System.out.println(elencoCompanies);
	
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
				codice=getLicenseCode();
				licenza=new ElencoLicenze();
				licenza.setCodice(codice);
				licenza.setElencoClients(null);
				licenza.setTipologieLicenze(tipologieLicenzeRepository.getLicenza("1"));
				licenza.setElencoCompanies(company);
				
				elencoLicenzeRepository.save(licenza);
				
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
				
				System.out.println(email);
				System.out.println(password);
				
				numCompany=elencoCompaniesRepository.Login(email, password);
				
				System.out.println(numCompany);
		
				if(numCompany<1)
				{
					generalResponse.setMessage("Password sbagliata o utente non presente");
					generalResponse.setMessageCode(1);
					return ResponseEntity.ok(generalResponse);
				}
				else
				{
					company=elencoCompaniesRepository.getInfoCompany(email);
					elencoClients=elencoClientsRepository.getElencoClients(company);
					token = getJWTToken(company.getRagione_sociale());
					
					AuthenticationManager.put(token, new Date(System.currentTimeMillis() +milliSecLenghtToken));
					
					responseLogin.setMessage("Login avvenuto con successo");
					responseLogin.setMessageCode(0);
					responseLogin.setRagione_sociale(company.getRagione_sociale());
					responseLogin.setId_company(company.getId());
					responseLogin.setElencoClients(elencoClients);
					responseLogin.setToken(token);
					return ResponseEntity.ok(responseLogin);
				}
			}
			catch (Exception e) {
				generalResponse.setMessage(e.getMessage());
				generalResponse.setMessageCode(-1);
				System.out.println(e.getMessage());
				return ResponseEntity.badRequest().body(generalResponse);
			}
		}
		
		@PostMapping(path="/hello",produces=MediaType.APPLICATION_JSON_VALUE)
		public String helloWorld(@RequestBody Map<String,Object> body) {
			if(isValid((String)body.get("token")))
			{
				String newToken=checkThreshold((String)body.get("token"));
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
		
		//Metodo token
		private String getJWTToken(String ragione_sociale) {
			String secretKey = "mySecretKey";
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_USER");
			
			String token = Jwts
					.builder()
					.setId("ITCube")
					.setSubject(ragione_sociale)
					.claim("authorities",
							grantedAuthorities.stream()
									.map(GrantedAuthority::getAuthority)
									.collect(Collectors.toList()))
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + milliSecLenghtToken))
					.signWith(SignatureAlgorithm.HS512, 
					  secretKey.getBytes()).compact();

			return token;
		}

		private boolean isValid(String token)
		{
			if(AuthenticationManager.containsKey(token))
			{
				if((new Date(System.currentTimeMillis())).before(((Date)AuthenticationManager.get(token))))
				{
					return true;
				}
				else
				{
					AuthenticationManager.remove(token);
					return false;
				}
			}
			return false;
		}
		
		private String checkThreshold(String token)
		{
			if((getDateDiff(new Date(System.currentTimeMillis()),((Date)AuthenticationManager.get(token)), TimeUnit.MILLISECONDS))<threshold)
			{
				System.out.println((getDateDiff(new Date(System.currentTimeMillis()),((Date)AuthenticationManager.get(token)), TimeUnit.MILLISECONDS)));
				System.out.println(threshold);
				String newToken=getJWTToken(token);
				AuthenticationManager.remove(token);
				AuthenticationManager.put(newToken,new Date(System.currentTimeMillis() + milliSecLenghtToken));
				return newToken;
			}
			return null;
		}
		
		private String getLicenseCode()
		{
				String codice="";
				String shortId;
				do
				{
					for(int i=0; i<4; i++)
					{
						shortId = RandomStringUtils.randomAlphanumeric(4); 
						System.out.println(shortId);
						codice+=shortId;
						if(i<3)
							codice+="-";
					}
					//codice = UUID.randomUUID().toString();
				}
			    while(elencoLicenzeRepository.countCodes(codice)!=0);
				
			    return codice;    	
		}
		
		private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		    long diffInMillies = date2.getTime() - date1.getTime();
		    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
		}
}
