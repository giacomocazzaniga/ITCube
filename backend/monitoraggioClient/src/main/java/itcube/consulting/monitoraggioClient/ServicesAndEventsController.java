package itcube.consulting.monitoraggioClient;

import java.util.ArrayList;
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
import itcube.consulting.monitoraggioClient.response.GeneralResponse;
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
	
	@PostMapping(path="/shallowClients",produces=MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin
	public ResponseEntity<GeneralResponse> inserimentoServizi (@RequestBody Map<String,Object> body)
	{
		GeneralResponse generalResponse=new GeneralResponse();
		ValidToken validToken=new ValidToken();
		int id_company;
		String token;
		List<ConfWindowsServices> servizi=new ArrayList<ConfWindowsServices>();
		List<Monitoraggio> monitoraggio=new ArrayList<Monitoraggio>();
		
		try
		{
			servizi=(List<ConfWindowsServices>)body.get("servizi");
			if(servizi.size()!=0)
			{
				for(ConfWindowsServices i: servizi)
				{
					confWindowsServicesRepository.save(i);
				}
				id_company=servizi.get(0).getElencoClients().getId();
				monitoraggio=monitoraggioRepository.getServiziClient(id_company);
				
				if(monitoraggio!=null)
				{
					for(ConfWindowsServices i: servizi)
					{
						if(monitoraggio.contains(i.toMonitoraggio(i, confWindowsServicesRepository)))
							monitoraggioRepository.save(null);
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
}
