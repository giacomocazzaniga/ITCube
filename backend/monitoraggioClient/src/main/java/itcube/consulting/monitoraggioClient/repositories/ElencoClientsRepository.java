package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.ElencoClients;
import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;
import itcube.consulting.monitoraggioClient.entities.database.OperationsPerClient;

public interface ElencoClientsRepository extends CrudRepository<ElencoClients,Integer>{
	
	@Query(value="Select * from elenco_clients where id_company= :company", nativeQuery=true)
	List<ElencoClients> getElencoClients(@Param("company") ElencoCompanies company);
	
	@Query(value="SELECT config.id, config.tempo, eo.id, eo.nome, eo.tabella_riferimento\n"
			+ "from ((elenco_clients ec\n"
			+ "INNER JOIN config on ec.id=config.id_client)\n"
			+ "INNER JOIN elenco_operazioni eo on eo.id=config.id_operazione)\n"
			+ "where ec.id= :id_client", nativeQuery=true)
	OperationsPerClient getOperationsPerClient(@Param("id_client") int id_client);
}