package isa.tim13.PozoristaiBioskopi.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import isa.tim13.PozoristaiBioskopi.model.Karta;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.Rezervacija;
import isa.tim13.PozoristaiBioskopi.service.KartaService;
import isa.tim13.PozoristaiBioskopi.service.KorisniciService;
import isa.tim13.PozoristaiBioskopi.service.RezervacijaService;

@Controller

public class OtkazivanjeRezervacijeController {

	@Autowired
	RezervacijaService servis;
	@Autowired
	KartaService servis1;
	@Autowired
	KorisniciService korisniciServis;

	@RequestMapping(method = RequestMethod.POST ,path ="/otkazivanje")
	public String otkazivanje(HttpSession sesion, @RequestParam("id") int ID) {
		sesion.removeAttribute("Poruka");
		Rezervacija rez = servis.findById(ID).get();
		LocalDateTime termin = LocalDateTime.of(rez.getTermin().getDatum(), rez.getTermin().getVreme());
		if (!(termin.minusMinutes(30).compareTo(LocalDateTime.now()) < 0)) {

			ArrayList<Karta> karteZaBriranje = new ArrayList<Karta>();
			for (Karta k : rez.getKarte()) {
				rez.getTermin().getMesta()[k.getSediste()[0]][k.getSediste()[1] - 1] = false;
				k.getOsoba().getKarte().remove(k);
				k.getOsoba().getRezervacije().remove(rez);
				karteZaBriranje.add(k);
			}
			rez.getKarte().clear();
			Korisnik ulogovan = (Korisnik) korisniciServis.pronadjiKorisnikaPoEmailu(((Korisnik)sesion.getAttribute("korisnik")).getEmail());
			ulogovan.setBrojBodova(ulogovan.getBrojBodova() - rez.getBodovi());
			ulogovan.getRezervacije().remove(rez);
			for (Karta k : karteZaBriranje) {
				korisniciServis.dodajKorisnika(k.getOsoba());
			}
			servis.obrisiRezervaciju(rez);
			korisniciServis.dodajKorisnika(ulogovan);
			sesion.setAttribute("korisnik", ulogovan);

		} else {
			sesion.setAttribute("Poruka", "Ne mozete otkazati rezervaciju predstave/projekcije koja pocinje za manje od 30min.");

		}

		return "/rezervacije";
	}
	@RequestMapping(path="otkazivanje/{linkZaOtkazivanje}")
	public String otkazivanjePutemLinka(HttpSession session, @PathVariable("linkZaOtkazivanje") String linkZaOtkazivanje, HttpServletResponse response) throws IOException {
		 Karta k = servis1.pronadjiKartuZaOtkazivanje(linkZaOtkazivanje);
		 if(k != null) {
			 LocalDateTime termin = LocalDateTime.of(k.getRezervacija().getTermin().getDatum(), k.getRezervacija().getTermin().getVreme());
			 if (!(termin.minusMinutes(30).compareTo(LocalDateTime.now()) < 0)) {
				 k.getOsoba().getKarte().remove(k);
				 k.getRezervacija().getKarte().remove(k);
				 k.getRezervacija().setBodovi(k.getRezervacija().getBodovi()-5);
				 k.getRezervacija().getKorisnik().setBrojBodova(k.getRezervacija().getKorisnik().getBrojBodova()-5);
				 k.getRezervacija().setUkupnaCena(k.getRezervacija().getUkupnaCena()-k.getRezervacija().getTermin().getCena());
				 
				 k.getRezervacija().getTermin().getMesta()[k.getSediste()[0]][k.getSediste()[1] - 1] = false;
				 
				 servis.dodajRezervaciju(k.getRezervacija());
				 korisniciServis.dodajKorisnika(k.getOsoba());
				 korisniciServis.dodajKorisnika(k.getRezervacija().getKorisnik());
				 servis1.obrisiKartu(k);
				 session.setAttribute("otkazivanjePoruka", "Uspesno ste otkazali poziv na predstavu/projekciju.");
			 }else {
				 session.setAttribute("otkazivanjePoruka", "Kartu mozete otkazati minimalno 30 minuta pre predstave/projekcije.");
			 }
		} else {
			 session.setAttribute("otkazivanjePoruka", "Karta nije pronadjena.");
		}
		return "../otkazivanjePutemLinka";
		
	}
}
