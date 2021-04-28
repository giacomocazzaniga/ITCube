package itcube.consulting.monitoraggioClient.repositories;

import java.time.LocalDateTime;
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
	@Query(value="update conf_total_free_disc_space set perc_free_disc_space=:perc_free_disc_space, total_free_disc_space= :totalFreeSpace, total_size=:totalSize, date_and_time= :date_and_time where drive= :drive and id_client= :id_client", nativeQuery=true)
	void updateDisk(@Param("drive") String drive, @Param("id_client") int id_client, @Param("totalSize") String totalSize, @Param("totalFreeSpace") String TotalFreeSpace, @Param("perc_free_disc_space") double perc_free_disc_space,  @Param("date_and_time") LocalDateTime date_and_time );
	
	@Query(value="Select * from conf_total_free_disc_space where id_client= :id_client", nativeQuery=true)
	List<ConfTotalFreeDiscSpace> getDrives(@Param("id_client") int id_client);
	
	@Query(value="select count(*) from ( "
			+ "Select * "
			+ "from conf_total_free_disc_space "
			+ "WHERE id_client= :id_client "
			+ "ORDER by date_and_time desc "
			+ "limit :limite "
			+ ") SUB where perc_free_disc_space > 10 and perc_free_disc_space <= 20 ", nativeQuery=true)
	int getNumWarning(@Param("id_client") int id_client, @Param("limite") int limite);
	
	@Query(value="select count(*) from ( "
			+ "Select * "
			+ "from conf_total_free_disc_space "
			+ "WHERE id_client= :id_client "
			+ "ORDER by date_and_time desc "
			+ "limit :limite "
			+ ") SUB where perc_free_disc_space <= 10 ", nativeQuery=true)
	int getNumError(@Param("id_client") int id_client, @Param("limite") int limite);
	
	@Query(value="select count(*) from ( "
			+ "Select * "
			+ "from conf_total_free_disc_space "
			+ "WHERE id_client= :id_client "
			+ "ORDER by date_and_time desc "
			+ "limit :limite "
			+ ") SUB where perc_free_disc_space > 20 ", nativeQuery=true)
	int getNumOk(@Param("id_client") int id_client, @Param("limite") int limite);
	
	@Query(value="select count(DISTINCT drive) from conf_total_free_disc_space where id_client= :id_client", nativeQuery=true)
	int getTotDrives(@Param("id_client") int id_client);
	
}
