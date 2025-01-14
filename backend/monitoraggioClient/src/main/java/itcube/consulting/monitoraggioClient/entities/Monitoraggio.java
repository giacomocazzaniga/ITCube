package itcube.consulting.monitoraggioClient.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Monitoraggio implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Boolean monitora;
	
	private int id_client;

	private String nome_servizio;
	
	public Monitoraggio() {
	}
	
	public Monitoraggio(ElencoClients elencoClients, List<ConfWindowsServices> confWindowsServices,
			boolean monitora) {
		super();
		this.monitora = monitora;
	}
	
	public Monitoraggio(int id, boolean monitora, int id_client, String nome_servizio) {
		this.id = id;
		this.monitora = monitora;
		this.id_client = id_client;
		this.nome_servizio = nome_servizio;
		System.out.println(monitora);
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

	public boolean isMonitora() {
		return monitora;
	}
	public void setMonitora(boolean monitora) {
		this.monitora = monitora;
	}

	public String getNome_servizio() {
		return nome_servizio;
	}

	public void setNome_servizio(String nome_servizio) {
		this.nome_servizio = nome_servizio;
	}
}
