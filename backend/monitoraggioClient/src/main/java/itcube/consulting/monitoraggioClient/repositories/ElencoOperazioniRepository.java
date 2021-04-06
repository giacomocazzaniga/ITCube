package itcube.consulting.monitoraggioClient.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.ElencoOperazioni;

public interface ElencoOperazioniRepository extends CrudRepository<ElencoOperazioni,Integer>{
	
	@Query(value="Select count(*) from elenco_operazioni join config on config.id_operazione=elenco_operazioni.id where elenco_operazioni.stato= :stato and config.id_client= :id_client", nativeQuery=true)
	public int countStato(@Param("stato") String stato, @Param("id_client") int id_client);
}
