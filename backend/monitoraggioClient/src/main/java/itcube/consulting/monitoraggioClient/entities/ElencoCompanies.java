package itcube.consulting.monitoraggioClient.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ElencoCompanies {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//Chiave esterna 
	@OneToMany(mappedBy = "elencoCompanies")
	private List<ElencoLicenze> elencoLicenze;
	
	//Chiave esterna 
	@OneToMany(mappedBy = "elencoCompanies")
	private List<ElencoClients> elencoClients;
	
	private String ragione_sociale;
	private String password;
	private String email;
	private String email_alert;
	private String chiave_di_registrazione;
	private Long intervallo_mail;
	private LocalDateTime last_mail_date_and_time;

	public ElencoCompanies() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRagione_sociale() {
		return ragione_sociale;
	}

	public void setRagione_sociale(String ragione_sociale) {
		this.ragione_sociale = ragione_sociale;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail_alert() {
		return email_alert;
	}

	public void setEmail_alert(String email_alert) {
		this.email_alert = email_alert;
	}

	public List<ElencoLicenze> getElencoLicenze() {
		return elencoLicenze;
	}

	public void setElencoLicenze(List<ElencoLicenze> elencoLicenze) {
		this.elencoLicenze = elencoLicenze;
	}

	public List<ElencoClients> getElencoClients() {
		return elencoClients;
	}

	public void setElencoClients(List<ElencoClients> elencoClients) {
		this.elencoClients = elencoClients;
	}

	public String getChiave_di_registrazione() {
		return chiave_di_registrazione;
	}

	public void setChiave_di_registrazione(String chiave_di_registrazione) {
		this.chiave_di_registrazione = chiave_di_registrazione;
	}

	@Override
	public String toString() {
		return "ElencoCompanies [id=" + id + ", elencoLicenze=" + elencoLicenze + ", elencoClients=" + elencoClients
				+ ", ragione_sociale=" + ragione_sociale + ", password=" + password + ", email=" + email
				+ ", email_alert=" + email_alert + "]";
	}

	public Long getIntervallo_mail() {
		return intervallo_mail;
	}

	public void setIntervallo_mail(Long intervallo_mail) {
		this.intervallo_mail = intervallo_mail;
	}

	public LocalDateTime getLast_mail_date_and_time() {
		return last_mail_date_and_time;
	}

	public void setLast_mail_date_and_time(LocalDateTime last_mail_date_and_time) {
		this.last_mail_date_and_time = last_mail_date_and_time;
	}
	
	
}