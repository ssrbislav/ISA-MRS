package isa.tim13.PozoristaiBioskopi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isa.tim13.PozoristaiBioskopi.model.Termin;

@Repository
public interface TerminRepository extends CrudRepository<Termin, Integer> {
	

}
