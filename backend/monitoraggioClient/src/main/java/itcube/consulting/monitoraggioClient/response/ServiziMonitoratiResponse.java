package itcube.consulting.monitoraggioClient.response;

import java.util.List;

import itcube.consulting.monitoraggioClient.entities.Monitoraggio;

public class ServiziMonitoratiResponse extends GeneralResponse{
	
	private List<Monitoraggio> monitoraggi;

	public List<Monitoraggio> getMonitoraggi() {
		return monitoraggi;
	}

	public void setMonitoraggi(List<Monitoraggio> monitoraggi) {
		this.monitoraggi = monitoraggi;
	}
}
