package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import itcube.consulting.monitoraggioClient.entities.Alert;

@Repository
public interface ElencoAlertRepository extends CrudRepository<Alert,Integer> {
	
	@Query(value="select * "
			+ "from alert "
			+ "where id_client= :id_client and corpo_messaggio LIKE concat('%', :label ,'%') "
			+ "ORDER BY date_and_time_alert desc "
			+ "LIMIT 1", nativeQuery=true)
	Alert getRecentAlert(@Param("id_client") int id_client, @Param("label") String label);
	
	
	@Query(value="SELECT * FROM agentwindows.alert WHERE id_client= :id_client "
			+ "AND date_and_time_alert between date_sub(now(),INTERVAL :n_settimane WEEK) and now()"
			+ "ORDER BY date_and_time_alert desc", nativeQuery=true)
	List<Alert> getLatestAlerts(@Param("id_client") int id_client, @Param("n_settimane") int n_settimane);
	
	@Query(value="Select id from alert where categoria=2 and id_client= :id_client and corpo_messaggio LIKE concat('%', :nome_servizio ,'%')", nativeQuery=true)
	Integer getServiziAlert(@Param("id_client") int id_client, @Param("nome_servizio") String nome_servizio);
}
