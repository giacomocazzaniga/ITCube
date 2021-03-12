package itcube.consulting.monitoraggioClient.entities.database;

public class ValidToken {
	private String token;
	private boolean valid;
	
	public ValidToken() {
		
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
