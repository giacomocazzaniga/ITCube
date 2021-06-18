package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.*;

public interface TipologieClientRepository extends CrudRepository<TipologiaClient,Integer>{
	
	@Query(value="Select * from tipologia_client", nativeQuery=true)
	List<TipologiaClient> getTipologie();
	
	@Query(value="Select * from tipologia_client where tipologia_client.nome_tipologia= :nome", nativeQuery=true)
	TipologiaClient getSpecificType(@Param ("nome") String nome);
	
	@Query(value="Select * from tipologia_client where id= :tipologiaClient", nativeQuery=true)
	TipologiaClient getNomeFromNum(@Param ("tipologiaClient") int tipologiaClient);
	
	@Query(value="INSERT INTO tipologia_client (nome_tipologia, id_company) VALUES (:tier_name, :id_company)", nativeQuery = true)
	@Transactional
	@Modifying
	void addTier(@Param("tier_name") String tier_name, @Param("id_company") int id_company);

	@Query(value="DELETE FROM tipologia_client WHERE nome_tipologia = :tier_name AND id_company= :id_company", nativeQuery = true)
	@Transactional
	@Modifying
	void removeTier(@Param("tier_name") String tier_name, @Param("id_company") int id_company);
	
	@Query(value="UPDATE elenco_clients SET tipologia_client= :tier_id WHERE id = :id_client", nativeQuery = true)
	@Transactional
	@Modifying
	void updateTier(@Param("tier_id") int tier_id, @Param("id_client") int id_client);
	
	@Query(value="SELECT count(*) "
			+ "FROM tipologia_client t "
			+ "INNER JOIN elenco_clients c ON t.id_company = c.id_company AND t.id = c.tipologia_client "
			+ "WHERE t.id_company = :id_company "
			+ "AND t.nome_tipologia = :tier_name ", nativeQuery=true)
	int getNumOfClientFromType(@Param ("tier_name") String tier_name, @Param ("id_company") int id_company);
	
	@Query(value="SELECT nome_tipologia "
			+ "FROM tipologia_client t "
			+ "INNER JOIN elenco_clients c ON c.tipologia_client = t.id "
			+ "WHERE c.id = :id_client", nativeQuery=true)
	String getTypeFromClient(@Param ("id_client") int id_client);
	
	@Query(value="SELECT id FROM tipologia_client WHERE nome_tipologia =:nome_tier AND id_company=:id_company", nativeQuery=true)
	int getTypeIdFromClient(@Param ("id_company") int id_company, @Param ("nome_tier") String nome_tier);
	
}
