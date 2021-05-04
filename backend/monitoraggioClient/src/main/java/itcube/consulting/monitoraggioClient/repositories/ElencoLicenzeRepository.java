package itcube.consulting.monitoraggioClient.repositories;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;
import itcube.consulting.monitoraggioClient.entities.ElencoLicenze;

public interface ElencoLicenzeRepository extends CrudRepository<ElencoLicenze,Integer>{

	@Query(value="Select count(*) from elenco_licenze where codice= :codice", nativeQuery=true)
	int countCodes(@Param("codice") String codice);
	
	@Query(value="Select * from elenco_licenze where acquistato_da= :company and scadenza > current_timestamp()", nativeQuery=true)
	List<ElencoLicenze> getLicenze(@Param("company") ElencoCompanies company);

	@Query(value="Select tl.classe from elenco_licenze el inner join tipologie_licenze tl on :id_tipo=tl.id", nativeQuery=true)
	int getClasseLicenza(@Param("id_tipo") int id_tipo);
	
	@Query(value="Select * from elenco_licenze el where el.codice= :codice", nativeQuery=true)
	ElencoLicenze getLicenza(@Param ("codice") String codice);
	
	@Query(value="Select ec.id from elenco_licenze el join elenco_companies ec on el.acquistato_da=ec.id where el.codice= :codice", nativeQuery=true)
	Integer getIdCompanyFromLicenza(@Param ("codice") String codice);
	
	@Query(value="select id from elenco_licenze where codice = :codice", nativeQuery=true)
	Integer getIdLicenzaFromLicenza(@Param ("codice") String codice);
	
	@Query(value="INSERT INTO elenco_clients_elenco_licenze (id_client, id_licenza) "
			+ "VALUES (:id_client, :id_licenza)", nativeQuery=true)
	@Modifying
	@Transactional
	Integer assegnaLicenza(@Param ("id_client") int id_client,@Param ("id_licenza") int id_licenza);
	
	@Query(value="SELECT id FROM elenco_licenze WHERE acquistato_da = :id_company AND id_tipo= :id_tipo", nativeQuery=true)
	Integer getIdLicenzaFromTipoAndCompany(@Param ("id_company") int id_company, @Param ("id_tipo") int id_tipo);
	
}

