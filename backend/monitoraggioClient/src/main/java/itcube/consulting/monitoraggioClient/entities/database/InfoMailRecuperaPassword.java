package itcube.consulting.monitoraggioClient.entities.database;

public class InfoMailRecuperaPassword {
	
	private int id_company;
	private String token;
	private String server_address;
	private String nome_company;

	public InfoMailRecuperaPassword(int id_company, String token, String server_address, String nome_company) {
		super();
		this.setId_company(id_company);
		this.token = token;
		this.server_address = server_address;
		this.nome_company = nome_company;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getServer_address() {
		return server_address;
	}

	public void setServer_address(String server_address) {
		this.server_address = server_address;
	}

	public int getId_company() {
		return id_company;
	}

	public void setId_company(int id_company) {
		this.id_company = id_company;
	}

	public String getNome_company() {
		return nome_company;
	}

	public void setNome_company(String nome_company) {
		this.nome_company = nome_company;
	}
	
}
