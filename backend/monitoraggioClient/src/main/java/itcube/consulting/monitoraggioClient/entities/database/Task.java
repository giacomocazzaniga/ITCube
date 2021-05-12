package itcube.consulting.monitoraggioClient.entities.database;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import itcube.consulting.monitoraggioClient.entities.Alert;
import itcube.consulting.monitoraggioClient.repositories.ElencoAlertRepository;
import itcube.consulting.monitoraggioClient.services.EmailService;
import itcube.consulting.monitoraggioClient.services.Services;

@Component
public class Task extends TimerTask{
	
	@Autowired
	ElencoAlertRepository elencoAlertRepository;

	@Scheduled(fixedRate = 60000)
	public void run() {
		List<Alert> list_alert = new ArrayList<Alert>();
		list_alert = elencoAlertRepository.getAllAlertsWithoutMailDateTime();
		System.out.println("Aggiornamento invio mail alert");
		list_alert.forEach( alert -> {
			//TODO: Inviare mail
			
			String categoria_alert;
			if(alert.getCategoria()==3) {
				categoria_alert = "windows service";
			} else if (alert.getCategoria()==2){
				categoria_alert = "windows event";
			} else if (alert.getCategoria()==1) {
				categoria_alert = "disco";
			}
			
			EmailService.sendEmail("Alert ", null, null);
			elencoAlertRepository.updateMailTimestamp(alert.getId());
		});
	}

	
}
