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
	List<Integer> getServiziAlert(@Param("id_client") int id_client, @Param("nome_servizio") String nome_servizio);
	
	@Query(value="SELECT id\n"
			+ "FROM alert\n"
			+ "where id_client= :id_client and tipo= :tipo and corpo_messaggio LIKE concat('%', :nome_servizio ,'%') and date_and_time_alert>=any(\n"
			+ "	Select max(date_and_time_alert)\n"
			+ "	from alert\n"
			+ "	where id_client=:id_client and corpo_messaggio LIKE concat('%', :nome_servizio ,'%'))", nativeQuery=true)
	Integer isModified(@Param("id_client") int id_client, @Param("nome_servizio") String nome_servizio, @Param("tipo") String tipo);
	
	/*@Query(value="Select count(*) from alert where id_client= :id_client and tipo='ERROR' and AND date_and_time_alert between date_sub(now(),INTERVAL :n_giorni DAY) and now()", nativeQuery=true)
	int countErrorClient(@Param("id_client") int id_client, @Param("n_giorni") int n_giorni);
	
	@Query(value="Select count(*) from alert where id_company= :id_company and tipo='ERROR' and AND date_and_time_alert between date_sub(now(),INTERVAL :n_giorni DAY) and now()",nativeQuery=true)
	int countErrorCompany(@Param("id_company") int id_company, @Param("n_giorni") int n_giorni);
	
	@Query(value="Select count(*) from alert where id_client= :id_client and tipo='WARNING' and AND date_and_time_alert between date_sub(now(),INTERVAL :n_giorni DAY) and now()", nativeQuery=true)
	int countWarningClient(@Param("id_client") int id_client, @Param("n_giorni") int n_giorni);
	
	@Query(value="Select count(*) from alert where id_company= :id_company and tipo='WARNING' and AND date_and_time_alert between date_sub(now(),INTERVAL :n_giorni DAY) and now()",nativeQuery=true)
	int countWarningCompany(@Param("id_company") int id_company, @Param("n_giorni") int n_giorni);
	
	@Query(value="Select count(*)\n"
			+ "from alert\n"
			+ "where tipo='OK' and categoria=1 and id_client=3 and date_and_time_alert>=any (\n"
			+ "	select max(date_and_time_alert)\n"
			+ "    from alert\n"
			+ "    where categoria=1 and id_client=3\n"
			+ ")", nativeQuery=true)
	int countOkClient();
	
	@Query(value="", nativeQuery=true)
	int countOkCompany();*/
}
