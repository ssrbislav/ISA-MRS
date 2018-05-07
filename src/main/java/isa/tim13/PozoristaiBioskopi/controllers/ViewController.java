package isa.tim13.PozoristaiBioskopi.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import isa.tim13.PozoristaiBioskopi.exceptions.NeovlascenPristupException;
import isa.tim13.PozoristaiBioskopi.model.InstitucijaKulture;
import isa.tim13.PozoristaiBioskopi.model.TipAdministratora;
import isa.tim13.PozoristaiBioskopi.service.AuthService;

@Controller
public class ViewController {
	// Pocetna strana
	@RequestMapping("/")
	public String welcome() {
		return "prijava";
	}

	// strana za registraciju
	@RequestMapping("/registracija")
	public String registracija() {
		return "registracija";
	}

	// strana za prijavu
	@RequestMapping("/prijava")
	public String prijava() {
		return "prijava";
	}

	// strana za profil
	@RequestMapping("/profilKorisnika")
	public String profil() {
		return "profilKorisnika";
	}

	// strana za profil
	@RequestMapping("/izmenaLozinke")
	public String lozinka() {
		return "izmenaLozinke";
	}

	@RequestMapping("/projekcija")
	public String projekcija() {
		return "projekcija";
	}

	@RequestMapping("/predstava")
	public String predstava() {
		return "predstava";
	}

	@RequestMapping("/registracijaInstitucija")
	public String registracijaInstitucija(HttpSession s) {
		try {
			AuthService.adminProvera(s, TipAdministratora.SISTEMSKI);
		} catch (NeovlascenPristupException e) {
			return "prijava";
		}
		return "registracijaInstitucija";
	}

	@RequestMapping("/dodavanjeAdministratora")
	public String dodavanjeAdministratora(HttpSession s) {
		try {
			AuthService.adminProvera(s, TipAdministratora.SISTEMSKI);
		} catch (NeovlascenPristupException e) {
			return "prijava";
		}
		return "dodavanjeAdministratora";
	}

}
