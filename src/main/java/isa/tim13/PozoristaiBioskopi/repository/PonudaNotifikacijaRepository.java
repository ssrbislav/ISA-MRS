package isa.tim13.PozoristaiBioskopi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import isa.tim13.PozoristaiBioskopi.model.PonudaNotifikacija;

@Repository
public interface PonudaNotifikacijaRepository extends CrudRepository<PonudaNotifikacija,Integer> {
	@Query(value="select notifikacija from PonudaNotifikacija notifikacija where notifikacija.ponuda.autor.id = :id order by notifikacija.datum desc",nativeQuery=false)
	Iterable<PonudaNotifikacija> dobaviObavestenjaKorisnika(@Param("id")int id);
	PonudaNotifikacija findById(int id);
    
}
