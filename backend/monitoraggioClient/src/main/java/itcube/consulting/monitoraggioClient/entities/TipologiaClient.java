package itcube.consulting.monitoraggioClient.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TipologiaClient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "tipologiaClient")
	private List<ElencoClients> elencoClients;
	
	private String nome_tipologia;
	
	@ManyToOne
	@JoinColumn(name = "id_company")
	private ElencoCompanies company;
	
	public TipologiaClient() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public List<ElencoClients> getElencoClients() {
		return elencoClients;
	}
	public void setElencoClients(List<ElencoClients> elencoClients) {
		this.elencoClients = elencoClients;
	} 
	
	public String getNome_tipologia() {
		return nome_tipologia;
	}
	public void setNome_tipologia(String nome_tipologia) {
		this.nome_tipologia = nome_tipologia;
	}
	public ElencoCompanies getId_company() {
		return company;
	}
	public void setId_company(ElencoCompanies id_company) {
		this.company = id_company;
	}
	
	
}
