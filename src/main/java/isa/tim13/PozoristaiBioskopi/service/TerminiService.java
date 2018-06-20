package isa.tim13.PozoristaiBioskopi.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.tim13.PozoristaiBioskopi.model.Termin;
import isa.tim13.PozoristaiBioskopi.repository.TerminRepository;

@Service
public class TerminiService {

	@Autowired
	TerminRepository rep;

	public void dodajTermin(Termin t) {
		rep.save(t);
	}

	public Collection<Termin> prikaziTermine() {
		return (Collection<Termin>) rep.findAll();
	}

	public Optional<Termin> findById(int id) {
		return rep.findById(id);
	}

}
