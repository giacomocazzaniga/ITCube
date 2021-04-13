package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.ConfTotalFreeDiscSpace;

public interface ConfTotalFreeDiscSpaceRepository extends CrudRepository<ConfTotalFreeDiscSpace,Integer>{
	
	@Query(value="Select * from conf_total_free_disc_space where id_client= :id_client and drive= :drive", nativeQuery=true)
	ConfTotalFreeDiscSpace isPresent(@Param("drive") String drive, @Param("id_client") int id_client);
	
	@Modifying
	@Transactional
	@Query(value="update conf_total_free_disc_space set perc_free_disc_space=:perc_free_disc_space, total_free_disc_space= :totalFreeSpace, total_size=:totalSize where drive= :drive and id_client= :id_client", nativeQuery=true)
	void updateDisk(@Param("drive") String drive, @Param("id_client") int id_client, @Param("totalSize") long totalSize, @Param("totalFreeSpace") long TotalFreeSpace, @Param("perc_free_disc_space") double perc_free_disc_space );
	
	@Query(value="Select * from conf_total_free_disc_space where id_client= :id_client", nativeQuery=true)
	List<ConfTotalFreeDiscSpace> getDrives(@Param("id_client") int id_client);
	
}
