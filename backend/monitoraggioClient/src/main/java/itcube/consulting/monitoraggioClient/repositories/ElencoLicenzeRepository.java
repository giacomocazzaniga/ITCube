package itcube.consulting.monitoraggioClient.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.ElencoLicenze;

public interface ElencoLicenzeRepository extends CrudRepository<ElencoLicenze,Integer>{

	@Query(value="Select count(*) from elenco_licenze where codice= :codice", nativeQuery=true)
	int countCodes(@Param("codice") String codice);
}
