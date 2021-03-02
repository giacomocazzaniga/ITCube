package itcube.consulting.monitoraggioClient.response;

public class GeneralResponse {
	private String message;
	private int messageCode;
	String token;
	
	/*public GeneralResponse(String message, int messageCode) {
		this.message = message;
		this.messageCode = messageCode;
	}*/

	public GeneralResponse() {
	
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(int messageCode) {
		this.messageCode = messageCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
