package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.*;

public interface TipologieClientRepository extends CrudRepository<TipologiaClient,Integer>{
	
	@Query(value="Select * from tipologia_client", nativeQuery=true)
	List<TipologiaClient> getTipologie();
	
	@Query(value="Select * from tipologia_client where tipologia_client.nome_tipologia= :nome", nativeQuery=true)
	TipologiaClient getSpecificType(@Param ("nome") String nome);
	
	@Query(value="Select * from tipologia_client where id= :tipologiaClient", nativeQuery=true)
	TipologiaClient getNomeFromNum(@Param ("tipologiaClient") int tipologiaClient);
}