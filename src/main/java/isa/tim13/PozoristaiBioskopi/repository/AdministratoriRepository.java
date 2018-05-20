package isa.tim13.PozoristaiBioskopi.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isa.tim13.PozoristaiBioskopi.model.Administrator;
import isa.tim13.PozoristaiBioskopi.model.Osoba;

@Repository
public interface AdministratoriRepository extends CrudRepository<Administrator, Integer> {
	Osoba findByEmail(String email);
	
}
