package isa.tim13.PozoristaiBioskopi.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.Osoba;
import isa.tim13.PozoristaiBioskopi.model.PredstavaProjekcija;
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
					//za testiranje ispisa poseta
					PredstavaProjekcija p1= new PredstavaProjekcija();
					p1.setNaziv("ArenaCineplex");
					PredstavaProjekcija p2= new PredstavaProjekcija();
					p2.setNaziv("Narodno pozoriste");
					
					if(korisnik instanceof Korisnik) {
						((Korisnik) korisnik).getIstorijatPoseta().add(p1);
						((Korisnik) korisnik).getIstorijatPoseta().add(p2);
					}
					
					
					session.setAttribute("korisnik", korisnik);
					session.removeAttribute(PORUKA_GRESKE);
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
