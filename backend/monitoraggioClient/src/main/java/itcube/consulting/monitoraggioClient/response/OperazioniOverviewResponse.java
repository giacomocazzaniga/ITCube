package itcube.consulting.monitoraggioClient.response;

public class OperazioniOverviewResponse extends GeneralResponse{
	private int attive;
	private int esecuzione;
	private int problemi;
	private int warning;
	
	public OperazioniOverviewResponse() {
		super();
	}
	public int getAttive() {
		return attive;
	}
	public void setAttive(int attive) {
		this.attive = attive;
	}
	public int getEsecuzione() {
		return esecuzione;
	}
	public void setEsecuzione(int esecuzione) {
		this.esecuzione = esecuzione;
	}
	public int getProblemi() {
		return problemi;
	}
	public void setProblemi(int problemi) {
		this.problemi = problemi;
	}
	public int getWarning() {
		return warning;
	}
	public void setWarning(int warning) {
		this.warning = warning;
	}
}
