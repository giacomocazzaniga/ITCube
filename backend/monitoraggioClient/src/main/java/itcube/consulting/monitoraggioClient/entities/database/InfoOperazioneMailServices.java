package itcube.consulting.monitoraggioClient.entities.database;

import java.time.LocalDateTime;

import itcube.consulting.monitoraggioClient.services.Services;

public class InfoOperazioneMailServices extends InfoOperazioneMail{ 
	
	private String nome_servizio;
	private String tipologia_alert;
	private String token_mail;
	private int id_client;
	private String server_address;

	public InfoOperazioneMailServices(String tipo_operazione, String date_and_time_alert,String nome_client, String nome_company, String nome_servizio, String tipologia_alert, String token_mail, int id_client, String server_address) {
		super(tipo_operazione, date_and_time_alert, nome_client, nome_company);
		this.nome_servizio = nome_servizio;
		this.tipologia_alert = tipologia_alert;
		this.token_mail = token_mail;
		this.id_client = id_client;
		this.server_address = server_address;
	}

	public String getNome_servizio() {
		return nome_servizio;
	}

	public void setNome_servizio(String nome_servizio) {
		this.nome_servizio = nome_servizio;
	}

	public String getTipologia_alert() {
		return tipologia_alert;
	}

	public void setTipologia_alert(String tipologia_alert) {
		this.tipologia_alert = tipologia_alert;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public String getToken_mail() {
		return token_mail;
	}

	public void setToken_mail(String token_mail) {
		this.token_mail = token_mail;
	}

	public String getServer_address() {
		return server_address;
	}

}
