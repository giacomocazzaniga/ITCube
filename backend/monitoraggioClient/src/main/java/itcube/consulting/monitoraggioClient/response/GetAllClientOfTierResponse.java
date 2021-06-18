package itcube.consulting.monitoraggioClient.response;

import java.util.List;

import itcube.consulting.monitoraggioClient.entities.ElencoClients;

public class GetAllClientOfTierResponse extends GeneralResponse {
		
	private List<ElencoClients> clientsOfTier;

	public List<ElencoClients> getClientsOfTier() {
		return clientsOfTier;
	}

	public void setClientsOfTier(List<ElencoClients> clientsOfTier) {
		this.clientsOfTier = clientsOfTier;
	}
	
}
