package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.Monitoraggio;

public interface MonitoraggioRepository extends CrudRepository<Monitoraggio,Integer>{
	
	@Query(value="Select * from Monitoraggio Where id_client = :id_client", nativeQuery=true)
	List<Monitoraggio> getServiziClient(@Param("id_client") int id_client);
	
	@Query(value="Select count(*) from Monitoraggio Where id_client = :id_client and monitora = 1", nativeQuery=true)
	public int getNServiziMonitorati(@Param("id_client") int id_client);
	
	@Query(value="select * from monitoraggio where id_client= :id_client", nativeQuery=true)
	List<Monitoraggio> getListaMonitorati(@Param("id_client") int id_client);
	
	@Modifying
	@Transactional
	@Query(value="update monitoraggio set monitora = :monitora where nome_servizio= :nome_servizio", nativeQuery=true)
	void updateMonitora(@Param("monitora") boolean monitora, @Param("nome_servizio") String nome_servizio);

	@Query(value="select id from monitoraggio where nome_servizio= :nome_servizio and id_client= :id_client", nativeQuery=true)
	Integer containsServizio (@Param("nome_servizio") String nome_servizio, @Param("id_client") int id_client);
	
	@Query(value="Select m.id from monitoraggio m join conf_windows_services s on m.nome_servizio=s.nome_servizio where m.id_client= :id_client and m.monitora=true and m.nome_servizio= :nome_servizio and s.stato=1", nativeQuery=true)
	Integer getMonitoratoStopped(@Param("id_client") int id_client, @Param("nome_servizio") String nome_servizio);
	
	@Query(value="Select monitora from monitoraggio where id_client= :id_client and nome_servizio= :nome_servizio", nativeQuery=true)
	boolean getMonitora(@Param("id_client") int id_client, @Param("nome_servizio") String nome_servizio);
	
	@Modifying
	@Transactional
	@Query(value="update monitoraggio set monitora = :monitora where nome_servizio= :nome_servizio", nativeQuery=true)
	void updateAllClientsOfCompany(@Param("monitora") boolean monitora, @Param("nome_servizio") String nome_servizio);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE monitoraggio m "
			+ "	SET m.monitora = :monitora "
			+ "	WHERE m.id_client IN ( "
			+ "		SELECT c.id "
			+ "		FROM elenco_clients_elenco_licenze cl "
			+ "		INNER JOIN elenco_clients c ON cl.id_client = c.id "
			+ "	    INNER JOIN elenco_licenze l ON cl.id_licenza = l.id "
			+ "		WHERE (:tipologia_client =-1 OR c.tipologia_client=:tipologia_client) "
			+ "	    AND (:tipo_licenza =-1 OR l.id_tipo = :tipo_licenza) "
			+ "	    AND (:sede =-1 OR c.sede = :sede) "
			+ "	    AND c.id_company = :id_company "
			+ "	)"
			+ "AND m.nome_servizio = :nome_servizio", nativeQuery=true)
	void updateFilteredServices(@Param("monitora") boolean monitora, @Param("nome_servizio") String nome_servizio,@Param("tipologia_client") int tipologia_client, @Param("tipo_licenza") int tipo_licenza, @Param("sede") int sede, @Param("id_company") int id_company);
	
	
}
