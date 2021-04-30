package itcube.consulting.monitoraggioClient.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ClientHistoryResponse extends GeneralResponse{

	private String last_update;
	private List<ClientHistoryResponseSub> history;

	public List<ClientHistoryResponseSub> getHistory() {
		return history;
	}

	public void setHistory(List<ClientHistoryResponseSub> history) {
		this.history = history;
	}

	public String getLast_update() {
		return last_update;
	}

	public void setLast_update(String date) {
		this.last_update = date;
	}
	
}
