package itcube.consulting.monitoraggioClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import itcube.consulting.monitoraggioClient.entities.ElencoCompanies;

public interface ElencoCompaniesRepository extends CrudRepository<ElencoCompanies,Integer>{
	@Query(value="Select email, ragione_sociale from elenco_companies where email= :email or ragione_sociale= :ragione_sociale", nativeQuery=true)
	List<String> getCompanies(@Param("email") String email, @Param("ragione_sociale") String ragione_sociale);
	
	@Query(value="select count(*) from elenco_companies where elenco_companies.email= :email and elenco_companies.password= :password", nativeQuery=true)
	int Login(@Param("email") String email, @Param("password") String password);
	
	@Query(value="Select * from elenco_companies where elenco_companies.email= :email", nativeQuery=true)
	ElencoCompanies getInfoCompany(@Param("email") String email);
	
	@Query(value="Select * from elenco_companies where elenco_companies.id= :id_company", nativeQuery=true)
	ElencoCompanies getInfoCompany(@Param("id_company") int id);
	
	@Query(value="select count(*) from sedi where id_company = :id_company and nome <> 'Senza sede'", nativeQuery=true)
	Integer getNSedi(@Param("id_company") int id_company);
	
	@Query(value="Select id from elenco_companies where chiave_di_registrazione= :chiave_di_registrazione",nativeQuery=true)
	Integer getIdCompanyFromChiave(@Param("chiave_di_registrazione") String chiave);
	
	@Query(value="SELECT id FROM agentwindows.sedi WHERE nome = 'Senza sede' AND id_company = :id_company",nativeQuery=true)
	Integer getSenzaSedeOfCompany(@Param("id_company") int id_company);
	
}
