package itcube.consulting.monitoraggioClient.entities.database;

public class TipologiaClientCount {

	private String nome_categoria;
	private int count_categoria;
	
	
	
	public TipologiaClientCount(String nome_categoria, int count_categoria) {
		this.nome_categoria = nome_categoria;
		this.count_categoria = count_categoria;
	}
	public String getNome_categoria() {
		return nome_categoria;
	}
	public void setNome_categoria(String nome_categoria) {
		this.nome_categoria = nome_categoria;
	}
	public int getCount_categoria() {
		return count_categoria;
	}
	public void setCount_categoria(int count_categoria) {
		this.count_categoria = count_categoria;
	}
	
	
}
