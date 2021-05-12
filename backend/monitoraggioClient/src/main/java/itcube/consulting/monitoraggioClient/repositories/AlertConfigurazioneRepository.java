package itcube.consulting.monitoraggioClient.repositories;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.AlertConfigurazione;

public interface AlertConfigurazioneRepository extends CrudRepository<AlertConfigurazione, Integer>{
	
	@Query(value="INSERT INTO alert_configurazione (monitora, operazione, id_client, categoria) "
			+ "VALUES (1, :operazione, :id_client, :categoria)", nativeQuery=true)
	@Modifying
	@Transactional
	void insertAlertConfigurazione(@Param("operazione") String operazione, @Param("id_client") int id_client,  @Param("categoria") int categoria);
	
	@Query(value = "SELECT * FROM alert_configurazione WHERE id_client= :id_client", nativeQuery = true)
	List<AlertConfigurazione> getAlertConfigurazione(@Param("id_client") int id_client);

	@Query(value="UPDATE alert_configurazione SET monitora= :monitora WHERE id_client= :id_client AND operazione= :operazione", nativeQuery=true)
	@Modifying
	@Transactional
	void updateAlertConfigurazione(@Param("monitora") boolean monitora, @Param("id_client") int id_client, @Param("operazione") String operazione);

	@Query(value="SELECT monitora FROM alert_configurazione WHERE id_client = :id_client AND operazione = :operazione",nativeQuery=true)
	public boolean isOperazioneMonitorata(@Param("id_client") int id_client, @Param("operazione") String operazione);
}
