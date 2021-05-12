package itcube.consulting.monitoraggioClient.entities.database;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import freemarker.template.TemplateException;
import itcube.consulting.monitoraggioClient.entities.Alert;
import itcube.consulting.monitoraggioClient.entities.ElencoClients;
import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;
import itcube.consulting.monitoraggioClient.repositories.ElencoAlertRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoClientsRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoCompaniesRepository;
import itcube.consulting.monitoraggioClient.services.EmailService;
import itcube.consulting.monitoraggioClient.services.Services;

@Component
public class Task extends TimerTask{
	
	@Autowired
	ElencoAlertRepository elencoAlertRepository;
	
	@Autowired
	ElencoCompaniesRepository elencoCompaniesRepository;
	
	@Autowired 
	ElencoClientsRepository elencoClientsRepository;

	@Scheduled(fixedRate = 60000)
	public void run() {
		List<Alert> list_alert = new ArrayList<Alert>();
		EmailService mail = new EmailService();
		list_alert = elencoAlertRepository.getAllAlertsWithoutMailDateTime();
		list_alert.forEach( alert -> {
			
			String categoria_alert = "";
			ElencoClients client = elencoClientsRepository.getClientFromId(alert.getId_client());
			ElencoCompanies company = elencoCompaniesRepository.getInfoCompany(alert.getId_company());
			InfoOperazioneMail info = null;
			
			if(alert.getCategoria()==2) {
				categoria_alert = "windows services";
				String nome_servizio = null;
				if(alert.getCorpo_messaggio().contains("presenta"))
					nome_servizio = alert.getCorpo_messaggio().substring(alert.getCorpo_messaggio().indexOf("servizio")+"servizio".length()+1, alert.getCorpo_messaggio().indexOf("presenta"));				
				if(alert.getCorpo_messaggio().contains("è in stato"))
					nome_servizio = alert.getCorpo_messaggio().substring(alert.getCorpo_messaggio().indexOf("servizio")+"servizio".length()+1, alert.getCorpo_messaggio().indexOf("è in stato"));
				if(alert.getCorpo_messaggio().contains("funziona"))
					nome_servizio = alert.getCorpo_messaggio().substring(alert.getCorpo_messaggio().indexOf("servizio")+"servizio".length()+1, alert.getCorpo_messaggio().indexOf("funziona"));
					
				info = new InfoOperazioneMailServices("WINDOWS_SERVICES", alert.getDate_and_time_alert().toString(), client.getNome(), company.getRagione_sociale(), nome_servizio, alert.getTipo());
			} else if (alert.getCategoria()==3){
				categoria_alert = "windows events";
				String descrizione_evento = alert.getCorpo_messaggio().substring(alert.getCorpo_messaggio().indexOf(":")+2, alert.getCorpo_messaggio().length());
				
				info = new InfoOperazioneMailEvents("WINDOWS_EVENTS", alert.getDate_and_time_alert().toString(), client.getNome(), company.getRagione_sociale(), descrizione_evento, alert.getTipo());
			} else if (alert.getCategoria()==1) {
				categoria_alert = "drives";
				String nome_disco = alert.getCorpo_messaggio().substring(alert.getCorpo_messaggio().indexOf("disco")+"disco".length()+1, alert.getCorpo_messaggio().indexOf("ha lo spazio"));
				String spazio_disponibile = alert.getCorpo_messaggio().substring(alert.getCorpo_messaggio().indexOf("pari al")+"pari al".length()+1, alert.getCorpo_messaggio().length());
				
				info = new InfoOperazioneMailDrives("DRIVES", alert.getDate_and_time_alert().toString(), nome_disco, spazio_disponibile, alert.getTipo(), client.getNome(), company.getRagione_sociale());
			}
			
			try {
				EmailService.sendEmail("Alert " + categoria_alert, mail.getEmailContent(elencoCompaniesRepository.getInfoCompany(alert.getId_company()), info), elencoCompaniesRepository.getInfoCompany(alert.getId_company()).getEmail_alert());
			} catch (IOException | TemplateException e) {
				e.printStackTrace();
			}
			
			elencoAlertRepository.updateMailTimestamp(alert.getId());
		});
	}

	
}
