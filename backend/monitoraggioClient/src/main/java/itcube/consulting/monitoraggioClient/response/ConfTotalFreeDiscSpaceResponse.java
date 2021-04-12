package itcube.consulting.monitoraggioClient.response;

import java.time.LocalDateTime;

public class ConfTotalFreeDiscSpaceResponse {
	private String driveLabel;
	private String occupiedSpace;
	private LocalDateTime lastUpdate;
	private String totalSpace;
	private String message;
	private Integer messageCode;
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(Integer messageCode) {
		this.messageCode = messageCode;
	}
	public String getDriveLabel() {
		return driveLabel;
	}
	public void setDriveLabel(String driveLabel) {
		this.driveLabel = driveLabel;
	}
	public String getOccupiedSpace() {
		return occupiedSpace;
	}
	public void setOccupiedSpace(String occupiedSpace) {
		this.occupiedSpace = occupiedSpace;
	}
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getTotalSpace() {
		return totalSpace;
	}
	public void setTotalSpace(String totalSpace) {
		this.totalSpace = totalSpace;
	}
}
