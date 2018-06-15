package isa.tim13.PozoristaiBioskopi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isa.tim13.PozoristaiBioskopi.model.BodovnaSkala;

@Repository
public interface BodovnaSkalaRepository extends CrudRepository<BodovnaSkala, Integer> {
	
	
	@Query(value="select skala from BodovnaSkala skala where skala.id = (select min(skala.id) from BodovnaSkala)",nativeQuery=false)
	BodovnaSkala dobaviSkalu();
}
