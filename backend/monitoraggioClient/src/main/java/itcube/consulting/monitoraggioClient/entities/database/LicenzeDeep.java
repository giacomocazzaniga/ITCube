package itcube.consulting.monitoraggioClient.entities.database;

import java.util.*;
import itcube.consulting.monitoraggioClient.entities.ElencoLicenze;

public class LicenzeDeep {
		
	private String codice;
	private Integer classe;
	private String nome_tipologia;
	private List<ClientIdNome> listClients;
	
	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Integer getClasse() {
		return classe;
	}

	public void setClasse(Integer classe) {
		this.classe = classe;
	}

	public String getNome_tipologia() {
		return nome_tipologia;
	}

	public void setNome_tipologia(String nome_tipologia) {
		this.nome_tipologia = nome_tipologia;
	}

	public List<ClientIdNome> getListClients() {
		return listClients;
	}

	public void setListClients(List<ClientIdNome> listClients) {
		this.listClients = listClients;
	}
	
	//Converte una lista di licenze a una lista di licenze deep
	public static List<LicenzeDeep> createLicenzeDeep (List<ElencoLicenze> licenze) {
		List<LicenzeDeep> licenzeDeep = new ArrayList<> ();
		for (int i = 0; i < licenze.size(); i++) {
			licenzeDeep.add(new LicenzeDeep(licenze.get(i)));
		}
		return licenzeDeep;
	}
	
	public LicenzeDeep (ElencoLicenze licenza) {
		this.codice = licenza.getCodice();
		this.classe = licenza.getTipologieLicenze().getClasse();
		this.nome_tipologia = licenza.getTipologieLicenze().getNome_tipologia();
		this.listClients = new ArrayList<>();
		for (int i = 0; i < licenza.getElencoClients().size(); i++) {
			this.listClients.add(new ClientIdNome(licenza.getElencoClients().get(i).getNome(),licenza.getElencoClients().get(i).getId()));
		}
	}

	public class ClientIdNome {
		
		private Integer id_client;
		private String nome_client;
				
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

		public ClientIdNome (String nome_client, Integer id_client) { 
			this.id_client = id_client;
			this.nome_client = nome_client;
		}
		
	}
	
}

