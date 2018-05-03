package isa.tim13.PozoristaiBioskopi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.Osoba;

@Repository
public interface KorisnikRepository extends CrudRepository<Osoba, Integer> {
	Osoba findByEmail(String email);
	Korisnik findByRegistracioniLink(String registracioniLink);
}
