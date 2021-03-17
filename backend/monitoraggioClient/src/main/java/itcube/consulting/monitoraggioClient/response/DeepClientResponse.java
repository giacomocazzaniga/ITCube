package itcube.consulting.monitoraggioClient.response;

import java.util.List;

public class DeepClientResponse extends GeneralResponse{
	private String nome;
	private String nome_tipologia;
	private String mac_address;
	private String sede;
	private List<String> codice_licenza;
	private List<Integer> classe_licenza;
	private int servizi_attivi;
	private int servizi_esecuzione;
	private int servizi_problemi;
	private int servizi_warning;
	
	public DeepClientResponse() {

	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome_tipologia() {
		return nome_tipologia;
	}
	public void setNome_tipologia(String nome_tipologia) {
		this.nome_tipologia = nome_tipologia;
	}
	public String getMac_address() {
		return mac_address;
	}
	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	
	public List<String> getCodice_licenza() {
		return codice_licenza;
	}

	public void setCodice_licenza(List<String> codice_licenza) {
		this.codice_licenza = codice_licenza;
	}

	
	public List<Integer> getClasse_licenza() {
		return classe_licenza;
	}

	public void setClasse_licenza(List<Integer> classe_licenza) {
		this.classe_licenza = classe_licenza;
	}

	public int getServizi_attivi() {
		return servizi_attivi;
	}
	public void setServizi_attivi(int servizi_attivi) {
		this.servizi_attivi = servizi_attivi;
	}
	public int getServizi_esecuzione() {
		return servizi_esecuzione;
	}
	public void setServizi_esecuzione(int servizi_esecuzione) {
		this.servizi_esecuzione = servizi_esecuzione;
	}
	public int getServizi_problemi() {
		return servizi_problemi;
	}
	public void setServizi_problemi(int servizi_problemi) {
		this.servizi_problemi = servizi_problemi;
	}
	public int getServizi_warning() {
		return servizi_warning;
	}
	public void setServizi_warning(int servizi_warning) {
		this.servizi_warning = servizi_warning;
	}
	
	
}
