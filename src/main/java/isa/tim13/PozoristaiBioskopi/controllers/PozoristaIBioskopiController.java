package isa.tim13.PozoristaiBioskopi.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.tim13.PozoristaiBioskopi.model.InstitucijaKulture;
import isa.tim13.PozoristaiBioskopi.service.PozoristaIBioskopiService;

@RestController
@RequestMapping("/pozoristaibioskopi")
public class PozoristaIBioskopiController {
	
	@Autowired
	PozoristaIBioskopiService servis;
	
	@RequestMapping(method=RequestMethod.GET)
	public Collection<InstitucijaKulture> prikaziInstitucije() {
		return null;
	}
	
	@RequestMapping(value = "/registruj", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void registrujInstituciju(@RequestBody InstitucijaKulture k) {
		servis.dodajInstitucijuKulture(k);
	}
}
