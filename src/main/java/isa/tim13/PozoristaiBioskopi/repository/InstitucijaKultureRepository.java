package isa.tim13.PozoristaiBioskopi.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isa.tim13.PozoristaiBioskopi.model.InstitucijaKulture;

@Repository
public interface InstitucijaKultureRepository extends CrudRepository<InstitucijaKulture, Integer>   {

	InstitucijaKulture findByNaziv(String naziv);
	
	
}
