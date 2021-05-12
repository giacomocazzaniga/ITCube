package itcube.consulting.monitoraggioClient.repositories;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
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
	List<Integer> getServiziAlert(@Param("id_client") int id_client, @Param("nome_servizio") String nome_servizio);
	
	@Query(value="SELECT id\n"
			+ "FROM alert\n"
			+ "where id_client= :id_client and tipo= :tipo and corpo_messaggio LIKE concat('%', :nome_servizio ,'%') and date_and_time_alert>=any(\n"
			+ "	Select max(date_and_time_alert)\n"
			+ "	from alert\n"
			+ "	where id_client=:id_client and corpo_messaggio LIKE concat('%', :nome_servizio ,'%'))", nativeQuery=true)
	Integer isModified(@Param("id_client") int id_client, @Param("nome_servizio") String nome_servizio, @Param("tipo") String tipo);
	
	@Query(value="Select tipo "
			+ "from alert "
			+ "where categoria=2 and id_client= :id_client and corpo_messaggio LIKE concat('%', :nome_servizio ,'%') "
			+ "order by date_and_time_alert desc "
			+ "limit 1", nativeQuery=true)
	String lastAlertStatus(@Param("id_client") int id_client, @Param("nome_servizio") String nome_servizio);

	@Query(value="SELECT * "
			+ "FROM alert "
			+ "WHERE id_client = :id_client "
			+ "AND date_and_time_alert >= CONCAT(curdate() - interval 10 * :slot day, ' 00:00:00') "
			+ "AND date_and_time_alert <= CONCAT(curdate() - interval 10 * (:slot - 1) day, ' 23:59:59') "
			+ "AND tipo <> 'OK'" , nativeQuery = true)
	List<Alert> getAlertOfDay(@Param("id_client") int id_client, @Param("slot") int slot);
	
	@Query(value="SELECT * "
			+ "FROM alert "
			+ "WHERE id_company = :id_company "
			+ "AND date_and_time_alert >= CONCAT(curdate() - interval 10 * :slot day, ' 00:00:00') "
			+ "AND date_and_time_alert <= CONCAT(curdate() - interval 10 * (:slot - 1) day, ' 23:59:59') "
			+ "AND tipo <> 'OK'" , nativeQuery = true)
	List<Alert> getAlertOfCompanyOfDay(@Param("id_company") int id_company, @Param("slot") int slot);
	

	@Modifying
	@Transactional
	@Query(value="UPDATE alert SET date_and_time_mail = CURRENT_TIMESTAMP(6) WHERE id= :id_alert",nativeQuery=true)
	public void updateMailTimestamp(@Param("id_alert") int id_alert);

	@Query(value="SELECT * FROM alert WHERE date_and_time_mail IS NULL",nativeQuery=true)
	public List<Alert> getAllAlertsWithoutMailDateTime();
}
