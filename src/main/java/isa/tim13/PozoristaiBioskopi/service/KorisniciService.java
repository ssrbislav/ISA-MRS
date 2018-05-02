package isa.tim13.PozoristaiBioskopi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.repository.KorisnikRepository;

@Service
public class KorisniciService {
	@Autowired
	KorisnikRepository rep;
	
	public void dodajKorisnika(Korisnik kor) {
		rep.save(kor);
	}
	
	public Korisnik pronadjiKorisnikaPoEmailu(String email) {
		return rep.findByEmail(email);
	}
	
	public Korisnik pronadjiNeaktivnogKorisnika(String registracioniLink) {
		return rep.findByRegistracioniLink(registracioniLink);
	}

}
