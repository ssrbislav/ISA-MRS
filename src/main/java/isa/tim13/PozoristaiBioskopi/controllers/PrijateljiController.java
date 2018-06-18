package isa.tim13.PozoristaiBioskopi.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.service.KorisniciService;
@Controller
@RequestMapping("/prijatelji")
public class PrijateljiController {

	@Autowired
	KorisniciService korisniciServis;

	@RequestMapping(method = RequestMethod.POST, path = "/brisanje")
	public String repertoar(HttpSession sesion, @RequestParam("id") int ID) {

		Korisnik ulogovan=  (Korisnik) korisniciServis.pronadjiPoId(((Korisnik) sesion.getAttribute("korisnik")).getId()).get(); ;
		
		Korisnik prijatelj = (Korisnik) korisniciServis.pronadjiPoId(ID).get();
		
		prijatelj.getPrijatelji().remove(ulogovan);
		ulogovan.getPrijatelji().remove(prijatelj);
		
		korisniciServis.dodajKorisnika(ulogovan);
		korisniciServis.dodajKorisnika(prijatelj);
		sesion.setAttribute("korisnik", ulogovan);

		return "../prijatelji";
	}
	

}
