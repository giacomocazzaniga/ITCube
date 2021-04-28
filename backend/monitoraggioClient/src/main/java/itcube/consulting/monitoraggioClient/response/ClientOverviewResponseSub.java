package itcube.consulting.monitoraggioClient.response;

public class ClientOverviewResponseSub {
	private int errori;
	private int warning;
	private int ok;
	
	
	public int getErrori() {
		return errori;
	}
	public void setErrori(int errori) {
		this.errori = errori;
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