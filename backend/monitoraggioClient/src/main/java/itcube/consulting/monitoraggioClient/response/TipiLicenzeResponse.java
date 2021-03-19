package itcube.consulting.monitoraggioClient.response;

import java.util.HashMap;

public class TipiLicenzeResponse extends GeneralResponse{
	HashMap<Integer, String> tipi;

	public TipiLicenzeResponse() {
		super();
	}

	public HashMap<Integer, String> getTipi() {
		return tipi;
	}

	public void setTipi(HashMap<Integer, String> tipi) {
		this.tipi = tipi;
	}
}
