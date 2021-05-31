package itcube.consulting.monitoraggioClient.response;

import java.time.LocalDateTime;

public class GetLastMailDateResponse extends GeneralResponse {
	
	private LocalDateTime last_mail_date_and_time;

	public LocalDateTime getLast_mail_date_and_time() {
		return last_mail_date_and_time;
	}

	public void setLast_mail_date_and_time(LocalDateTime last_mail_date_and_time) {
		this.last_mail_date_and_time = last_mail_date_and_time;
	}
	
}
