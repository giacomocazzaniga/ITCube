package itcube.consulting.monitoraggioClient.services;

import java.util.List;
import java.util.Timer;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;
import itcube.consulting.monitoraggioClient.entities.database.EmailResocontoHandler;
import itcube.consulting.monitoraggioClient.repositories.ElencoClientsRepository;
import itcube.consulting.monitoraggioClient.repositories.ElencoCompaniesRepository;

@Component
public class StartMailAfterDownBE implements InitializingBean{

	@Autowired
	ElencoCompaniesRepository elencoCompaniesRepository;

	@Autowired
	ElencoClientsRepository elencoClientsRepository;

	@Override
	public void afterPropertiesSet() {

		List<ElencoCompanies> companies = elencoCompaniesRepository.getAllCompanies();
		
		companies.forEach( company -> {
			
			Long mailInterval = elencoCompaniesRepository.getMailInterval(company.getId());
			
			if(mailInterval!= null && mailInterval!=0) {
				Timer t = new Timer();
				EmailResocontoHandler.timers.put(company.getId(), t);
				EmailResocontoHandler handler = new EmailResocontoHandler(); 
				
				handler.scheduledEmail(company, mailInterval, false, elencoClientsRepository, elencoCompaniesRepository);
			}
		});
		
		
	}
}
