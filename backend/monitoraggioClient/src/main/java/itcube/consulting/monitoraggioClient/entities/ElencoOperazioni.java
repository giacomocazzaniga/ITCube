package itcube.consulting.monitoraggioClient.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "elenco_operazioni")
public class ElencoOperazioni {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(mappedBy = "elencoOperazioni")
	private Config config;
	
	private String nome;
	private String tabella_riferimento;

	/*@ManyToOne
	@JoinColumn(name = "licenza_riferimento")
	private TipologieLicenze tipologieLicenze;*/
	
	@ManyToMany(mappedBy = "elencoOperazioni", fetch = FetchType.LAZY)
	private List<TipologieLicenze> elencoTipologieLicenze;
	
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

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public List<TipologieLicenze> getElencoTipologieLicenze() {
		return elencoTipologieLicenze;
	}

	public void setElencoTipologieLicenze(List<TipologieLicenze> elencoTipologieLicenze) {
		this.elencoTipologieLicenze = elencoTipologieLicenze;
	}
	
	/*public TipologieLicenze getTipologieLicenze() {
		return tipologieLicenze;
	}

	public void setTipologieLicenze(TipologieLicenze tipologieLicenze) {
		this.tipologieLicenze = tipologieLicenze;
	}*/
	
	/*public String getId_tipologia_licenze() {
		return id_tipologia_licenze;
	}

	public void setId_tipologia_licenze(String id_tipologia_licenze) {
		this.id_tipologia_licenze = id_tipologia_licenze;
	}*/
}
