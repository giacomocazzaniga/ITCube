package itcube.consulting.monitoraggioClient.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.TipologieLicenze;

public interface TipologieLicenzeRepository extends CrudRepository<TipologieLicenze,Integer>{
	
	@Query(value="Select * from tipologie_licenze where classe= :classe", nativeQuery=true)
	TipologieLicenze getLicenza(@Param("classe") String classe);
}
