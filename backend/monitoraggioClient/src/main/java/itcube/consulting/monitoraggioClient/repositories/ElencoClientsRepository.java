package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.ElencoClients;
import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;

public interface ElencoClientsRepository extends CrudRepository<ElencoClients,Integer>{
	
	@Query(value="Select * from elenco_clients where id_company= :company", nativeQuery=true)
	List<ElencoClients> getElencoClients(@Param("company") ElencoCompanies company);
}
