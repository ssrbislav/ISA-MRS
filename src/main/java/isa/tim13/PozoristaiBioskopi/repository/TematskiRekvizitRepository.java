package isa.tim13.PozoristaiBioskopi.repository;



import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import isa.tim13.PozoristaiBioskopi.model.RezervacijaRekvizita;
import isa.tim13.PozoristaiBioskopi.model.TematskiRekvizit;

@Repository
public interface TematskiRekvizitRepository extends CrudRepository<TematskiRekvizit, Integer> {
	
	TematskiRekvizit findByNazivRekvizita(String naziv);
	
	@Lock(value = LockModeType.PESSIMISTIC_READ)
	TematskiRekvizit findById(int id);
	
	void save(RezervacijaRekvizita rezervacija);
	
	
	@Query(value="select r from TematskiRekvizit r where lower(r.nazivRekvizita) like lower(concat('%',:nazivRekvizita,'%'))  and r.cenaRekvizita <= :gornjaCena",nativeQuery=false)
	Iterable<TematskiRekvizit> findByRazniKriterijumi(@Param("nazivRekvizita")String nazivRekvizita, @Param("gornjaCena")double gornjaCena);
}
