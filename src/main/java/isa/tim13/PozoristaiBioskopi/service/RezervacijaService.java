package isa.tim13.PozoristaiBioskopi.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.tim13.PozoristaiBioskopi.model.PredstavaProjekcija;
import isa.tim13.PozoristaiBioskopi.model.Rezervacija;
import isa.tim13.PozoristaiBioskopi.repository.RezervacijaRepository;
@Service
public class RezervacijaService {
	@Autowired
	RezervacijaRepository rep;
	
	public void dodajRezervaciju(Rezervacija rez) {
		rep.save(rez);
	}
	
	public Optional<Rezervacija> findById( int id) {
		
		return rep.findById(id);
	}
	public void obrisiRezervaciju(Rezervacija rez) {
		rep.delete(rez);
		
	}
	

}
