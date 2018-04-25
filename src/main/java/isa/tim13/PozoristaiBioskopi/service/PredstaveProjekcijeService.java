package isa.tim13.PozoristaiBioskopi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.tim13.PozoristaiBioskopi.model.PredstavaProjekcija;
import isa.tim13.PozoristaiBioskopi.repository.PredstavaProjekcijaRepository;

@Service
public class PredstaveProjekcijeService {

	@Autowired
	PredstavaProjekcijaRepository rep;
	
	public void dodajPredstavuProjekciju(PredstavaProjekcija pp) {
		rep.save(pp);
	}
}
