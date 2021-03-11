package itcube.consulting.monitoraggioClient.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "tipologie_licenze")
public class TipologieLicenze {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "elencoOperazioni_tipologieLicenze",
     joinColumns = { @JoinColumn(name = "licenza_riferimento",nullable = false, updatable = false)},
     inverseJoinColumns = { @JoinColumn(name = "classe",nullable = false, updatable = false)})
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