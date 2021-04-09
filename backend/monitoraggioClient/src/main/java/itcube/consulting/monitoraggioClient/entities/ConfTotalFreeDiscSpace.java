package itcube.consulting.monitoraggioClient.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
	
	/*@OneToOne
	@JoinColumn(name = "id_client")
	private ElencoClients elencoClients;*/
	private int id_client;
	
	private String drive;
	private String descrizione;
	private long total_free_disc_space;
	private long total_size;
	private double perc_free_disc_space;
	private LocalDateTime date_and_time;
	
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

	public double getPerc_free_disc_space() {
		return perc_free_disc_space;
	}

	public double setPerc_free_disc_space(double total_size, double total_free_disc_space) {
		double tmp=total_free_disc_space/total_size;
		this.perc_free_disc_space = tmp*100;
		return this.perc_free_disc_space;
	}

	/*public ElencoClients getElencoClients() {
		return elencoClients;
	}

	public void setElencoClients(ElencoClients elencoClients) {
		this.elencoClients = elencoClients;
	}*/

	public LocalDateTime getDate_and_time() {
		return date_and_time;
	}

	public void setDate_and_time(LocalDateTime date_and_time) {
		this.date_and_time = date_and_time;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public long getTotal_free_disc_space() {
		return total_free_disc_space;
	}

	public void setTotal_free_disc_space(long total_free_disc_space) {
		this.total_free_disc_space = total_free_disc_space;
	}

	public long getTotal_size() {
		return total_size;
	}

	public void setTotal_size(long total_size) {
		this.total_size = total_size;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
}

