package itcube.consulting.monitoraggioClient.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MacroCategorieEventi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	/*@OneToMany(mappedBy = "macroCategorieEventi")
	private List<VisualizzazioneEventi> visualizzazioneEventi;
	//private int classe;
	
	public List<VisualizzazioneEventi> getVisualizzazioneEventi() {
		return visualizzazioneEventi;
	}
	public void setVisualizzazioneEventi(List<VisualizzazioneEventi> visualizzazioneEventi) {
		this.visualizzazioneEventi = visualizzazioneEventi;
	}*/
	private String nome_categoria;
	
	public MacroCategorieEventi() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*public int getClasse() {
		return classe;
	}
	public void setClasse(int classe) {
		this.classe = classe;
	}*/
	public String getNome_categoria() {
		return nome_categoria;
	}
	public void setNome_categoria(String nome_categoria) {
		this.nome_categoria = nome_categoria;
	}
}
