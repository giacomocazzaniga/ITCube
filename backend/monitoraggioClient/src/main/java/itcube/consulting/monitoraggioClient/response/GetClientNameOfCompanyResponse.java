package itcube.consulting.monitoraggioClient.response;

import java.util.List;

public class GetClientNameOfCompanyResponse extends GeneralResponse{

	List<String> nomi_servizi;

	public List<String> getNomi_servizi() {
		return nomi_servizi;
	}

	public void setNomi_servizi(List<String> nomi_servizi) {
		this.nomi_servizi = nomi_servizi;
	}
	
	
}
