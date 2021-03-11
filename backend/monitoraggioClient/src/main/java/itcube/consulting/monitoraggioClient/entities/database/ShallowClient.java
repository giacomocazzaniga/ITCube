package itcube.consulting.monitoraggioClient.entities.database;

public class ShallowClient {
	
	private Integer id_client;
	private String nome_client;
	private String tipo_client;
	private String sede;
	private Integer classe_licenza;
	
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
	public Integer getClasse_licenza() {
		return classe_licenza;
	}
	public void setClasse_licenza(Integer classe_licenza) {
		this.classe_licenza = classe_licenza;
	}
	
	public ShallowClient () { }

}
