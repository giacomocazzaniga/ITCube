package itcube.consulting.monitoraggioClient.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class VisualizzazioneEventi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "macro_categoria")
	private MacroCategorieEventi macroCategorieEventi;
	//private int macro_categoria;
	
	private int sottocategoria;
	private int level;
	private LocalDateTime date_and_time;
	private LocalDateTime date_and_time_evento;
	private String source;
	private int id_event;
	private String task_category;
	private String info;
	
	@OneToOne
	@JoinColumn(name = "id_client")
	private ElencoClients elencoClients;
	//private int id_client;

	public VisualizzazioneEventi() {

	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public ElencoClients getElencoClients() {
		return elencoClients;
	}

	public void setElencoClients(ElencoClients elencoClients) {
		this.elencoClients = elencoClients;
	}
	
	/*public int getMacro_categoria() {
		return macro_categoria;
	}
	public void setMacro_categoria(int macro_categoria) {
		this.macro_categoria = macro_categoria;
	}*/
	
	public MacroCategorieEventi getMacroCategorieEventi() {
		return macroCategorieEventi;
	}

	public void setMacroCategorieEventi(MacroCategorieEventi macroCategorieEventi) {
		this.macroCategorieEventi = macroCategorieEventi;
	}
	
	public int getSottocategoria() {
		return sottocategoria;
	}

	public void setSottocategoria(int sottocategoria) {
		this.sottocategoria = sottocategoria;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public LocalDateTime getDate_and_time() {
		return date_and_time;
	}
	public void setDate_and_time(LocalDateTime date_and_time) {
		this.date_and_time = date_and_time;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getId_event() {
		return id_event;
	}
	public void setId_event(int id_event) {
		this.id_event = id_event;
	}
	public String getTask_category() {
		return task_category;
	}
	public void setTask_category(String task_category) {
		this.task_category = task_category;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	public LocalDateTime getDate_and_time_evento() {
		return date_and_time_evento;
	}

	public void setDate_and_time_evento(LocalDateTime date_and_time_evento) {
		this.date_and_time_evento = date_and_time_evento;
	}
	
	/*public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}*/
}
