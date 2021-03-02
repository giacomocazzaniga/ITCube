package itcube.consulting.monitoraggioClient.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ElencoOperazioni implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(mappedBy = "elencoOperazioni")
	private Config config;
	
	private String nome;
	private String tabella_riferimento;

	@ManyToOne
	@JoinColumn(name = "classe_licenza_minima")
	private TipologieLicenze tipologieLicenze;
	
	//private String id_tipologia_licenze;
	
	public ElencoOperazioni() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTabella_riferimento() {
		return tabella_riferimento;
	}

	public void setTabella_riferimento(String tabella_riferimento) {
		this.tabella_riferimento = tabella_riferimento;
	}

	public TipologieLicenze getTipologieLicenze() {
		return tipologieLicenze;
	}

	public void setTipologieLicenze(TipologieLicenze tipologieLicenze) {
		this.tipologieLicenze = tipologieLicenze;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	/*public String getId_tipologia_licenze() {
		return id_tipologia_licenze;
	}

	public void setId_tipologia_licenze(String id_tipologia_licenze) {
		this.id_tipologia_licenze = id_tipologia_licenze;
	}*/
}
