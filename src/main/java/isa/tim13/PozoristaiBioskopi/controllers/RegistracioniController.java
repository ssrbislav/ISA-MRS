package isa.tim13.PozoristaiBioskopi.controllers;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.tim13.PozoristaiBioskopi.dto.RegisterDTO;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.Osoba;
import isa.tim13.PozoristaiBioskopi.model.PredstavaProjekcija;
import isa.tim13.PozoristaiBioskopi.service.EmailService;
import isa.tim13.PozoristaiBioskopi.service.KorisniciService;

@RestController
@RequestMapping("/registerUser")
public class RegistracioniController {
	
	@Autowired
	private KorisniciService korisniciServis;
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(method=RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registruj(@RequestBody RegisterDTO registracija) {
		Osoba noviKorisnik  = korisniciServis.pronadjiKorisnikaPoEmailu(registracija.getEmail());
		if(registracija.getLozinka1().equals(registracija.getLozinka2()) && noviKorisnik == null) {
			noviKorisnik = new Korisnik();
			noviKorisnik(registracija, (Korisnik)noviKorisnik);
			korisniciServis.dodajKorisnika((Korisnik)noviKorisnik);
			mail(noviKorisnik.getEmail(), noviKorisnik.getRegistracioniLink());
			return  ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();	
	}

	private void noviKorisnik(RegisterDTO registracija, Korisnik noviKorisnik) {
		noviKorisnik.setEmail(registracija.getEmail());
		noviKorisnik.setIme(registracija.getIme());
		noviKorisnik.setPrezime(registracija.getPrezime());
		noviKorisnik.setTelefon(registracija.getTelefon());
		noviKorisnik.setGrad(registracija.getGrad());
		noviKorisnik.setLozinka(registracija.getLozinka1());
		
		noviKorisnik.setRegistracioniLink(UUID.randomUUID().toString());
		noviKorisnik.setAktivan(false);

		noviKorisnik.setLokacijaSlike("");
		
		noviKorisnik.setBrojBodova(0.0);
		noviKorisnik.setIstorijatPoseta(new ArrayList<PredstavaProjekcija>());
		noviKorisnik.setPrijatelji(new ArrayList<Korisnik>());
		noviKorisnik.setZahtevi(new ArrayList<Korisnik>());
	}
	
	private void mail(String mailTo, String registracioniLink) {
		String naslov = "Potvrda registracije";
		String sadrzaj = "Pozdrav, molimo Vas da potvrdite registraciju putem sledeceg linka:\nhttp://localhost:8080/aktivacija/"+ registracioniLink;
		emailService.sendEmail(mailTo, naslov, sadrzaj);
	}
}
