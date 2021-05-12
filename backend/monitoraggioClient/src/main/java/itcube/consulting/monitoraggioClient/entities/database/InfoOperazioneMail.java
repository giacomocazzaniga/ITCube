package itcube.consulting.monitoraggioClient.entities.database;

public class InfoOperazioneMail {

	private String tipo_alert;
	private String tipo_operazione;
	private String operazione;
	
	public InfoOperazioneMail(String tipo_alert, String tipo_operazione, String operazione) {
		this.tipo_alert = tipo_alert;
		this.tipo_operazione = tipo_operazione;
		this.operazione = operazione;
	}
	
	public String getTipo_alert() {
		return tipo_alert;
	}
	public void setTipo_alert(String tipo_alert) {
		this.tipo_alert = tipo_alert;
	}
	public String getTipo_operazione() {
		return tipo_operazione;
	}
	public void setTipo_operazione(String tipo_operazione) {
		this.tipo_operazione = tipo_operazione;
	}
	public String getOperazione() {
		return operazione;
	}
	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}
	
}
