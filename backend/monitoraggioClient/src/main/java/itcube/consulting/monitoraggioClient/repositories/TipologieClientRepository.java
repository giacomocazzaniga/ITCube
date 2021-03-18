package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import itcube.consulting.monitoraggioClient.entities.*;

public interface TipologieClientRepository extends CrudRepository<TipologiaClient,Integer>{
	
	@Query(value="Select * from tipologia_client", nativeQuery=true)
	List<TipologiaClient> getTipologie();
	
}