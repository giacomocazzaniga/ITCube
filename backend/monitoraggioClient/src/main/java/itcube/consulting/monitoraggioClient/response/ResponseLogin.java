package itcube.consulting.monitoraggioClient.response;

import java.util.List;

import itcube.consulting.monitoraggioClient.entities.ElencoClients;

public class ResponseLogin extends GeneralResponse{
	private String ragione_sociale;
	private int id_company;
	private String emailNotify;
	private String chiave_di_registrazione;
	
	public ResponseLogin() {

	}

	public String getRagione_sociale() {
		return ragione_sociale;
	}

	public void setRagione_sociale(String ragione_sociale) {
		this.ragione_sociale = ragione_sociale;
	}

	public int getId_company() {
		return id_company;
	}

	public void setId_company(int id_company) {
		this.id_company = id_company;
	}

	public String getEmailNotify() {
		return emailNotify;
	}

	public void setEmailNotify(String emailNotify) {
		this.emailNotify = emailNotify;
	}

	public String getChiave_di_registrazione() {
		return chiave_di_registrazione;
	}

	public void setChiave_di_registrazione(String chiave_di_registrazione) {
		this.chiave_di_registrazione = chiave_di_registrazione;
	}
}
