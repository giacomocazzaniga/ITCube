package itcube.consulting.monitoraggioClient.repositories;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import itcube.consulting.monitoraggioClient.entities.ElencoClients;
import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;
import itcube.consulting.monitoraggioClient.entities.database.ShallowClient;

@Repository
public interface ElencoClientsRepository extends CrudRepository<ElencoClients,Integer>{
	
	@Query(value="Select * from elenco_clients where id_company= :company", nativeQuery=true)
	List<ElencoClients> getElencoClients(@Param("company") ElencoCompanies company);
	
	@Query(value="SELECT *\n"
			+ "FROM elenco_clients ec\n"
			+ "WHERE ec.id_company = :company", nativeQuery=true)
	List<ElencoClients> getShallowClients(@Param("company") ElencoCompanies company);
	
	@Query(value="SELECT *\n"
			+ "FROM elenco_clients ec\n"
			+ "where ec.id= :id_client", nativeQuery=true)
	ElencoClients getDeepClient(@Param("id_client") int id_client);	
	
	@Query(value="Select elenco_companies.id from elenco_companies join elenco_clients on elenco_companies.id=elenco_clients.id_company where elenco_clients.id= :id_client", nativeQuery=true)
	int getIdCompany(@Param("id_client") int id_client);
	
	@Query(value="Select id from elenco_clients where nome= :nome and mac_address= :mac_address", nativeQuery=true)
	Integer getIdFromInfo(@Param("nome") String nome, @Param("mac_address") String mac_address);
	
	@Query(value="Select * from elenco_clients where id= :id_client", nativeQuery=true)
	ElencoClients getClientFromId(@Param("id_client") int id_client);
	
	@Query(value="UPDATE elenco_clients SET sede = :nuova_sede WHERE id= :id_client", nativeQuery=true)
	@Modifying
	@Transactional
	void modificaSingolaSedeClient(@Param("id_client") int id_client, @Param("nuova_sede") String nuova_sede);
	
	@Query(value="UPDATE elenco_clients SET sede = :nuova_sede WHERE sede = :vecchia_sede AND id_company= :id_company", nativeQuery=true)
	@Modifying
	@Transactional
	void modificaAllVecchieSediClient(@Param("id_company") int id_company, @Param("nuova_sede") String nuova_sede, @Param("vecchia_sede") String vecchia_sede);
	
	@Query(value="Select id from elenco_clients where sede= :sede and id_company= :id_company", nativeQuery=true)
	List<Integer> getClientsInSede(@Param("sede") int sede,@Param("id_company") int id_company );
	
	@Query(value="SELECT tipologia_client "
			+ "FROM elenco_clients "
			+ "WHERE id= :id_client", nativeQuery=true)
	Integer getTipoFromIdClient(@Param("id_client") int id_client);
	
	@Query(value="SELECT * FROM elenco_clients WHERE tipologia_client= :tier_id AND id_company= :id_company", nativeQuery=true)
	List<ElencoClients> getClientFromTier(@Param ("id_company") Integer id_company, @Param ("tier_id") Integer tier_id);
}