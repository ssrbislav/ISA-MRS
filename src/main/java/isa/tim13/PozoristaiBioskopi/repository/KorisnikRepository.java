package isa.tim13.PozoristaiBioskopi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isa.tim13.PozoristaiBioskopi.model.Korisnik;

@Repository
public interface KorisnikRepository extends CrudRepository<Korisnik, Integer> {
	Korisnik findByEmail(String email);
}
