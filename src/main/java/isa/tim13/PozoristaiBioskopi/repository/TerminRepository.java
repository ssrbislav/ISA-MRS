package isa.tim13.PozoristaiBioskopi.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isa.tim13.PozoristaiBioskopi.model.Termin;

@Repository
public interface TerminRepository extends CrudRepository<Termin, Integer> {
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	Optional<Termin> findById(int id);
	
}
