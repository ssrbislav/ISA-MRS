package isa.tim13.PozoristaiBioskopi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.tim13.PozoristaiBioskopi.model.InstitucijaKulture;
import isa.tim13.PozoristaiBioskopi.repository.InstitucijaKultureRepository;

@Service
public class PozoristaIBioskopiService {
	
	@Autowired
	InstitucijaKultureRepository rep;
	
	public void dodajInstitucijuKulture(InstitucijaKulture k) {
		//Servis postoji zato sto ovde mogu da se izvrse razne provere pre samog modifikovanja
		rep.save(k);
		
	}
}
