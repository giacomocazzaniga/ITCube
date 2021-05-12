package itcube.consulting.monitoraggioClient.response;

import java.time.LocalDateTime;

public class MaxDateResponse extends GeneralResponse{

	private LocalDateTime maxDate;

	public LocalDateTime getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(LocalDateTime maxDate) {
		this.maxDate = maxDate;
	}
	
	
}
