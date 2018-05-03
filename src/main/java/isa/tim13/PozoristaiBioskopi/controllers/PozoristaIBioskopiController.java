package isa.tim13.PozoristaiBioskopi.controllers;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import isa.tim13.PozoristaiBioskopi.exceptions.NeovlascenPristupException;
import isa.tim13.PozoristaiBioskopi.model.InstitucijaKulture;
import isa.tim13.PozoristaiBioskopi.model.TipAdministratora;
import isa.tim13.PozoristaiBioskopi.service.AuthService;
import isa.tim13.PozoristaiBioskopi.service.PozoristaIBioskopiService;

@Controller
@RequestMapping("/pozoristaibioskopi")
public class PozoristaIBioskopiController {
	
	@Autowired
	PozoristaIBioskopiService servis;
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<InstitucijaKulture> prikaziInstitucije() {
		return servis.prikaziInstitucije();
	}
	
	@RequestMapping(value = "/registruj", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<InstitucijaKulture> registrujInstituciju(@RequestBody InstitucijaKulture k,HttpSession s) {
		try {
			AuthService.adminProvera(s, TipAdministratora.SISTEMSKI);
			servis.dodajInstitucijuKulture(k);
			return new ResponseEntity<InstitucijaKulture>(k,HttpStatus.CREATED);
		} catch (NeovlascenPristupException e) {
			return new ResponseEntity<InstitucijaKulture>(k,HttpStatus.FORBIDDEN);
		}
		
	}
	
	
}
