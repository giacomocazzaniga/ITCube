package itcube.consulting.monitoraggioClient.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AlertConfigurazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String operazione;
	
	@ManyToOne
	@JoinColumn(name = "id_client")
	private ElencoClients elencoClients;
	
	private Boolean monitora;
	
	private int categoria;
	
	public AlertConfigurazione( ) {
	}
	
	public AlertConfigurazione( String operazione, ElencoClients elencoClients, Boolean monitora, int categoria) {
		this.operazione = operazione;
		this.elencoClients = elencoClients;
		this.monitora = monitora;
		this.categoria = categoria;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean isMonitora() {
		return monitora;
	}

	public void setMonitora(Boolean monitora) {
		this.monitora = monitora;
	}

	public String getOperazione() {
		return operazione;
	}

	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}

	public ElencoClients getElencoClients() {
		return elencoClients;
	}

	public void setElencoClients(ElencoClients elencoClients) {
		this.elencoClients = elencoClients;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}


}
