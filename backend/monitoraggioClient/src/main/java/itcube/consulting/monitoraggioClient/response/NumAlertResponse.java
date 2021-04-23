package itcube.consulting.monitoraggioClient.response;

public class NumAlertResponse extends GeneralResponse{
	private int error;
	private int warning;
	private int ok;
	
	public NumAlertResponse() {
		super();
	}
	
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public int getWarning() {
		return warning;
	}
	public void setWarning(int warning) {
		this.warning = warning;
	}
	public int getOk() {
		return ok;
	}
	public void setOk(int ok) {
		this.ok = ok;
	}
	
	
}
