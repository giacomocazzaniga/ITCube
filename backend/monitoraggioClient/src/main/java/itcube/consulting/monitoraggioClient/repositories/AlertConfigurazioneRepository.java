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
	
	@Query(value="SELECT DISTINCT operazione FROM alert_configurazione", nativeQuery = true)
	public List<String> getAllNomiAlertConfigurazione();
	
	@Modifying
	@Transactional
	@Query(value="UPDATE alert_configurazione a "
			+ "SET a.monitora = :monitora "
			+ "WHERE a.id_client IN ( "
			+ "	SELECT c.id "
			+ "	FROM elenco_clients_elenco_licenze cl "
			+ "	INNER JOIN elenco_clients c ON cl.id_client = c.id "
			+ "    INNER JOIN elenco_licenze l ON cl.id_licenza = l.id "
			+ "	WHERE (:tipologia_client =-1 OR c.tipologia_client=:tipologia_client) "
			+ "    AND (:tipo_licenza =-1 OR l.id_tipo = :tipo_licenza) "
			+ "    AND (:sede =-1 OR c.sede = :sede) "
			+ "    AND c.id_company = :id_company "
			+ ") "
			+ "AND a.operazione = :nome_operazione", nativeQuery=true)
	void updateFilteredAlerts(@Param("monitora") boolean monitora, @Param("nome_operazione") String nome_operazione,@Param("tipologia_client") Integer tipologia_client, @Param("tipo_licenza") Integer tipo_licenza, @Param("sede") Integer sede, @Param("id_company") Integer id_company);
	
}
