package itcube.consulting.monitoraggioClient.entities.database;

import java.util.ArrayList;
import java.util.List;

import itcube.consulting.monitoraggioClient.entities.Alert;
import itcube.consulting.monitoraggioClient.entities.ConfWindowsServices;

public class InfoOperazioneMailAggregata {
	
	private List<Alert> alert = new ArrayList<Alert>();
	private String server_address;
	private ResocontoErroriMacchina client_data;
	private ResocontoErroriMacchina server_data;
	private String nome_company;
	

	public InfoOperazioneMailAggregata(List<Alert> alert, String server_address, ResocontoErroriMacchina client_data,
			ResocontoErroriMacchina server_data, String nome_company) {
		this.alert = alert;
		this.server_address = server_address;
		this.client_data = client_data;
		this.server_data = server_data;
		this.nome_company = nome_company;
	}

	public List<Alert> getAlert() {
		return alert;
	}

	public void setAlert(List<Alert> alert) {
		this.alert = alert;
	}

	public String getServer_address() {
		return server_address;
	}

	public void setServer_address(String server_address) {
		this.server_address = server_address;
	}

	public ResocontoErroriMacchina getServer_data() {
		return server_data;
	}

	public void setServer_data(ResocontoErroriMacchina server_data) {
		this.server_data = server_data;
	}

	public ResocontoErroriMacchina getClient_data() {
		return client_data;
	}

	public void setClient_data(ResocontoErroriMacchina client_data) {
		this.client_data = client_data;
	}
	public String getNome_company() {
		return nome_company;
	}

	public void setNome_company(String nome_company) {
		this.nome_company = nome_company;
	}
	
}
