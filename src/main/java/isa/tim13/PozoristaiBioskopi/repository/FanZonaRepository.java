package isa.tim13.PozoristaiBioskopi.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import isa.tim13.PozoristaiBioskopi.model.TematskiRekvizit;

@Repository
public interface FanZonaRepository extends CrudRepository<TematskiRekvizit, Integer> {
	
	TematskiRekvizit findByNazivRekvizita(String naziv);
	
	
	@Query(value="select * from pozorista_i_bioskopi.tematski_rekvizit where lower(naziv) like lower(concat('%',:nazivRekvizita,'%')) and cena >= :donjaCena and cena <= :gornjaCena",nativeQuery=true)
	Iterable<TematskiRekvizit> findByRazniKriterijumi(@Param("nazivRekvizita")String nazivRekvizita, @Param("donjaCena")double donjaCena, @Param("gornjaCena")double gornjaCena);
}
