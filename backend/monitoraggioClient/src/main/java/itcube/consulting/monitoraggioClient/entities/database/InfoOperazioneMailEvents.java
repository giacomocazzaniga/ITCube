package itcube.consulting.monitoraggioClient.entities.database;

import java.time.LocalDateTime;

public class InfoOperazioneMailEvents extends InfoOperazioneMail{
	
	private String descrizione_evento;
	private String tipologia_alert;

	public InfoOperazioneMailEvents(String tipo_operazione, String date_and_time_alert, String nome_client, String nome_company, String descrizione_evento, String tipologia_alert) {
		super(tipo_operazione, date_and_time_alert, nome_client, nome_company);
		this.descrizione_evento = descrizione_evento;
		this.setTipologia_alert(tipologia_alert);
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

}
