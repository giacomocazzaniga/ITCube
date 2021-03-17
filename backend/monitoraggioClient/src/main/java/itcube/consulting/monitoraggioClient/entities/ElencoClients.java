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
import javax.persistence.Transient;

@Entity
public class ElencoClients {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(mappedBy = "elencoClients")
	private Config config;
	
	@OneToOne(mappedBy = "elencoClients")
	private ConfTotalFreeDiscSpace confTotalFreeDiscSpace;
	
	@OneToOne(mappedBy = "elencoClients")
	private ConfWindowsServices confWindowsServices;
	
	@OneToOne(mappedBy = "elencoClients")
	private VisualizzazioneEventi visualizzazioneEventi;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "tipologiaClient")
	private TipologiaClient tipologiaClient;
	
	@ManyToOne
	@JoinColumn(name = "id_company")
	private ElencoCompanies elencoCompanies;
	//private int id_company;
	
	private String mac_address;
	
	//Chiave esterna licenza_in_uso 
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "elencoClients_elencoLicenze",
     joinColumns = { @JoinColumn(name = "licenza_in_uso",nullable = false, updatable = false)},
     inverseJoinColumns = { @JoinColumn(name = "id",nullable = false, updatable = false)})
	private List<ElencoLicenze> elencoLicenze;
	
	private String sede;

	public ElencoClients() {
		
	}
	
	public TipologiaClient getTipologiaClient() {
		return tipologiaClient;
	}

	public void setTipologiaClient(TipologiaClient tipologiaClient) {
		this.tipologiaClient = tipologiaClient;
	}

	
	public VisualizzazioneEventi getVisualizzazioneEventi() {
		return visualizzazioneEventi;
	}

	public void setVisualizzazioneEventi(VisualizzazioneEventi visualizzazioneEventi) {
		this.visualizzazioneEventi = visualizzazioneEventi;
	}
	
	public List<ElencoLicenze> getElencoLicenze() {
		return elencoLicenze;
	}

	public void setElencoLicenze(List<ElencoLicenze> elencoLicenze) {
		this.elencoLicenze = elencoLicenze;
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

	/*public int getId_Company() {
		return id_company;
	}

	public void setId_Company(int id_company) {
		this.id_company = id_company;
	}*/

	public String getMac_address() {
		return mac_address;
	}

	public void setMac_address(String mac_address) {
		mac_address = mac_address;
	}

	public ElencoCompanies getElencoCompanies() {
		return elencoCompanies;
	}

	public void setElencoCompanies(ElencoCompanies elencoCompanies) {
		this.elencoCompanies = elencoCompanies;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public ConfTotalFreeDiscSpace getConfTotalFreeDiscSpace() {
		return confTotalFreeDiscSpace;
	}

	public void setConfTotalFreeDiscSpace(ConfTotalFreeDiscSpace confTotalFreeDiscSpace) {
		this.confTotalFreeDiscSpace = confTotalFreeDiscSpace;
	}

	public ConfWindowsServices getConfWindowsServices() {
		return confWindowsServices;
	}

	public void setConfWindowsServices(ConfWindowsServices confWindowsServices) {
		this.confWindowsServices = confWindowsServices;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}
	
	public Boolean hasLicenza (TipologieLicenze licenza) {
		if (this.getElencoLicenze().contains(licenza)) 
			return true;
		return false;
	}
	
}