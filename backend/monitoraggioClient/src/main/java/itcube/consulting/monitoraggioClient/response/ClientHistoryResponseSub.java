package itcube.consulting.monitoraggioClient.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClientHistoryResponseSub {
	
	private LocalDate date;
	private int errors;
	private int warnings;
	
	
	
	public ClientHistoryResponseSub(LocalDate date, int errors, int warnings) {
		super();
		this.date = date;
		this.errors = errors;
		this.warnings = warnings;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getErrors() {
		return errors;
	}
	public void setErrors(int errors) {
		this.errors = errors;
	}
	public int getWarnings() {
		return warnings;
	}
	public void setWarnings(int warnings) {
		this.warnings = warnings;
	}
	
	
	
}
