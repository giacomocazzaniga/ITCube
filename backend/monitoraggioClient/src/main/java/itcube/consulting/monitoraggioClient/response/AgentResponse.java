package itcube.consulting.monitoraggioClient.response;

public class AgentResponse {

	private Integer MyID;
	private String Message;
	private int MessageCode;
	
	public AgentResponse() {
		super();
	}
	
	
	public Integer getMyID() {
		return MyID;
	}
	public void setMyID(Integer myID) {
		MyID = myID;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public int getMessageCode() {
		return MessageCode;
	}
	public void setMessageCode(int messageCode) {
		MessageCode = messageCode;
	}
}
