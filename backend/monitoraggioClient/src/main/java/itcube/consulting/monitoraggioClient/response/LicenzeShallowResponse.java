package itcube.consulting.monitoraggioClient.response;

import java.util.*;
import itcube.consulting.monitoraggioClient.entities.database.LicenzaShallow;

public class LicenzeShallowResponse extends GeneralResponse {
	
	private List<LicenzaShallow> licenzeShallow;

	public List<LicenzaShallow> getLicenzeShallow() {
		return licenzeShallow;
	}

	public void setLicenzeShallow(List<LicenzaShallow> licenzeShallow) {
		this.licenzeShallow = licenzeShallow;
	}
	
	public LicenzeShallowResponse () { }

}