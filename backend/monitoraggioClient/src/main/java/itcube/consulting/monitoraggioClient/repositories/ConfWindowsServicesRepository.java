package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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
	
	@Query(value="select count(*) from (\n"
			+ "    Select *\n"
			+ "    from conf_windows_services \n"
			+ "    WHERE id_client= :id_client\n"
			+ "    ORDER by date_and_time desc\n"
			+ "    limit :limite\n"
			+ ") SUB where SUB.stato = :stato", nativeQuery=true)
	int getNumStato(@Param("id_client") int id_client, @Param("stato") int stato, @Param("limite") int limite);
	
	@Query(value="select count(DISTINCT nome_servizio) from conf_windows_services where id_client= :id_client", nativeQuery=true)
	int getTotServizi(@Param("id_client") int id_client);
	
	
}
