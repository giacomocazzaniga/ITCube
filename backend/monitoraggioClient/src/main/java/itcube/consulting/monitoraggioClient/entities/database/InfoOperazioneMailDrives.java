package itcube.consulting.monitoraggioClient.entities.database;

import java.time.LocalDateTime;

public class InfoOperazioneMailDrives extends InfoOperazioneMail{
	
	private String nome_disco;
	private String spazio_disponibile;
	private String tipologia_alert;

	public InfoOperazioneMailDrives(String tipo_operazione, String date_and_time_alert, String nome_disco,
			String spazio_disponibile, String tipologia_alert, String nome_client, String nome_company) {
		super(tipo_operazione, date_and_time_alert, nome_client, nome_company);
		this.nome_disco = nome_disco;
		this.spazio_disponibile = spazio_disponibile;
		this.tipologia_alert = tipologia_alert;
	}

	public String getNome_disco() {
		return nome_disco;
	}

	public void setNome_disco(String nome_disco) {
		this.nome_disco = nome_disco;
	}

	public String getSpazio_disponibile() {
		return spazio_disponibile;
	}

	public void setSpazio_disponibile(String spazio_disponibile) {
		this.spazio_disponibile = spazio_disponibile;
	}

	public String getTipologia_alert() {
		return tipologia_alert;
	}

	public void setTipologia_alert(String tipologia_alert) {
		this.tipologia_alert = tipologia_alert;
	}

	
}
