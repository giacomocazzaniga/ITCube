package itcube.consulting.monitoraggioClient.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	private String nome_servizio;
	

//	@OneToOne
//	@JoinColumn(name = "id_client")
	private int id_client;
	//private int id_client;

	private int stato;
	private String description;
	private String display_name;
	private int start_type;
	private String service_type;
	private LocalDateTime date_and_time;
	
	public ConfWindowsServices() {
		
	}
	
	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDate_and_time() {
		return date_and_time;
	}

	public void setDate_and_time(LocalDateTime date_and_time) {
		this.date_and_time = date_and_time;
	}

	public int getStato() {
		return stato;
	}

	public void setStato(int stato) {
		this.stato = stato;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public int getStart_type() {
		return start_type;
	}

	public void setStart_type(int start_type) {
		this.start_type = start_type;
	}

	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	
	public String getNome_servizio() {
		return nome_servizio;
	}

	public void setNome_servizio(String nome_servizio) {
		this.nome_servizio = nome_servizio;
	}

	
	public Monitoraggio toMonitoraggio(ConfWindowsServices service)
	{
		Monitoraggio monitoraggio=new Monitoraggio();
		monitoraggio.setMonitora(true);
		monitoraggio.setNome_servizio(service.getNome_servizio());
		monitoraggio.setId_client(service.getId_client());
		
		return monitoraggio;
	}
}
