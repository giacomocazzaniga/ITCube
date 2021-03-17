package itcube.consulting.monitoraggioClient.repositories;

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
}
