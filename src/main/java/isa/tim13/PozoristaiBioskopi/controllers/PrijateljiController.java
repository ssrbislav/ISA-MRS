package isa.tim13.PozoristaiBioskopi.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import isa.tim13.PozoristaiBioskopi.dto.PrijateljDTO;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.service.KorisniciService;
@Controller
@RequestMapping("/prijatelji")
public class PrijateljiController {

	@Autowired
	KorisniciService korisniciServis;

	@RequestMapping(method = RequestMethod.POST, path = "/brisanje")
	public String repertoar(HttpSession sesion, @RequestParam("id") int ID) {

		Korisnik ulogovan=  (Korisnik) korisniciServis.pronadjiPoId(((Korisnik) sesion.getAttribute("korisnik")).getId()).get(); ;
		
		Korisnik prijatelj = (Korisnik) korisniciServis.pronadjiPoId(ID).get();
		
		prijatelj.getPrijatelji().remove(ulogovan );
		ulogovan.getPrijatelji().remove(prijatelj);
		
		korisniciServis.dodajKorisnika(ulogovan);
		korisniciServis.dodajKorisnika(prijatelj);
		sesion.setAttribute("korisnik", ulogovan);

		return "redirect:/prijatelji";
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/pretragaPrijatelja", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> pretraga(HttpSession session, @RequestParam("stringZaPretragu") String zaPretragu) {
		ArrayList<PrijateljDTO> pronadjeniKorisnici= new ArrayList<PrijateljDTO>();
		ArrayList <Korisnik> korisnici=korisniciServis.pronadjiSveKorisnike();
		Korisnik ulogovan = (Korisnik)session.getAttribute("korisnik");
		for (Korisnik korisnik : korisnici) {
			if(korisnik.getIme().toLowerCase().contains(zaPretragu.toLowerCase()) ||  korisnik.getPrezime().toLowerCase().contains(zaPretragu.toLowerCase())) {
				if(!ulogovan.getEmail().equalsIgnoreCase(korisnik.getEmail())) {
					PrijateljDTO prijatelj = new PrijateljDTO();
					prijatelj.setId(korisnik.getId());
					prijatelj.setEmail(korisnik.getEmail());
					prijatelj.setIme(korisnik.getIme());
					prijatelj.setPrezime(korisnik.getPrezime());
					prijatelj.setLokacijaSlike(korisnik.getLokacijaSlike());
					pronadjeniKorisnici.add(prijatelj);
				}
			}
		}
		return ResponseEntity.ok(pronadjeniKorisnici);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/dodavanje")
	public String dodajPrijatelja(HttpSession session, @RequestParam("id") int ID) {
			Korisnik ulogovan =(Korisnik) korisniciServis.pronadjiKorisnikaPoEmailu(((Korisnik)session.getAttribute("korisnik")).getEmail());
			
			Korisnik prijatelj =(Korisnik) korisniciServis.pronadjiPoId(ID).get();
			
			if (prijatelj.getZahtevi().contains(ulogovan) || ulogovan.getPrijatelji().contains(prijatelj)) {
				session.setAttribute("PrijateljPoruka", "Osoba vec postoji u listi prijatelja ili ste joj poslali zahtev.");
				
				
			}else {
				prijatelj.getZahtevi().add(ulogovan);
				korisniciServis.dodajKorisnika(prijatelj);
				session.setAttribute("PrijateljPoruka", "Uspesno ste poslali zahtev za prijateljstvo.");
			}
			return "redirect:/prijatelji";
	}
	@RequestMapping(method = RequestMethod.POST, path = "/prihvatiZahtev")
	public String prihvatiZahtev(HttpSession session, @RequestParam("id") int ID, @RequestParam("from") String from) {
		
		Korisnik ulogovan =(Korisnik) korisniciServis.pronadjiKorisnikaPoEmailu(((Korisnik)session.getAttribute("korisnik")).getEmail());
		
		Korisnik prijatelj =(Korisnik) korisniciServis.pronadjiPoId(ID).get();
		
		ulogovan.getPrijatelji().add(prijatelj);
		prijatelj.getPrijatelji().add(ulogovan);
		ulogovan.getZahtevi().remove(prijatelj);
		
		korisniciServis.dodajKorisnika(prijatelj);
		korisniciServis.dodajKorisnika(ulogovan);
		session.setAttribute("korisnik", (Korisnik) korisniciServis.pronadjiPoId(ulogovan.getId()).get());
		return "redirect:"+from.split("\\.")[0];
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/odbijZahtev")
	public String odbijZahtev(HttpSession session, @RequestParam("id") int ID, @RequestParam("from") String from) {
		
		Korisnik ulogovan =(Korisnik) korisniciServis.pronadjiKorisnikaPoEmailu(((Korisnik)session.getAttribute("korisnik")).getEmail());
		
		Korisnik prijatelj =(Korisnik) korisniciServis.pronadjiPoId(ID).get();
		
		ulogovan.getZahtevi().remove(prijatelj);
		
		korisniciServis.dodajKorisnika(ulogovan);
		session.setAttribute("korisnik", (Korisnik) korisniciServis.pronadjiPoId(ulogovan.getId()).get());
		return "redirect:"+from.split("\\.")[0];
	}
}
