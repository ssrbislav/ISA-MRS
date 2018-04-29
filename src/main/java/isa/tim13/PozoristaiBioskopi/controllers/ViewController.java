package isa.tim13.PozoristaiBioskopi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	// Pocetna strana
	@RequestMapping("/")
	public String welcome() {
		return "registracija";
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
	
	@RequestMapping("/projekcija")
	public String projekcija() {
		return "projekcija";
	}
	
	@RequestMapping("/predstava")
	public String predstava() {
		return "predstava";
	}
	
	@RequestMapping("/registracijaInstitucija")
	public String registracijaInstitucija() {
		return "registracijaInstitucija";
	}

}