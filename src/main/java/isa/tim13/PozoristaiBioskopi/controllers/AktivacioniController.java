package isa.tim13.PozoristaiBioskopi.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.service.KorisniciService;

@Controller
public class AktivacioniController {
	
	@Autowired
	private KorisniciService korisniciServis;
	
	@RequestMapping("/aktivacija/{registracioniLink}")
	public void aktivacija(@PathVariable("registracioniLink") String registracioniLink, HttpServletResponse response) throws IOException {
		Korisnik kor = korisniciServis.pronadjiNeaktivnogKorisnika(registracioniLink);
		if(kor != null) {
			kor.setAktivan(true);
			korisniciServis.dodajKorisnika(kor);
		}else {
			System.out.println("Korisnik sa datim aktivacionim linkom nije pronadjen!");
		}
		response.sendRedirect("/prijava");
	}

}
