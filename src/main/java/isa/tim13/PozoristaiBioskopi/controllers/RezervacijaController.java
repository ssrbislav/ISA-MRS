package isa.tim13.PozoristaiBioskopi.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import isa.tim13.PozoristaiBioskopi.model.PredstavaProjekcija;
import isa.tim13.PozoristaiBioskopi.model.Rezervacija;
import isa.tim13.PozoristaiBioskopi.model.Termin;
import isa.tim13.PozoristaiBioskopi.service.PredstaveProjekcijeService;
import isa.tim13.PozoristaiBioskopi.service.TerminiService;


@Controller
@RequestMapping("/rezervacija")

public class RezervacijaController {

		@Autowired
		PredstaveProjekcijeService servis;
		@Autowired
		TerminiService servis1;
		
		@RequestMapping(method=RequestMethod.POST, path="/termin")
		public String repertoar(HttpSession sesion, @RequestParam ("id") int  ID ) {
			
				PredstavaProjekcija predstava= servis.findById(ID).get();
				
				ArrayList<String> datumiPrikazivanja = new ArrayList<String>();
				
				for (Termin i : predstava.getTermini() ) {
					if (!datumiPrikazivanja.contains(i.getDatum())) {
						
						datumiPrikazivanja.add(i.getDatum());
					}
				}
				
				sesion.setAttribute("predstavaZaRezervaciju", predstava);
				sesion.setAttribute("datumiPrikazivanja", datumiPrikazivanja);
				return "../rezervacija";
		}
		
		@RequestMapping(method=RequestMethod.POST , path="/mesta")
		public String odabirMesta(HttpSession sesion, @RequestParam ("id") int  ID ) {
			
				Termin zakazanTermin= servis1.findById(ID).get();
				
				Rezervacija r= new Rezervacija ();
				r.setTermin(zakazanTermin);
				
				sesion.setAttribute("rezervacija", r);
				
				return "../odabirMesta";
		}
	}

	
