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
public class LicenzeController {
	
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
}
