package itcube.consulting.monitoraggioClient.response;

import java.time.LocalDateTime;
import java.util.List;

import itcube.consulting.monitoraggioClient.entities.ConfTotalFreeDiscSpace;

public class ConfTotalFreeDiscSpaceResponse {
	
	public static class ConfTotalFreeDiscSpaceDTO {
		private String driveLabel;
		private String occupiedSpace;
		private LocalDateTime lastUpdate;
		private String totalSpace;
		
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
	
	private List<ConfTotalFreeDiscSpaceDTO> drives;
	private String message;
	private Integer messageCode;
	private String token;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
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
	
	public List<ConfTotalFreeDiscSpaceDTO> getDrives() {
		return drives;
	}
	public void setDrives(List<ConfTotalFreeDiscSpaceDTO> drives) {
		this.drives = drives;
	}
	
}
