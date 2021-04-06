package itcube.consulting.monitoraggioClient.response;

import java.util.HashMap;

public class EventiOverviewResponse extends GeneralResponse{
	private int problemi_oggi;
	private int warning_oggi;
	HashMap<Integer, Integer> tot_per_sottocategoria=new HashMap<Integer, Integer>();
	
	public EventiOverviewResponse() {
		super();
	}
	
	public int getProblemi_oggi() {
		return problemi_oggi;
	}
	public void setProblemi_oggi(int problemi_oggi) {
		this.problemi_oggi = problemi_oggi;
	}
	public int getWarning_oggi() {
		return warning_oggi;
	}
	public void setWarning_oggi(int warning_oggi) {
		this.warning_oggi = warning_oggi;
	}
	public HashMap<Integer, Integer> getTot_per_sottocategoria() {
		return tot_per_sottocategoria;
	}
	public void setTot_per_sottocategoria(HashMap<Integer, Integer> tot_per_sottocategoria) {
		this.tot_per_sottocategoria = tot_per_sottocategoria;
	}
	
	
}
