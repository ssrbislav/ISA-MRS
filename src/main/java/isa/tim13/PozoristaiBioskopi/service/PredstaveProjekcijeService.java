package isa.tim13.PozoristaiBioskopi.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.tim13.PozoristaiBioskopi.model.InstitucijaKulture;
import isa.tim13.PozoristaiBioskopi.model.PredstavaProjekcija;
import isa.tim13.PozoristaiBioskopi.repository.PredstavaProjekcijaRepository;

@Service
public class PredstaveProjekcijeService {

	@Autowired
	PredstavaProjekcijaRepository rep;
	
	public void dodajPredstavuProjekciju(PredstavaProjekcija pp) {
		rep.save(pp);
	}
	
	public Collection<PredstavaProjekcija> prikaziPredstaveProjekcije() {
		return (Collection<PredstavaProjekcija>) rep.findAll();
	}
	
public Optional<PredstavaProjekcija> findById( int id) {
		
		return rep.findById(id);
	}
	
}
