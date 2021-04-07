package itcube.consulting.monitoraggioClient.response;

public class ServiziOverviewResponse extends GeneralResponse{
	private int n_totali;
	private int n_running;
	private int n_stopped;
	private int n_monitorati;

	public ServiziOverviewResponse() {
	}
	public int getN_totali() {
		return n_totali;
	}
	public void setN_totali(int n_totali) {
		this.n_totali = n_totali;
	}
	public int getN_running() {
		return n_running;
	}
	public void setN_running(int n_running) {
		this.n_running = n_running;
	}
	public int getN_stopped() {
		return n_stopped;
	}
	public void setN_stopped(int n_stopped) {
		this.n_stopped = n_stopped;
	}
	public int getN_monitorati() {
		return n_monitorati;
	}
	public void setN_monitorati(int n_monitorati) {
		this.n_monitorati = n_monitorati;
	}
	
	
}
