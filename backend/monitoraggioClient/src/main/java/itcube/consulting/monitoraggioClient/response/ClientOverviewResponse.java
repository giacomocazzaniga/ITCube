package itcube.consulting.monitoraggioClient.response;

public class ClientOverviewResponse extends GeneralResponse {
	
	private ClientOverviewResponseSub servicesResponse;
	private ClientOverviewResponseSub eventiResponse;
	private ClientOverviewResponseSub drivesResponse;
	
	
	public ClientOverviewResponseSub getServicesResponse() {
		return servicesResponse;
	}
	public void setServicesResponse(ClientOverviewResponseSub servicesResponse) {
		this.servicesResponse = servicesResponse;
	}
	public ClientOverviewResponseSub getEventiResponse() {
		return eventiResponse;
	}
	public void setEventiResponse(ClientOverviewResponseSub eventiResponse) {
		this.eventiResponse = eventiResponse;
	}
	public ClientOverviewResponseSub getDrivesResponse() {
		return drivesResponse;
	}
	public void setDrivesResponse(ClientOverviewResponseSub drivesResponse) {
		this.drivesResponse = drivesResponse;
	}
	
}
