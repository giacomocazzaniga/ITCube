package itcube.consulting.monitoraggioClient.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;
import itcube.consulting.monitoraggioClient.entities.ElencoLicenze;

public interface ElencoLicenzeRepository extends CrudRepository<ElencoLicenze,Integer>{

	@Query(value="Select count(*) from elenco_licenze where codice= :codice", nativeQuery=true)
	int countCodes(@Param("codice") String codice);
	
	@Query(value="Select * from elenco_licenze where acquistato_da= :company", nativeQuery=true)
	List<ElencoLicenze> getLicenze(@Param("company") ElencoCompanies company);
	
}