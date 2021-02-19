package itcube.consulting.monitoraggioClient.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Config {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int tempo;
	
	@OneToOne
	@JoinColumn(name = "id_client")
	private ElencoClients elencoClients;
	//private int id_client;
	
	@OneToOne
	@JoinColumn(name = "id_operazione")
	private ElencoOperazioni elencoOperazioni;
	//private int id_operazione;
	
	public Config() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	/*public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}*/

	public ElencoOperazioni getElencoOperazioni() {
		return elencoOperazioni;
	}

	public void setElencoOperazioni(ElencoOperazioni elencoOperazioni) {
		this.elencoOperazioni = elencoOperazioni;
	}

	public ElencoClients getElencoClients() {
		return elencoClients;
	}

	public void setElencoClients(ElencoClients elencoClients) {
		this.elencoClients = elencoClients;
	}

	/*public int getId_operazione() {
		return id_operazione;
	}

	public void setId_operazione(int id_operazione) {
		this.id_operazione = id_operazione;
	}*/
}
