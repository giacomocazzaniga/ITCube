package itcube.consulting.monitoraggioClient.response;

import java.util.List;

public class CompanyOverviewResponse extends GeneralResponse{
	private List<Integer> errori;
	private List<Integer> warning;
	private List<Integer> ok;
	
	
	public List<Integer> getErrori() {
		return errori;
	}
	public void setErrori(List<Integer> clients_with_error) {
		this.errori = clients_with_error;
	}
	public List<Integer> getWarning() {
		return warning;
	}
	public void setWarning(List<Integer> warning) {
		this.warning = warning;
	}
	public List<Integer> getOk() {
		return ok;
	}
	public void setOk(List<Integer> ok) {
		this.ok = ok;
	}	
}
