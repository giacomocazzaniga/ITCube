package itcube.consulting.monitoraggioClient.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import itcube.consulting.monitoraggioClient.repositories.ConfWindowsServicesRepository;

@Entity
public class ConfWindowsServices {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "nome_servizio")
	private Monitoraggio monitoraggio;
	//private String nome_servizio;
	
	@OneToOne
	@JoinColumn(name = "id_client")
	private ElencoClients elencoClients;
	//private int id_client;
	
	private int stato;
	private String description;
	private int display_name;
	private int start_type;
	private int service_type;
	private Date date_and_time;
	
	public ConfWindowsServices() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate_and_time() {
		return date_and_time;
	}

	public void setDate_and_time(Date date_and_time) {
		this.date_and_time = date_and_time;
	}

	public ElencoClients getElencoClients() {
		return elencoClients;
	}

	public void setElencoClients(ElencoClients elencoClients) {
		this.elencoClients = elencoClients;
	}

	public int getStato() {
		return stato;
	}

	public void setStato(int stato) {
		this.stato = stato;
	}

	public Monitoraggio getMonitoraggio() {
		return monitoraggio;
	}

	public void setMonitoraggio(Monitoraggio monitoraggio) {
		this.monitoraggio = monitoraggio;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(int display_name) {
		this.display_name = display_name;
	}

	public int getStart_type() {
		return start_type;
	}

	public void setStart_type(int start_type) {
		this.start_type = start_type;
	}

	public int getService_type() {
		return service_type;
	}

	public void setService_type(int service_type) {
		this.service_type = service_type;
	}
	
	public Monitoraggio toMonitoraggio(ConfWindowsServices service, ConfWindowsServicesRepository repo)
	{
		Monitoraggio monitoraggio=new Monitoraggio();
		monitoraggio.setMonitora(true);
		//monitoraggio.setConfWindowsServices(repo.getServiziClient());
		monitoraggio.setElencoClients(service.getElencoClients());
		
		return monitoraggio;
	}
}
