package itcube.consulting.monitoraggioClient.response;

import java.util.List;

import itcube.consulting.monitoraggioClient.entities.ConfWindowsServices;

public class ServiziResponse extends GeneralResponse {
	private List<ConfWindowsServices> confWindowsServices;
	
	public ServiziResponse() {
		
	}

	public List<ConfWindowsServices> getConfWindowsSerives() {
		return confWindowsServices;
	}

	public void setConfWindowsSerives(List<ConfWindowsServices> confWindowsSerives) {
		this.confWindowsServices = confWindowsSerives;
	}
}
