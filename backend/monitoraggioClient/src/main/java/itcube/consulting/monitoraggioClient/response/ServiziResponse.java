package itcube.consulting.monitoraggioClient.response;

import java.util.List;

import itcube.consulting.monitoraggioClient.entities.ConfWindowsServices;

public class ServiziResponse extends GeneralResponse {
	private List<ConfWindowsServices> confWindowsSerives;
	
	public ServiziResponse() {
		
	}

	public List<ConfWindowsServices> getConfWindowsSerives() {
		return confWindowsSerives;
	}

	public void setConfWindowsSerives(List<ConfWindowsServices> confWindowsSerives) {
		this.confWindowsSerives = confWindowsSerives;
	}
}
