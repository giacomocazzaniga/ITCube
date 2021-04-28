package itcube.consulting.monitoraggioClient.repositories;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.ConfTotalFreeDiscSpace;
import itcube.consulting.monitoraggioClient.entities.ConfWindowsServices;
import itcube.consulting.monitoraggioClient.entities.Monitoraggio;

public interface ConfWindowsServicesRepository extends CrudRepository<ConfWindowsServices,Integer>{
	@Query(value="Select count(*) from conf_windows_services where tipo_alert = :tipo_alert", nativeQuery=true)
	int countTipoAlert(@Param("tipo_alert") int tipo_alert);
	
	@Query(value="Select count(*) from conf_windows_services where stato = :stato", nativeQuery=true)
	int countStato(@Param("stato") boolean stato);
	
	@Query(value="Select count(*) from conf_windows_services where attivo = :attivo", nativeQuery=true)
	int countAttivo(@Param("attivo") boolean attivo);
	
	@Query(value="Select count(*) from conf_windows_services where esecuzione = :esecuzione", nativeQuery=true)
	int countEsecuzione(@Param("esecuzione") boolean esecuzione);
	
	@Query(value="Select * from conf_windows_services where nome_servizio= :nome_servizio", nativeQuery=true)
	List<ConfWindowsServices> getServiziClient(@Param("nome_servizio") String nome_servizio);
	
	@Query(value="Select *\n"
			+ "from conf_windows_services\n"
			+ "WHERE id_client= :id_client\n"
			+ "ORDER by date_and_time desc\n"
			+ "limit :limite", nativeQuery=true)
	List<ConfWindowsServices> getServizi(@Param("id_client") int id_client, @Param("limite") int limite);
	
	@Query(value="SELECT count( DISTINCT nome_servizio)\n"
			+ "    FROM conf_windows_services\n"
			+ "    WHERE id_client= :id_client", nativeQuery=true)
	int getNumServizi(@Param("id_client") int id_client);
	
	@Query(value="	SELECT count(*) "
			+ "	FROM monitoraggio m INNER JOIN conf_windows_services s ON m.id_client = s.id_client AND m.nome_servizio = s.nome_servizio "
			+ "	WHERE s.id_client = :id_client AND m.monitora = 1 AND s.stato = :stato", nativeQuery=true)
	int getNumStato(@Param("id_client") int id_client, @Param("stato") int stato);
	
	@Query(value="	SELECT count(*) "
			+ "	FROM conf_windows_services s "
			+ "	WHERE s.id_client = :id_client  AND s.stato = :stato", nativeQuery=true)
	int getNumStatoAll(@Param("id_client") int id_client, @Param("stato") int stato);
	
	@Query(value="select count(DISTINCT nome_servizio) from conf_windows_services where id_client= :id_client", nativeQuery=true)
	int getTotServizi(@Param("id_client") int id_client);
	
	@Query(value="Select * from conf_windows_services where id_client= :id_client and nome_servizio= :nome_servizio", nativeQuery=true)
	ConfWindowsServices isPresent(@Param("nome_servizio") String nome_servizio, @Param("id_client") int id_client);
	
	@Modifying
	@Transactional
	@Query(value="update conf_windows_services set stato=:stato, date_and_time= :date_and_time where id_client= :id_client and nome_servizio = :nome_servizio", nativeQuery=true)
	void updateService(@Param("id_client") int id_client, @Param("stato") int stato, @Param("date_and_time") LocalDateTime date_and_time, @Param("nome_servizio") String nome_servizio);
	
}
