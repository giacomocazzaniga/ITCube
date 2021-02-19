package itcube.consulting.monitoraggioClient.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ConfWindowsServices {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome_servizio;
	private String stato;
	
	@OneToOne
	@JoinColumn(name = "id_client")
	private ElencoClients elencoClients;
	//private int id_client;
	
	public ConfWindowsServices() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome_servizio() {
		return nome_servizio;
	}

	public void setNome_servizio(String nome_servizio) {
		this.nome_servizio = nome_servizio;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public ElencoClients getElencoClients() {
		return elencoClients;
	}

	public void setElencoClients(ElencoClients elencoClients) {
		this.elencoClients = elencoClients;
	}

	/*public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}*/
}
