package itcube.consulting.monitoraggioClient.response;

import java.util.List;

import itcube.consulting.monitoraggioClient.entities.ElencoClients;

public class ResponseLogin extends GeneralResponse{
	private String ragione_sociale;
	private int id_company;
	List<ElencoClients> elencoClients;
	
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

	public List<ElencoClients> getElencoClients() {
		return elencoClients;
	}

	public void setElencoClients(List<ElencoClients> elencoClients) {
		this.elencoClients = elencoClients;
	}
	
}
