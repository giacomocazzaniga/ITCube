package itcube.consulting.monitoraggioClient.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_company")
	private ElencoCompanies elencoCompanies;
	//private int id_company;
	
	private String MAC_address;
	//Chiave esterna licenza_in_uso 
	@ManyToOne
	@JoinColumn(name = "licenza_in_uso")
	private ElencoLicenze elencoLicenze;

	public ElencoLicenze getElencoLicenze() {
		return elencoLicenze;
	}

	public void setElencoLicenze(ElencoLicenze elencoLicenze) {
		this.elencoLicenze = elencoLicenze;
	}

	public ElencoClients() {
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

	public String getMAC_address() {
		return MAC_address;
	}

	public void setMAC_address(String mAC_address) {
		MAC_address = mAC_address;
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
}