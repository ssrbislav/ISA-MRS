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
	
	TematskiRekvizit findById(int id);
	
	void save(RezervacijaRekvizita rezervacija);
	
	
	@Query(value="select r from TematskiRekvizit r where lower(r.nazivRekvizita) like lower(concat('%',:nazivRekvizita,'%'))  and r.cenaRekvizita <= :gornjaCena",nativeQuery=false)
	Iterable<TematskiRekvizit> findByRazniKriterijumi(@Param("nazivRekvizita")String nazivRekvizita, @Param("gornjaCena")double gornjaCena);
	
	@Query(value="select r from RezervacijaRekvizita r where r.narucilac.id = :id",nativeQuery=false)
	Iterable<RezervacijaRekvizita> pribaviRezervacijeRekvizita(@Param("id") int id);
	
	@Query(value="select r from TematskiRekvizit r where r.id = :id",nativeQuery=false)
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	TematskiRekvizit pronadjiPoIdLock(@Param("id")int id);
}
