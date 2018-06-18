package isa.tim13.PozoristaiBioskopi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.tim13.PozoristaiBioskopi.model.Karta;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.repository.KartaRepository;
@Service
public class KartaService {
	
	@Autowired
	KartaRepository rep;
	
	public void dodajKartu(Karta K) {
		rep.save(K);
	}
	
	public void obrisiKartu(Karta k) {
		rep.delete(k);
		
	}
	public Optional<Karta> pronadjiKartu(int id) {
		return rep.findById(id);
	}
	
	public Karta pronadjiKartuZaOtkazivanje(String linkZaOtkazivanje) {
		return rep.findByLinkZaOtkazivanje(linkZaOtkazivanje);
	}

}
