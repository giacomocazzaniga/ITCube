package itcube.consulting.monitoraggioClient.response;

import java.util.List;

import itcube.consulting.monitoraggioClient.entities.AlertConfigurazione;

public class AlertConfigurazioneResponse extends GeneralResponse{
	
	private List<AlertConfigurazione> listaOperazioni;

	public List<AlertConfigurazione> getListaOperazioni() {
		return listaOperazioni;
	}

	public void setListaOperazioni(List<AlertConfigurazione> listaOperazioni) {
		this.listaOperazioni = listaOperazioni;
	}
}
