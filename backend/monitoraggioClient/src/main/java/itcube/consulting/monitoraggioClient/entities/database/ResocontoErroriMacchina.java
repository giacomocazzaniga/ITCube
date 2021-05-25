package itcube.consulting.monitoraggioClient.entities.database;

public class ResocontoErroriMacchina {

	private String tipo;
	private int errori_servizi;
	private int warning_servizi;
	private int errori_eventi;
	private int warning_eventi;
	private int errori_drives;
	private int warning_drives;
	
	public ResocontoErroriMacchina(String tipo, int errori_servizi, int warning_servizi, int errori_eventi,
			int warning_eventi, int errori_drives, int warning_drives) {
		super();
		this.tipo = tipo;
		this.errori_servizi = errori_servizi;
		this.warning_servizi = warning_servizi;
		this.errori_eventi = errori_eventi;
		this.warning_eventi = warning_eventi;
		this.errori_drives = errori_drives;
		this.warning_drives = warning_drives;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getErrori_servizi() {
		return errori_servizi;
	}

	public void setErrori_servizi(int errori_servizi) {
		this.errori_servizi = errori_servizi;
	}

	public int getWarning_servizi() {
		return warning_servizi;
	}

	public void setWarning_servizi(int warning_servizi) {
		this.warning_servizi = warning_servizi;
	}

	public int getErrori_eventi() {
		return errori_eventi;
	}

	public void setErrori_eventi(int errori_eventi) {
		this.errori_eventi = errori_eventi;
	}

	public int getWarning_eventi() {
		return warning_eventi;
	}

	public void setWarning_eventi(int warning_eventi) {
		this.warning_eventi = warning_eventi;
	}

	public int getErrori_drives() {
		return errori_drives;
	}

	public void setErrori_drives(int errori_drives) {
		this.errori_drives = errori_drives;
	}

	public int getWarning_drives() {
		return warning_drives;
	}

	public void setWarning_drives(int warning_drives) {
		this.warning_drives = warning_drives;
	}
	
	
}
