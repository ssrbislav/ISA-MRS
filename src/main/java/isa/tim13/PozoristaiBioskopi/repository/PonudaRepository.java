package isa.tim13.PozoristaiBioskopi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import isa.tim13.PozoristaiBioskopi.model.Ponuda;
import isa.tim13.PozoristaiBioskopi.model.PonudaNotifikacija;

@Repository
public interface PonudaRepository extends CrudRepository<Ponuda,Integer>{
	
	@Query(value="select pon from Ponuda pon where pon.autor.id = :idKorisnika and pon.objava.id = :idObjave",nativeQuery=false)
	Ponuda pronadjiPonudu(@Param("idKorisnika")int idKorisnika, @Param("idObjave")int idObjave);
	
	Ponuda findById(int id);

	void save(PonudaNotifikacija notifikacija);
    
	

}
