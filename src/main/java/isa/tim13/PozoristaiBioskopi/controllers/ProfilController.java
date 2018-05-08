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

	@RequestMapping(value = "/izmenaLozinke", method = RequestMethod.POST)
	public String izmeniLozinku( HttpServletRequest request, HttpSession session,
			@RequestParam(value = "lozinka1", required = true) String lozinka) {

		Osoba korisnik = korisniciServis.pronadjiKorisnikaPoEmailu(((Osoba)session.getAttribute("korisnik")).getEmail());
		
		korisnik.setLozinka(lozinka);
		session.setAttribute("korisnik",korisnik);
		
		korisniciServis.dodajKorisnika(korisnik);
	
		return "/profilKorisnika";

	}
	
	@RequestMapping( value = "/izmenaPodataka",method = RequestMethod.POST)
	public String izmenaPodataka(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "ime", required = true) String ime, @RequestParam(value = "prezime", required = true) String prezime, @RequestParam(value = "grad", required = true) String grad, @RequestParam(value = "telefon", required = true) String telefon ) {

		Osoba korisnik = korisniciServis.pronadjiKorisnikaPoEmailu(((Osoba)session.getAttribute("korisnik")).getEmail());
		
		korisnik.setIme(ime);
		korisnik.setPrezime(prezime);
		korisnik.setGrad(grad);
		korisnik.setTelefon(telefon);
		
		session.setAttribute("korisnik",korisnik);
		
		korisniciServis.dodajKorisnika(korisnik);
	
		return "/profilKorisnika";

	}
}
