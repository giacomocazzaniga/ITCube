package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.Sedi;

public interface SediRepository extends CrudRepository<Sedi, Integer>{
	
	@Query(value="Select * from sedi where nome= :nome and id_company= :id_company",nativeQuery=true)
	Sedi isPresent(@Param("nome") String nome,@Param("id_company") int id_company);

	@Query(value="Select distinct id, nome from sedi where id_company= :id_company",nativeQuery=true)
	List<String> getNomiSedi(@Param("id_company") int id_company);
	
	@Query(value="Select id from sedi where id_company= :id_company and nome = :nome_sede",nativeQuery=true)
	Integer nomeToId(@Param("id_company") Integer id_company, @Param("nome_sede") String nome_sede);
}
