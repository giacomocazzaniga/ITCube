package itcube.consulting.monitoraggioClient.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ConfTotalFreeDiscSpace {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "id_client")
	private ElencoClients elencoClients;
	//private int id_client;
	
	private String drive;
	private int perc_free_disc_space;
	
	public ConfTotalFreeDiscSpace() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}*/

	public String getDrive() {
		return drive;
	}

	public void setDrive(String drive) {
		this.drive = drive;
	}

	public int getPerc_free_disc_space() {
		return perc_free_disc_space;
	}

	public void setPerc_free_disc_space(int perc_free_disc_space) {
		this.perc_free_disc_space = perc_free_disc_space;
	}

	public ElencoClients getElencoClients() {
		return elencoClients;
	}

	public void setElencoClients(ElencoClients elencoClients) {
		this.elencoClients = elencoClients;
	}
}
