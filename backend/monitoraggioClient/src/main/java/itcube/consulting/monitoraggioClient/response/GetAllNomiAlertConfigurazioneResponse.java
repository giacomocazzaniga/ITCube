package itcube.consulting.monitoraggioClient.response;

import java.util.List;

public class GetAllNomiAlertConfigurazioneResponse extends GeneralResponse{

	List<String> tipologie_alert;

	public List<String> getTipologie_alert() {
		return tipologie_alert;
	}

	public void setTipologie_alert(List<String> tipologie_alert) {
		this.tipologie_alert = tipologie_alert;
	}
	
	
}
