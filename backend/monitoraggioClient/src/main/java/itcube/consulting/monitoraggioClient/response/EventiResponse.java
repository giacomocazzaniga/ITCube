package itcube.consulting.monitoraggioClient.response;

import java.util.List;

import itcube.consulting.monitoraggioClient.entities.VisualizzazioneEventi;

public class EventiResponse extends GeneralResponse{
	
	private List<VisualizzazioneEventi> eventi;
	
	public EventiResponse () {
		
	}

	public List<VisualizzazioneEventi> getEventi() {
		return eventi;
	}

	public void setEventi(List<VisualizzazioneEventi> eventi) {
		this.eventi = eventi;
	}
	
}
