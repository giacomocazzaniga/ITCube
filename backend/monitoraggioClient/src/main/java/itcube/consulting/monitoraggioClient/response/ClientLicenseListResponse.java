package itcube.consulting.monitoraggioClient.response;

import java.util.*;

public class ClientLicenseListResponse extends GeneralResponse{
	private HashMap<Integer,Integer> sedi;
	
	public ClientLicenseListResponse() {
		super();
	}

	public HashMap<Integer, Integer> getSedi() {
		return sedi;
	}

	public void setSedi(HashMap<Integer, Integer> sedi) {
		this.sedi = sedi;
	}
}	