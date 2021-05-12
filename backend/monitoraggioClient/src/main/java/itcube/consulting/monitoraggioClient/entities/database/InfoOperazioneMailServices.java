package itcube.consulting.monitoraggioClient.entities.database;

import java.time.LocalDateTime;

public class InfoOperazioneMailServices extends InfoOperazioneMail{
	
	private String nome_servizio;
	private String tipologia_alert;

	public InfoOperazioneMailServices(String tipo_operazione, String date_and_time_alert,String nome_client, String nome_company, String nome_servizio, String tipologia_alert) {
		super(tipo_operazione, date_and_time_alert, nome_client, nome_company);
		this.nome_servizio = nome_servizio;
		this.tipologia_alert = tipologia_alert;
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

}
