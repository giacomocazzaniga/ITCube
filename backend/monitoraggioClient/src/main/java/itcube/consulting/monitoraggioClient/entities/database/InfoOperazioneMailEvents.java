package itcube.consulting.monitoraggioClient.entities.database;

import java.time.LocalDateTime;

public class InfoOperazioneMailEvents extends InfoOperazioneMail{
	
	private String descrizione_evento;
	private String tipologia_alert;
	private String token_mail;
	private int id_client;
	private String server_address;

	public InfoOperazioneMailEvents(String tipo_operazione, String date_and_time_alert, String nome_client, String nome_company, String descrizione_evento, String tipologia_alert, String token_mail, int id_client, String server_address) {
		super(tipo_operazione, date_and_time_alert, nome_client, nome_company);
		this.descrizione_evento = descrizione_evento;
		this.setTipologia_alert(tipologia_alert);
		this.token_mail = token_mail;
		this.id_client = id_client;
		this.server_address = server_address;
	}

	public String getDescrizione_evento() {
		return descrizione_evento;
	}

	public void setDescrizione_evento(String descrizione_evento) {
		this.descrizione_evento = descrizione_evento;
	}

	public String getTipologia_alert() {
		return tipologia_alert;
	}

	public void setTipologia_alert(String tipologia_alert) {
		this.tipologia_alert = tipologia_alert;
	}

	public String getToken_mail() {
		return token_mail;
	}

	public void setToken_mail(String token_mail) {
		this.token_mail = token_mail;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public String getServer_address() {
		return server_address;
	}

	public void setServer_address(String server_address) {
		this.server_address = server_address;
	}

}
