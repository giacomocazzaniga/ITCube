package itcube.consulting.monitoraggioClient.response;

import itcube.consulting.monitoraggioClient.entities.ElencoLicenze;
import java.util.*;
import itcube.consulting.monitoraggioClient.entities.database.LicenzeDeep;

public class LicenzeDeepResponse extends GeneralResponse {
	
	private List<LicenzeDeep> elencoLicenze;

	public List<LicenzeDeep> getElencoLicenze() {
		return elencoLicenze;
	}

	public void setElencoLicenze(List<LicenzeDeep> elencoLicenze) {
		this.elencoLicenze = elencoLicenze;
	}
	
}
