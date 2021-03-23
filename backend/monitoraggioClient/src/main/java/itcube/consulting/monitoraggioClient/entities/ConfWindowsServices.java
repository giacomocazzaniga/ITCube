package itcube.consulting.monitoraggioClient.entities;

import java.io.Serializable;
import java.util.Date;

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
	private boolean attivo;
	private boolean esecuzione;
	private boolean notifica;
	private int tipo_alert;
	private Date date_and_time;
	
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
	
	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	public boolean isEsecuzione() {
		return esecuzione;
	}

	public void setEsecuzione(boolean esecuzione) {
		this.esecuzione = esecuzione;
	}

	public ElencoClients getElencoClients() {
		return elencoClients;
	}

	public void setElencoClients(ElencoClients elencoClients) {
		this.elencoClients = elencoClients;
	}
	
	public boolean isNotifica() {
		return notifica;
	}

	public void setNotifica(boolean notifica) {
		this.notifica = notifica;
	}
	/*public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}*/

	public int getTipo_alert() {
		return tipo_alert;
	}

	public void setTipo_alert(int tipo_alert) {
		this.tipo_alert = tipo_alert;
	}

	public Date getDate_and_time() {
		return date_and_time;
	}

	public void setDate_and_time(Date date_and_time) {
		this.date_and_time = date_and_time;
	}
}
