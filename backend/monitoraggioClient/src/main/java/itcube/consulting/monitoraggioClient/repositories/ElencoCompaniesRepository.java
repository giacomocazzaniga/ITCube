package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;
import itcube.consulting.monitoraggioClient.entities.database.OperationsPerClient;

public interface ElencoCompaniesRepository extends CrudRepository<ElencoCompanies,Integer>{
	@Query(value="Select email, ragione_sociale from elenco_companies where email= :email or ragione_sociale= :ragione_sociale", nativeQuery=true)
	List<String> getCompanies(@Param("email") String email, @Param("ragione_sociale") String ragione_sociale);
	
	@Query(value="select count(*) from elenco_companies where elenco_companies.email= :email and elenco_companies.password= :password", nativeQuery=true)
	int Login(@Param("email") String email, @Param("password") String password);
	
	@Query(value="Select * from elenco_companies where elenco_companies.email= :email", nativeQuery=true)
	ElencoCompanies getInfoCompany(@Param("email") String email);
	
	@Query(value="Select * from elenco_companies where elenco_companies.id= :id_company", nativeQuery=true)
	ElencoCompanies getInfoCompany(@Param("id_company") int id);
}
