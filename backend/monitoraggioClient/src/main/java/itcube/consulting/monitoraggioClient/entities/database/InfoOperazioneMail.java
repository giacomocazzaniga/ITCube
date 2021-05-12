package itcube.consulting.monitoraggioClient.entities.database;

import java.time.LocalDateTime;

public abstract class InfoOperazioneMail {

	private String tipo_operazione;
	private String date_and_time_alert;
	private String info_aggiuntive;
	private String nome_client;
	private String nome_company;

	public InfoOperazioneMail(String tipo_operazione, String date_and_time_alert, String nome_client, String nome_company) {
		this.tipo_operazione = tipo_operazione;
		this.date_and_time_alert = date_and_time_alert;
		this.nome_client = nome_client;
		this.nome_company = nome_company;
		this.info_aggiuntive = "";
	}
	
	public InfoOperazioneMail(String tipo_operazione, String date_and_time_alert, String info_aggiuntive, String nome_client, String nome_company) {
		this.tipo_operazione = tipo_operazione;
		this.date_and_time_alert = date_and_time_alert;
		this.info_aggiuntive = info_aggiuntive;
		this.nome_client = nome_client;
		this.nome_company = nome_company;
	}

	public String getTipo_operazione() {
		return tipo_operazione;
	}
	public void setTipo_operazione(String tipo_operazione) {
		this.tipo_operazione = tipo_operazione;
	}

	public String getDate_and_time_alert() {
		return date_and_time_alert;
	}

	public void setDate_and_time_alert(String date_and_time_alert) {
		this.date_and_time_alert = date_and_time_alert;
	}
	
	public String getInfo_aggiuntive() {
		return info_aggiuntive;
	}


	public void setInfo_aggiuntive(String info_aggiuntive) {
		this.info_aggiuntive = info_aggiuntive;
	}

	public String getNome_client() {
		return nome_client;
	}

	public void setNome_client(String nome_client) {
		this.nome_client = nome_client;
	}

	public String getNome_company() {
		return nome_company;
	}

	public void setNome_company(String nome_company) {
		this.nome_company = nome_company;
	}

}
