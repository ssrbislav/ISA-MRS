package isa.tim13.PozoristaiBioskopi.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import isa.tim13.PozoristaiBioskopi.exceptions.NeovlascenPristupException;
import isa.tim13.PozoristaiBioskopi.model.Administrator;
import isa.tim13.PozoristaiBioskopi.model.FanZonaAdministrator;
import isa.tim13.PozoristaiBioskopi.model.InstitucijaKulture;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
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
	public String profil(HttpSession s) {
		if(s.getAttribute("korisnik") instanceof Administrator) {
			return "profilAdministratora";
		}
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
		
		@RequestMapping("/odjava")
		public String odjava(HttpSession sesion) {
			sesion.invalidate();
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
			AuthService.osobaProvera(s,FanZonaAdministrator.class,Korisnik.class);
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
	
	@RequestMapping("/pregledNeobjavljenihObjava")
	public String pregledNeobjavljenihObjava(HttpSession s) {
		try {
			AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
		} catch (NeovlascenPristupException e) {
			return "prijava";
		}
		return "pregledNeobjavljenihObjava";
	}
	
	@RequestMapping("/pregledRazmatranihObjava")
	public String pregledRazmatranihObjava(HttpSession s) {
		try {
			AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
		} catch (NeovlascenPristupException e) {
			return "prijava";
		}
		return "pregledRazmatranihObjava";
	}
	
	@RequestMapping("/dodavanjeObjava")
	public String dodavanjeObjava(HttpSession s) {
		try {
			AuthService.korisnikProvera(s);
		} catch (NeovlascenPristupException e) {
			return "prijava";
		}
		return "dodavanjeObjava";
	}
	
	@RequestMapping("/pregledObjavljenihObjava")
	public String pregledObjavljenihObjava(HttpSession s) {
		try {
			AuthService.korisnikProvera(s);
		} catch (NeovlascenPristupException e) {
			return "prijava";
		}
		return "pregledObjavljenihObjava";
	}
	
	@RequestMapping(value="/prikazObjave" ,method=RequestMethod.GET)
	public String prikazObjave(HttpSession s,@RequestParam("id") int id) {
		
		try {
			AuthService.korisnikProvera(s);
		} catch (NeovlascenPristupException e) {
			return "redirect:/prijava";
		}
		s.setAttribute("idObjave", id);
		
		return "prikazObjave";
	}

}
