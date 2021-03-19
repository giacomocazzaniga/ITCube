package itcube.consulting.monitoraggioClient.response;

public class CompraLicenzaResponse extends GeneralResponse {
	String codice;
	
	public CompraLicenzaResponse() {
		super();
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	
}
