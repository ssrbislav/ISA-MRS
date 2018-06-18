package isa.tim13.PozoristaiBioskopi.controllers;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import isa.tim13.PozoristaiBioskopi.model.Karta;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.Osoba;
import isa.tim13.PozoristaiBioskopi.service.KorisniciService;

@Controller
@RequestMapping("/login")
public class LoginController {
	private static final String PORUKA_GRESKE = "porukaGreske";
	
	@Autowired
	private KorisniciService korisniciServis;
	
	@RequestMapping(method=RequestMethod.POST)
	public void prijava(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(value="email", required=true) String email, @RequestParam(value="lozinka", required=true) String lozinka) throws ServletException, IOException{
		
		Osoba korisnik = korisniciServis.pronadjiKorisnikaPoEmailu(email);
		
		if(korisnik != null) {
			if(korisnik.getAktivan()) {
				if(lozinka.equals(korisnik.getLozinka())){
					
					
					session.setAttribute("korisnik", korisnik);
					session.removeAttribute(PORUKA_GRESKE);
					if (korisnik instanceof Korisnik) {
					for ( Karta k  : ((Korisnik)korisnik).getKarte() ) {
						LocalDateTime kartaVreme =  LocalDateTime.of(k.getRezervacija().getTermin().getDatum(),k.getRezervacija().getTermin().getVreme());
						
							if (kartaVreme.minusMinutes(30).compareTo(LocalDateTime.now())<0) {
								if (!((Korisnik)korisnik).getIstorijatPoseta().contains(k.getRezervacija().getTermin().getpredProj())) {
								((Korisnik)korisnik).getIstorijatPoseta().add(k.getRezervacija().getTermin().getpredProj());
								}
							}
						}
					korisniciServis.dodajKorisnika(korisnik);
					}
					
					response.sendRedirect("/profilKorisnika");
					return;
				}else {
					request.setAttribute(PORUKA_GRESKE, "Uneli ste netacnu email adresu i/ili lozinku.");
				}
			} else {
				request.setAttribute(PORUKA_GRESKE, "Niste izvrsili aktivaciju naloga.");
			}
		}else{
			request.setAttribute(PORUKA_GRESKE, "Uneli ste netacnu email adresu i/ili lozinku.");
		}
		request.getRequestDispatcher("/prijava").forward(request, response);
		
}}
