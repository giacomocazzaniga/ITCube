package itcube.consulting.monitoraggioClient.response;

import java.util.List;

public class GetClientNameOfCompanyResponse extends GeneralResponse{

	private List<String> nomi_servizi;
	private int servizi_length;

	public int getServizi_length() {
		return servizi_length;
	}

	public void setServizi_length(int servizi_length) {
		this.servizi_length = servizi_length;
	}

	public List<String> getNomi_servizi() {
		return nomi_servizi;
	}

	public void setNomi_servizi(List<String> nomi_servizi) {
		this.nomi_servizi = nomi_servizi;
	}
	
	
}
