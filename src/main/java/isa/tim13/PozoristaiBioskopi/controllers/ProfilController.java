package isa.tim13.PozoristaiBioskopi.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import isa.tim13.PozoristaiBioskopi.exceptions.NeovlascenPristupException;
import isa.tim13.PozoristaiBioskopi.model.FanZonaAdministrator;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.Osoba;
import isa.tim13.PozoristaiBioskopi.service.AuthService;
import isa.tim13.PozoristaiBioskopi.service.KorisniciService;
import isa.tim13.PozoristaiBioskopi.service.ProfilSlikeService;

@Controller
@RequestMapping("/profil")
public class ProfilController {
	private static final String PORUKA_GRESKE = "porukaGreske";

	@Autowired
	private KorisniciService korisniciServis;
	
	@Autowired
	private ProfilSlikeService servis;
	
	//dobavice putanju do slike bedza koji govori koji je tip korisnika u pitanju(bronzani,srebrni,zlatni)
	@RequestMapping(value="/dobaviBedz",method=RequestMethod.GET)
	public @ResponseBody String dobaviBedz(HttpSession s) throws NeovlascenPristupException {
		Korisnik kor = AuthService.korisnikProvera(s);
		return korisniciServis.dobaviBedz(kor);
	}

	@RequestMapping(value = "/izmenaLozinke", method = RequestMethod.POST)
	public String izmeniLozinku( HttpServletRequest request, HttpSession session,
			@RequestParam(value = "lozinka1", required = true) String lozinka) {

		Osoba korisnik = korisniciServis.pronadjiKorisnikaPoEmailu(((Osoba)session.getAttribute("korisnik")).getEmail());
		
		korisnik.setLozinka(lozinka);
		
		if(korisnik instanceof FanZonaAdministrator) {
			((FanZonaAdministrator) korisnik).setPromenioSifru(true);
		}
		
		session.setAttribute("korisnik",korisnik);
		
		korisniciServis.dodajKorisnika(korisnik);
	
		return "redirect:/profilKorisnika";

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
	
		return "redirect:/profilKorisnika";

	}
	
	@RequestMapping(value = "/dodajSliku", method=RequestMethod.POST)
	public String dodajSliku(HttpSession session, @RequestParam("file") MultipartFile file) {
		try {
			if(!file.getOriginalFilename().equals("")) {
				servis.dodajSlikuOsobi((Osoba)session.getAttribute("korisnik"),file);
			}
		} catch (IOException e) {
			System.out.println("Doslo je do greske prilikom baratanja s fajlom ili JSONOM");
		}
		return "redirect:/profilKorisnika";
	}
}
