package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.ConfWindowsServices;

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
	
	@Query(value="select Monitoraggio.nome_servizio from conf_windows_services where monitorato=true and id_client= :id_client", nativeQuery=true)
	List<ConfWindowsServices> getServiziMonitorati(@Param("id_client") int id_client);
	
	@Query(value="select count(*) from conf_windows_services where stato= :stato and id_client= :id_client", nativeQuery=true)
	int getNumStato(@Param("id_client") int id_client, @Param("stato") int stato);
	
	@Query(value="select count(*) from conf_windows_services whereid_client= :id_client", nativeQuery=true)
	int getTotServizi(@Param("id_client") int id_client);
}
