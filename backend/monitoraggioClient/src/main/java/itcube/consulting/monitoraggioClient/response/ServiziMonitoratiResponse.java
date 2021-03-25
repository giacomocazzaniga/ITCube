package itcube.consulting.monitoraggioClient.response;

import java.util.List;

import itcube.consulting.monitoraggioClient.entities.ConfWindowsServices;

public class ServiziMonitoratiResponse extends GeneralResponse {
	private List<ConfWindowsServices> confWindowsSerives;
	
	public ServiziMonitoratiResponse() {
		
	}

	public List<ConfWindowsServices> getConfWindowsSerives() {
		return confWindowsSerives;
	}

	public void setConfWindowsSerives(List<ConfWindowsServices> confWindowsSerives) {
		this.confWindowsSerives = confWindowsSerives;
	}
}
