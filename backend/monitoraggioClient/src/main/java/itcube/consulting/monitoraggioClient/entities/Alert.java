package itcube.consulting.monitoraggioClient.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Alert {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime date_and_time_alert;
	private LocalDateTime date_and_time_mail;
	private int id_client;
	private int id_company;
	private String tipo;
	private String corpo_messaggio;
	private int categoria;
//	private String nome_disco;
//	private float perc_free_disc;
	
	
	
//	public String getNome_disco() {
//		return nome_disco;
//	}
//	public void setNome_disco(String nome_disco) {
//		this.nome_disco = nome_disco;
//	}
//	public float getPerc_free_disc() {
//		return perc_free_disc;
//	}
//	public void setPerc_free_disc(float perc_free_disc) {
//		this.perc_free_disc = perc_free_disc;
//	}
	public Alert() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getDate_and_time_alert() {
		return date_and_time_alert;
	}
	public void setDate_and_time_alert(LocalDateTime date_and_time_alert) {
		this.date_and_time_alert = date_and_time_alert;
	}
	public LocalDateTime getDate_and_time_mail() {
		return date_and_time_mail;
	}
	public void setDate_and_time_mail(LocalDateTime date_and_time_mail) {
		this.date_and_time_mail = date_and_time_mail;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public int getId_company() {
		return id_company;
	}
	public void setId_company(int id_company) {
		this.id_company = id_company;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCorpo_messaggio() {
		return corpo_messaggio;
	}
	public void setCorpo_messaggio(String corpo_messaggio) {
		this.corpo_messaggio = corpo_messaggio;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
}
