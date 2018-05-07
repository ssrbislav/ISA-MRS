package isa.tim13.PozoristaiBioskopi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.Osoba;
import isa.tim13.PozoristaiBioskopi.repository.KorisnikRepository;

@Service
public class KorisniciService {
	@Autowired
	KorisnikRepository rep;
	
	public void dodajKorisnika(Osoba kor) {
		rep.save(kor);
	}
	
	public Osoba pronadjiKorisnikaPoEmailu(String email) {
		return rep.findByEmail(email);
	}
	
	public Korisnik pronadjiNeaktivnogKorisnika(String registracioniLink) {
		return rep.findByRegistracioniLink(registracioniLink);
	}

}
