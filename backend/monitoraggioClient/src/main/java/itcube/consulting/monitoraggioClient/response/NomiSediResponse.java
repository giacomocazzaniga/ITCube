package itcube.consulting.monitoraggioClient.response;

import java.util.List;

public class NomiSediResponse extends GeneralResponse{
	List<String> sedi;
	
	public NomiSediResponse() {
		super();
	}

	public List<String> getSedi() {
		return sedi;
	}

	public void setSedi(List<String> sedi) {
		this.sedi = sedi;
	}
}
