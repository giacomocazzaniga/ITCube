package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.Monitoraggio;

public interface MonitoraggioRepository extends CrudRepository<Monitoraggio,Integer>{
	
	@Query(value="Select * from Monitoraggio Where id_client = :id_client", nativeQuery=true)
	public List<Monitoraggio> getServiziClient(@Param("id_client") int id_client);
}
