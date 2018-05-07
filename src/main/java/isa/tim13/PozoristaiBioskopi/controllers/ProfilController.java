package isa.tim13.PozoristaiBioskopi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import isa.tim13.PozoristaiBioskopi.model.Osoba;
import isa.tim13.PozoristaiBioskopi.service.KorisniciService;

@Controller
@RequestMapping("/profil")
public class ProfilController {
	private static final String PORUKA_GRESKE = "porukaGreske";

	@Autowired
	private KorisniciService korisniciServis;

	@RequestMapping(method = RequestMethod.POST)
	public String izmeniLozinku(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "lozinka1", required = true) String lozinka) {

		Osoba korisnik = korisniciServis.pronadjiKorisnikaPoEmailu(((Osoba)session.getAttribute("korisnik")).getEmail());
		
		korisnik.setLozinka(lozinka);
		session.setAttribute("korisnik",korisnik);
		
		korisniciServis.dodajKorisnika(korisnik);
	
		return "profilKorisnika";

	}
}
