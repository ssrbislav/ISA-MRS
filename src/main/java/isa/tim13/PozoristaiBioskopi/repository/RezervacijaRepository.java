package isa.tim13.PozoristaiBioskopi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isa.tim13.PozoristaiBioskopi.model.Rezervacija;

@Repository
public interface RezervacijaRepository extends CrudRepository<Rezervacija, Integer> {
	
}
