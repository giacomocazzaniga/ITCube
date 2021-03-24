package itcube.consulting.monitoraggioClient.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Monitoraggio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "id_client")
	private ElencoClients elencoClients;
	//private int id_client;
	
	@OneToMany(mappedBy = "tipologieLicenze")
	List<ConfWindowsServices> confWindowsServices;
	//private String nome_servizio;
	
	private boolean monitora;
	
	public Monitoraggio() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public ElencoClients getElencoClients() {
		return elencoClients;
	}

	public void setElencoClients(ElencoClients elencoClients) {
		this.elencoClients = elencoClients;
	}

	public List<ConfWindowsServices> getConfWindowsServices() {
		return confWindowsServices;
	}

	public void setConfWindowsServices(List<ConfWindowsServices> confWindowsServices) {
		this.confWindowsServices = confWindowsServices;
	}

	public boolean isMonitora() {
		return monitora;
	}
	public void setMonitora(boolean monitora) {
		this.monitora = monitora;
	}
}
