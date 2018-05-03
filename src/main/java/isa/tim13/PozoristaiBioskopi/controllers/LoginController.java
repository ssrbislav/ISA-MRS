package isa.tim13.PozoristaiBioskopi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import isa.tim13.PozoristaiBioskopi.model.Osoba;
import isa.tim13.PozoristaiBioskopi.service.KorisniciService;

@Controller
@RequestMapping("/login")
public class LoginController {
	private static final String PORUKA_GRESKE = "porukaGreske";
	
	@Autowired
	private KorisniciService korisniciServis;

	@RequestMapping(method=RequestMethod.POST)
	public String registruj(HttpServletRequest request,HttpSession session, @RequestParam(value="email", required=true) String email, @RequestParam(value="lozinka", required=true) String lozinka){
		
		Osoba korisnik = korisniciServis.pronadjiKorisnikaPoEmailu(email);
		
		if(korisnik != null) {
			if(korisnik.getAktivan()) {
				if(lozinka.equals(korisnik.getLozinka())){
					session.setAttribute("korisnik", korisnik);
					session.setAttribute("ulogovan", korisnik.getIme()+" "+korisnik.getPrezime());
					session.removeAttribute(PORUKA_GRESKE);
					return "profilKorisnika";
				}else {
					request.setAttribute(PORUKA_GRESKE, "Uneli ste netacnu email adresu i/ili lozinku.");
				}
			} else {
				request.setAttribute(PORUKA_GRESKE, "Niste izvrsili aktivaciju naloga.");
			}
		}else{
			request.setAttribute(PORUKA_GRESKE, "Uneli ste netacnu email adresu i/ili lozinku.");
		}
		return "prijava";
		
}}
