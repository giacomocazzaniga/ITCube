package itcube.consulting.monitoraggioClient.entities.database;

public class OperationsPerClient {
	//id_config, id_operazione, nome_operazione, tabella_riferimento, tempo
	
	int id_config;
	int id_operazione;
	String nome_operazione;
	String tabella_riferimento;
	double tempo;
	
	public OperationsPerClient() {
		
	}

	public int getId_config() {
		return id_config;
	}

	public void setId_config(int id_config) {
		this.id_config = id_config;
	}

	public int getId_operazione() {
		return id_operazione;
	}

	public void setId_operazione(int id_operazione) {
		this.id_operazione = id_operazione;
	}

	public String getNome_operazione() {
		return nome_operazione;
	}

	public void setNome_operazione(String nome_operazione) {
		this.nome_operazione = nome_operazione;
	}

	public String getTabella_riferimento() {
		return tabella_riferimento;
	}

	public void setTabella_riferimento(String tabella_riferimento) {
		this.tabella_riferimento = tabella_riferimento;
	}

	public double getTempo() {
		return tempo;
	}

	public void setTempo(double tempo) {
		this.tempo = tempo;
	}
}
