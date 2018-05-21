package isa.tim13.PozoristaiBioskopi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import isa.tim13.PozoristaiBioskopi.model.FanZonaAdministrator;
import isa.tim13.PozoristaiBioskopi.model.Objava;
import isa.tim13.PozoristaiBioskopi.model.StatusObjave;

@Repository
public interface ObjavaRepository extends CrudRepository<Objava,Integer> {
	Iterable<Objava> findByStatus(StatusObjave status);
	Objava findById(int id);
	
	Objava findByNaziv(String naziv);
	
	@Query(value="select obj from Objava obj where obj.status = 1 and obj.admin =:admin",nativeQuery=false)
	Iterable<Objava> dobaviRazmatraneObjave(@Param("admin")FanZonaAdministrator admin);
	
}
