package itcube.consulting.monitoraggioClient.entities.database;

import itcube.consulting.monitoraggioClient.entities.ElencoLicenze;
import java.util.*;

public class LicenzaShallow {

	private String codice;
	private Integer classe;
	private String tipologia;
	
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
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	
	//Converte da una lista di licenze a una lista di licenze shallow
	public static List<LicenzaShallow> getLicenzeShallow (List<ElencoLicenze> licenze) {
		
		List<LicenzaShallow> licenzeShallow = new ArrayList<> (); 
		
		for (int i = 0; i < licenze.size(); i++)
			licenzeShallow.add(new LicenzaShallow(licenze.get(i)));
		
		return licenzeShallow;
		
	}
	
	public LicenzaShallow (ElencoLicenze licenza) {
		this.codice = licenza.getCodice();
		this.classe = licenza.getTipologieLicenze().getClasse();
		this.tipologia = licenza.getTipologieLicenze().getNome_tipologia();
	}
	
	public LicenzaShallow () {} 
	
	
	
}
