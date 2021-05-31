package itcube.consulting.monitoraggioClient.services;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;
import itcube.consulting.monitoraggioClient.entities.database.InfoMailRecuperaPassword;
import itcube.consulting.monitoraggioClient.entities.database.InfoOperazioneMail;
import itcube.consulting.monitoraggioClient.entities.database.InfoOperazioneMailAggregata;
import itcube.consulting.monitoraggioClient.entities.database.InfoOperazioneMailDrives;
import itcube.consulting.monitoraggioClient.entities.database.InfoOperazioneMailEvents;
import itcube.consulting.monitoraggioClient.entities.database.InfoOperazioneMailServices;

@Service
public class EmailService {

	public static int sendEmail (String oggetto, String corpo, String destinatario) {
//		SMTP = smtp.gmail.com 
//		Port = 587
		
		final String username = "itsentinelalert@gmail.com";
        final String password = "itcubealert";
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("itsentinelalert@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinatario)
            );
            message.setSubject(oggetto);
            message.setContent(corpo, "text/html");

            Transport.send(message);

            System.out.println("mail inviata");
            return 0;

        } catch (MessagingException e) {
            e.printStackTrace();
            return -1;
        }
	}

	    public String getEmailContent(ElencoCompanies company, Object info) throws IOException, TemplateException {
	        StringWriter stringWriter = new StringWriter();
	        Map<String, Object> model = new HashMap<>();
	        model.put("company", company);
	        model.put("info", info);
	        Configuration configuration = returnConfiguration();
	        
	        Template temp = null;
	        

	        if(InfoMailRecuperaPassword.class.isInstance(info)) {
	        	temp = configuration.getTemplate("recuperaPassword.ftl");
	        	temp.process(model, stringWriter);
	        	
	        	return stringWriter.getBuffer().toString();
	        }
        	if(InfoOperazioneMailAggregata.class.isInstance(info)) {
        		temp = configuration.getTemplate("aggregate.ftl");
    		    temp.process(model, stringWriter);
   		       
  		        return stringWriter.getBuffer().toString();
        	}
    		if(InfoOperazioneMail.class.isInstance(info)) {
	        	String newDateString = ((InfoOperazioneMail) info).getDate_and_time_alert();

    		if(newDateString.indexOf(".") != -1){
    			newDateString = newDateString.substring(0,newDateString.indexOf("."));
    		}
    		if(newDateString.indexOf("T") != -1){
			  newDateString = newDateString.replace("T", " ");
    		}

        	((InfoOperazioneMail) info).setDate_and_time_alert(newDateString);

        	if(InfoOperazioneMailDrives.class.isInstance(info)) {
        	    switch(((InfoOperazioneMailDrives) info).getTipologia_alert()){
	  				case "ERROR":
	  					((InfoOperazioneMailDrives) info).setTipologia_alert("errore");
	  				break;
	  				case "WARNING":
	  					((InfoOperazioneMailDrives) info).setTipologia_alert("warning");
	  				break;
	  				case "OK":
	  					((InfoOperazioneMailDrives) info).setTipologia_alert("ok");
	  				break;
  				}
        		
        		temp = configuration.getTemplate("drives.ftl");
        	}
        	if(InfoOperazioneMailServices.class.isInstance(info)) {
        		
        		switch(((InfoOperazioneMailServices) info).getTipologia_alert()){
	  				case "ERROR":
	  					((InfoOperazioneMailServices) info).setTipologia_alert("errore");
	  				break;
	  				case "WARNING":
	  					((InfoOperazioneMailServices) info).setTipologia_alert("warning");
	  				break;
	  				case "OK":
	  					((InfoOperazioneMailServices) info).setTipologia_alert("ok");
	  				break;
        		}
        		temp = configuration.getTemplate("services.ftl");
        	}
        	if(InfoOperazioneMailEvents.class.isInstance(info)) {
        		switch(((InfoOperazioneMailEvents) info).getTipologia_alert()){
	  				case "ERROR":
	  					((InfoOperazioneMailEvents) info).setTipologia_alert("errore");
	  				break;
	  				case "WARNING":
	  					((InfoOperazioneMailEvents) info).setTipologia_alert("warning");
	  				break;
	  				case "OK":
	  					((InfoOperazioneMailEvents) info).setTipologia_alert("ok");
	  				break;
	  			}
	        		temp = configuration.getTemplate("events.ftl");
        	}
		        temp.process(model, stringWriter);
		       
		        return stringWriter.getBuffer().toString();
	        }
	        return null;
	    }
	    
	    Configuration returnConfiguration() throws IOException {
	    	// Create your Configuration instance, and specify if up to what FreeMarker
	    	// version (here 2.3.29) do you want to apply the fixes that are not 100%
	    	// backward-compatible. See the Configuration JavaDoc for details.
	    	Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);

	    	// Specify the source where the template files come from. Here I set a
	    	// plain directory for it, but non-file-system sources are possible too:
	    	cfg.setClassForTemplateLoading(this.getClass(), "/templates/");

	    	// From here we will set the settings recommended for new projects. These
	    	// aren't the defaults for backward compatibilty.

	    	// Set the preferred charset template files are stored in. UTF-8 is
	    	// a good choice in most applications:
	    	cfg.setDefaultEncoding("UTF-8");

	    	// Sets how errors will appear.
	    	// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
	    	cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

	    	// Don't log exceptions inside FreeMarker that it will thrown at you anyway:
	    	cfg.setLogTemplateExceptions(false);

	    	// Wrap unchecked exceptions thrown during template processing into TemplateException-s:
	    	cfg.setWrapUncheckedExceptions(true);

	    	// Do not fall back to higher scopes when reading a null loop variable:
	    	cfg.setFallbackOnNullLoopVariable(false);
	    	
	    	return cfg;
	    }
	
}