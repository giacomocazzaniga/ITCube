package itcube.consulting.monitoraggioClient.entities.database;

import java.util.ArrayList;
import java.util.List;

import itcube.consulting.monitoraggioClient.entities.ElencoClients;

public class ShallowClient {
	
	private Integer id_client;
	private String nome_client;
	private String tipo_client;
	private String sede;
	private List<String> classe_licenza;
	
	public Integer getId_client() {
		return id_client;
	}
	public void setId_client(Integer id_client) {
		this.id_client = id_client;
	}
	public String getNome_client() {
		return nome_client;
	}
	public void setNome_client(String nome_client) {
		this.nome_client = nome_client;
	}
	public String getTipo_client() {
		return tipo_client;
	}
	public void setTipo_client(String tipo_client) {
		this.tipo_client = tipo_client;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public List<String> getClasse_licenza() {
		return classe_licenza;
	}
	public void setClasse_licenza(List<String> classe_licenza) {
		this.classe_licenza = classe_licenza;
	}
	
	//Converte da una lista ElencoClients a ShallowClients
	public static List<ShallowClient> getShallowClients (List<ElencoClients> elencoClients) {
		List<ShallowClient> shallowClients = new ArrayList<>();
		List<String> classiLicenze = new ArrayList<>();
		
		for (int i = 0; i < elencoClients.size(); i++) {
						
			for (int j = 0; j < elencoClients.get(i).getElencoLicenze().size(); j++)
				classiLicenze.add(elencoClients.get(i).getElencoLicenze().get(j).getTipologieLicenze().getNome_tipologia());
			
			shallowClients.add(new ShallowClient(
					elencoClients.get(i).getId(),
					elencoClients.get(i).getNome(),
					elencoClients.get(i).getTipologiaClient().getNome_tipologia(),
					elencoClients.get(i).getSede(),
					classiLicenze
			));
		}
		
		return shallowClients;
	}
	
	public ShallowClient (Integer id_client, String nome_client, String tipo_client, String sede, List<String> classe_licenza) {
		this.id_client = id_client;
		this.nome_client = nome_client;
		this.tipo_client = tipo_client;
		this.sede = sede;
		this.classe_licenza = classe_licenza;
	}
	
	public ShallowClient () { }
	
	

}
