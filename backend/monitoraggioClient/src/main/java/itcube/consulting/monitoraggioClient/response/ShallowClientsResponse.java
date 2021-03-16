package itcube.consulting.monitoraggioClient.response;

import java.util.List;

import itcube.consulting.monitoraggioClient.entities.database.ShallowClient;

public class ShallowClientsResponse extends GeneralResponse {
	
	private List<ShallowClient> shallowClients;

	public List<ShallowClient> getShallowClients() {
		return shallowClients;
	}

	public void setShallowClients(List<ShallowClient> shallowClients) {
		this.shallowClients = shallowClients;
	}
	
	public ShallowClientsResponse () { }

}
