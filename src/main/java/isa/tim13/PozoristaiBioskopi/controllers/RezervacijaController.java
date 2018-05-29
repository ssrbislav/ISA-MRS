package isa.tim13.PozoristaiBioskopi.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import isa.tim13.PozoristaiBioskopi.model.Karta;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.Osoba;
import isa.tim13.PozoristaiBioskopi.model.PredstavaProjekcija;
import isa.tim13.PozoristaiBioskopi.model.Rezervacija;
import isa.tim13.PozoristaiBioskopi.model.Termin;
import isa.tim13.PozoristaiBioskopi.service.KorisniciService;
import isa.tim13.PozoristaiBioskopi.service.PredstaveProjekcijeService;
import isa.tim13.PozoristaiBioskopi.service.TerminiService;

@Controller
@RequestMapping("/rezervacija")

public class RezervacijaController {

	@Autowired
	PredstaveProjekcijeService servis;
	@Autowired
	TerminiService servis1;
	@Autowired
	KorisniciService korisniciServis;

	@RequestMapping(method = RequestMethod.POST, path = "/termin")
	public String repertoar(HttpSession sesion, @RequestParam("id") int ID) {

		PredstavaProjekcija predstava = servis.findById(ID).get();

		ArrayList<String> datumiPrikazivanja = new ArrayList<String>();

		for (Termin i : predstava.getTermini()) {
//			 pravljenje matrice mesta radi testiranja prikaza sale. nemamo studenta koji
//			 radi unos termina
			int redovi = i.getSala().getBrojVrsta();
			int kolone = i.getSala().getBrojKolona();
			boolean[][] sala = new boolean[redovi][kolone];
			i.setMesta(sala);
			servis1.dodajTermin(i);
			
			if (!datumiPrikazivanja.contains(i.getDatum())) {

				datumiPrikazivanja.add(i.getDatum());
			}
		}

		sesion.setAttribute("predstavaZaRezervaciju", predstava);
		sesion.setAttribute("datumiPrikazivanja", datumiPrikazivanja);
		return "../rezervacija";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/mesta")
	public String odabirMesta(HttpSession session, @RequestParam("id") int ID) {

		Termin zakazanTermin = servis1.findById(ID).get();

		Rezervacija r = new Rezervacija();
		r.setTermin(zakazanTermin);

		session.setAttribute("rezervacija", r);
		return "../odabirMesta";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/zakazivanje")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public String rezervacijaMesta(HttpSession sesion, @RequestParam("odabranaMesta") List<String> mesta) {
		Rezervacija r = (Rezervacija) sesion.getAttribute("rezervacija");
		Termin t = r.getTermin();
		Korisnik ulogovan = (Korisnik)sesion.getAttribute("korisnik");
		ArrayList<Karta> karte = new ArrayList<>();
		for (String mesto : mesta) {
			Karta k = new Karta();
			k.setRezervacija(r);
			karte.add(k);
			String[] sediste_korisnik = mesto.split("-");
			String email = sediste_korisnik.length>1 ? sediste_korisnik[1] : "ulogovan";
			String[] kordinate = sediste_korisnik[0].split("_");
			int x = Integer.parseInt(kordinate[0]) - 1;
			int y = Integer.parseInt(kordinate[1]) - 1;
			t.getMesta()[x][y] = true;
			
			int [] koordinateMesta = {x,y};
			k.setSediste(koordinateMesta);
			if(email.equals("ulogovan")) {
				k.setOsoba(ulogovan);
				ulogovan.getKarte().add(k);
				
			}else {
				Korisnik prijatelj = null;
				for (Osoba i : ulogovan.getPrijatelji()) {
					if(i.getEmail().equals(email)) {
						prijatelj = (Korisnik) i;
						break;
					}
				}
				k.setOsoba(prijatelj);
				prijatelj.getKarte().add(k);
			}
			
		}
		ulogovan.getRezervacije().add(r);
		r.setBodovi(mesta.size() * 5);
		r.setUkupnaCena(r.getTermin().getCena()*mesta.size());
		r.setKarte(karte);
		
		korisniciServis.dodajKorisnika(ulogovan);
		servis1.dodajTermin(t);
		return "../profilKorisnika";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/matrica")
	@ResponseBody
	public boolean[][] matricaTermina(HttpSession session) {
		Rezervacija r = (Rezervacija) session.getAttribute("rezervacija");
		return r.getTermin().getMesta();
	}
}
