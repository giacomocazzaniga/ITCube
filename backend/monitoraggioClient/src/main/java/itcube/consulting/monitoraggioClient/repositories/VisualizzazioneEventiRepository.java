package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.VisualizzazioneEventi;

public interface VisualizzazioneEventiRepository extends CrudRepository<VisualizzazioneEventi, Integer>{
	
	@Query(value="SELECT count(*) from visualizzazione_eventi where id_client= :id_client and level= 1 and CAST( date_and_time_evento AS Date) = CAST( CURDATE() AS Date )",nativeQuery=true)
	public int getProblemiOggi(@Param("id_client") int id_client);
	
	@Query(value="SELECT count(*) from visualizzazione_eventi where id_client= :id_client and level= 2 and CAST( date_and_time_evento AS Date) = CAST( CURDATE() AS Date )",nativeQuery=true)
	public int getWarningOggi(@Param("id_client") int id_client);
	
	@Query(value="select count(*) from visualizzazione_eventi where id_client= :id_client and sottocategoria= :sottocategoria",nativeQuery=true)
	public int contaSottocategoria(@Param("id_client") int id_client, @Param("sottocategoria") String i);
	
	@Query(value="select distinct sottocategoria from visualizzazione_eventi where id_client= :id_client",nativeQuery=true)
	public List<String> getSottocategorie(@Param("id_client") int id_client);
	
	@Query(value="SELECT * FROM agentwindows.visualizzazione_eventi WHERE sottocategoria = :sottocategoria AND id_client = :id_client ORDER BY date_and_time_evento DESC",nativeQuery=true)
	public List<VisualizzazioneEventi> getEventi(@Param("id_client") int id_client, @Param("sottocategoria") String sottocategoria);
	
	@Query(value="select count(info) from visualizzazione_eventi where id_client= :id_client AND date_and_time_evento >= curdate()",nativeQuery=true)
	public int getTotEventiToday(@Param("id_client") int id_client);
	
	@Query(value="select count(*) from ( "
			+ "Select * "
			+ "from visualizzazione_eventi "
			+ "WHERE id_client= :id_client "
			+ "ORDER by date_and_time desc "
			+ "limit :limite "
			+ ") SUB where SUB.level = :level ", nativeQuery=true)
	int getNumStato(@Param("id_client") int id_client, @Param("level") int level, @Param("limite") int limite);
	
}
