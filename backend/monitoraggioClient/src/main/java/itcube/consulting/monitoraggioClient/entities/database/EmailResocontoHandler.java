package itcube.consulting.monitoraggioClient.entities.database;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import freemarker.template.TemplateException;
import itcube.consulting.monitoraggioClient.entities.Alert;
import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;
import itcube.consulting.monitoraggioClient.repositories.ElencoClientsRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoCompaniesRepository;
import itcube.consulting.monitoraggioClient.services.EmailService;
import itcube.consulting.monitoraggioClient.services.Services;

@Component
public class EmailResocontoHandler {
	
	public static Map<Integer,Timer> timers = new HashMap<Integer,Timer>();
	public static Map<Integer,List<Alert>> alerts = new HashMap<Integer,List<Alert>>();

	public void scheduledEmail (ElencoCompanies company, long fixedRate, boolean stopTimer, ElencoClientsRepository elencoClientsRepository,  ElencoCompaniesRepository elencoCompaniesRepository) {
		if(!stopTimer) {
			Timer oldTimer = timers.get(company.getId());
			if(oldTimer!=null)
				oldTimer.cancel();
			
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new Task(company,elencoClientsRepository, elencoCompaniesRepository), fixedRate, fixedRate);
			timers.put(company.getId(), timer);
		} else {
			Timer t = timers.get(company.getId());
			if(t!=null)
				t.cancel();
		}
	}	
	
	public class Task extends TimerTask {

		ElencoCompaniesRepository elencoCompaniesRepository;		
		ElencoClientsRepository elencoClientsRepository;
		private ElencoCompanies company;
		
		public Task(ElencoCompanies company, ElencoClientsRepository elencoClientsRepository,ElencoCompaniesRepository elencoCompaniesRepository) {
			this.company = company;
			this.elencoClientsRepository = elencoClientsRepository;
			this.elencoCompaniesRepository = elencoCompaniesRepository;
		}



		@Override
		public void run() {
			EmailService mail = new EmailService();
			
			ResocontoErroriMacchina resocontoClient = new ResocontoErroriMacchina("Client", 0, 0, 0, 0, 0, 0);
			ResocontoErroriMacchina resocontoServer = new ResocontoErroriMacchina("Server", 0, 0, 0, 0, 0, 0);
			
			List<Alert> alertOfCompany = EmailResocontoHandler.alerts.get(company.getId());
			
			if(alertOfCompany != null) {
				alertOfCompany.forEach( alert -> {
					Integer tipoClient = elencoClientsRepository.getTipoFromIdClient(alert.getId_client());
					
					if(tipoClient == 1) {

						if(alert.getTipo()=="ERROR" && alert.getCorpo_messaggio().contains("servizio"))
							resocontoClient.setErrori_servizi(resocontoClient.getErrori_servizi()+1);
						if(alert.getTipo()=="WARNING" && alert.getCorpo_messaggio().contains("servizio"))
							resocontoClient.setWarning_servizi(resocontoClient.getWarning_servizi()+1);
						if(alert.getTipo()=="ERROR" && alert.getCorpo_messaggio().contains("disco"))
							resocontoClient.setErrori_drives(resocontoClient.getErrori_drives()+1);
						if(alert.getTipo()=="WARNING" && alert.getCorpo_messaggio().contains("disco"))
							resocontoClient.setWarning_drives(resocontoClient.getWarning_drives()+1);
						if(alert.getTipo()=="ERROR" && alert.getCorpo_messaggio().contains("Si è verificato"))
							resocontoClient.setErrori_eventi(resocontoClient.getErrori_eventi()+1);
						if(alert.getTipo()=="WARNING" && alert.getCorpo_messaggio().contains("Si è verificato"))
							resocontoClient.setWarning_eventi(resocontoClient.getWarning_eventi()+1);
							
					} else {
						
						if(alert.getTipo()=="ERROR" && alert.getCorpo_messaggio().contains("servizio"))
							resocontoServer.setErrori_servizi(resocontoServer.getErrori_servizi()+1);
						if(alert.getTipo()=="WARNING" && alert.getCorpo_messaggio().contains("servizio"))
							resocontoServer.setWarning_servizi(resocontoServer.getWarning_servizi()+1);
						if(alert.getTipo()=="ERROR" && alert.getCorpo_messaggio().contains("disco"))
							resocontoServer.setErrori_drives(resocontoServer.getErrori_drives()+1);
						if(alert.getTipo()=="WARNING" && alert.getCorpo_messaggio().contains("disco"))
							resocontoServer.setWarning_drives(resocontoServer.getWarning_drives()+1);
						if(alert.getTipo()=="ERROR" && alert.getCorpo_messaggio().contains("Si è verificato"))
							resocontoServer.setErrori_eventi(resocontoServer.getErrori_eventi()+1);
						if(alert.getTipo()=="WARNING" && alert.getCorpo_messaggio().contains("Si è verificato"))
							resocontoServer.setWarning_eventi(resocontoServer.getWarning_eventi()+1);
						
					}
				});
			}
			
			InfoOperazioneMailAggregata info = new InfoOperazioneMailAggregata(alertOfCompany, Services.address, resocontoClient, resocontoServer, company.getRagione_sociale());
			try {
				EmailService.sendEmail("Resoconto " + company.getRagione_sociale(), mail.getEmailContent(company, info),company.getEmail_alert());
				LocalDateTime timestamp = java.time.LocalDateTime.now();
				elencoCompaniesRepository.updateLastMailTimestamp(company.getId(), timestamp);
				System.out.println("Invio mail resoconto della company: " + company.getRagione_sociale());
				alerts.clear();
			} catch (IOException | TemplateException e) {
				e.printStackTrace();
			}

		}
	}

	
}
