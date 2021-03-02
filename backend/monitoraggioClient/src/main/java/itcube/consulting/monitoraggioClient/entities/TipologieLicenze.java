package itcube.consulting.monitoraggioClient.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TipologieLicenze implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "tipologieLicenze")
	private List<ElencoOperazioni> elencoOperazioni;
	
	@OneToMany(mappedBy = "tipologieLicenze")
	private List<ElencoLicenze> elencoLicenze;
	
	private int classe;
	private String nome_tipologia;

	public TipologieLicenze() { 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClasse() {
		return classe;
	}

	public void setClasse(int classe) {
		this.classe = classe;
	}

	public String getNome_tipologia() {
		return nome_tipologia;
	}

	public void setNome_tipologia(String nome_tipologia) {
		this.nome_tipologia = nome_tipologia;
	}

	public List<ElencoOperazioni> getElencoOperazioni() {
		return elencoOperazioni;
	}

	public void setElencoOperazioni(List<ElencoOperazioni> elencoOperazioni) {
		this.elencoOperazioni = elencoOperazioni;
	}

	public List<ElencoLicenze> getElencoLicenze() {
		return elencoLicenze;
	}

	public void setElencoLicenze(List<ElencoLicenze> elencoLicenze) {
		this.elencoLicenze = elencoLicenze;
	}
	
	
}