package itcube.consulting.monitoraggioClient.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ElencoLicenze {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToMany(mappedBy = "elencoLicenze", fetch = FetchType.LAZY)
	private Set<ElencoClients> elencoClients;

	/*
	@OneToMany(mappedBy = "elencoLicenze")
	private List<ElencoClients> elencoClients; */
	private String codice;

	@ManyToOne
	@JoinColumn(name = "id_tipo")
	private TipologieLicenze tipologieLicenze;
	//private int id_tipo;

	@ManyToOne
	@JoinColumn(name = "acquistato_da")
	private ElencoCompanies elencoCompanies;
	//private String acquistato_da;
	
	private Date scadenza;

	public ElencoLicenze() {

	}
	
	public Set<ElencoClients> getElencoClients() {
		return elencoClients;
	}

	public void setElencoClients(Set<ElencoClients> elencoClients) {
		this.elencoClients = elencoClients;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public int getId_tipo() {
		return id_tipo;
	}

	public void setId_tipo(int id_tipo) {
		this.id_tipo = id_tipo;
	}*/

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public ElencoCompanies getElencoCompanies() {
		return elencoCompanies;
	}

	/*public List<String> getCodice() {
		return codice;
	}

	public void setCodice(List<String> codice) {
		this.codice = codice;
	}*/

	public void setElencoCompanies(ElencoCompanies elencoCompanies) {
		this.elencoCompanies = elencoCompanies;
	}

	public TipologieLicenze getTipologieLicenze() {
		return tipologieLicenze;
	}

	public void setTipologieLicenze(TipologieLicenze tipologieLicenze) {
		this.tipologieLicenze = tipologieLicenze;
	}

	public Date getScadenza() {
		return scadenza;
	}

	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}
	
}