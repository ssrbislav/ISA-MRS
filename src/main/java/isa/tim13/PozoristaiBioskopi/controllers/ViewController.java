package isa.tim13.PozoristaiBioskopi.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import isa.tim13.PozoristaiBioskopi.exceptions.NeovlascenPristupException;
import isa.tim13.PozoristaiBioskopi.model.InstitucijaKulture;
import isa.tim13.PozoristaiBioskopi.model.TipAdministratora;
import isa.tim13.PozoristaiBioskopi.model.TipInstitucijeKulture;
import isa.tim13.PozoristaiBioskopi.service.AuthService;
import isa.tim13.PozoristaiBioskopi.service.PozoristaIBioskopiService;


@Controller
public class ViewController {
	
	
@Autowired  
PozoristaIBioskopiService servis;

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

	// strana za profil
		@RequestMapping("/izmenaPodataka")
		public String izmenaPodataka() {
			return "izmenaPodataka";
			
		}
		
		// spisak bioskopa
		@RequestMapping("/bioskopi")
		public String bioskopi(HttpSession sesion) {
			
			ArrayList<InstitucijaKulture> institucije = (ArrayList<InstitucijaKulture>) servis.prikaziInstitucije() ;
			ArrayList<InstitucijaKulture> bioskopi = new ArrayList<InstitucijaKulture>() ;
			
			for (InstitucijaKulture i : institucije) {
				if (i.getTip()==TipInstitucijeKulture.BIOSKOP) {
					bioskopi.add(i);
					
				}
			}
			sesion.setAttribute("institucijePrikaz", bioskopi);
			return "BioskopiPozorista";
		}
		
		
		// spisak pozorista
		@RequestMapping("/pozorista")
		public String pozorista(HttpSession sesion) {
			
			ArrayList<InstitucijaKulture> institucije = (ArrayList<InstitucijaKulture>) servis.prikaziInstitucije() ;
			ArrayList<InstitucijaKulture> pozorista = new ArrayList<InstitucijaKulture>() ;
			
			for (InstitucijaKulture i : institucije) {
				if (i.getTip()==TipInstitucijeKulture.POZORISTE) {
					pozorista.add(i);
					
				}
			}
			sesion.setAttribute("institucijePrikaz", pozorista);
			return "BioskopiPozorista";
		}
		
		@RequestMapping("/repertoar")
		public String repertoar() {
			return "repertoar";
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
	
	@RequestMapping("/prikazTematskihRekvizita")
	public String prikazTematskihRekvizita(HttpSession s) {
		try {
			AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
		} catch (NeovlascenPristupException e) {
			return "prijava";
		}
		return "prikazTematskihRekvizita";
	}
	
	@RequestMapping("/dodavanjeTematskihRekvizita")
	public String dodavanjeTematskihRekvizita(HttpSession s) {
		try {
			AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
		} catch (NeovlascenPristupException e) {
			return "prijava";
		}
		return "dodavanjeTematskihRekvizita";
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
