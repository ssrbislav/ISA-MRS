package isa.tim13.PozoristaiBioskopi.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.LockModeType;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
import isa.tim13.PozoristaiBioskopi.service.EmailService;
import isa.tim13.PozoristaiBioskopi.service.KartaService;
import isa.tim13.PozoristaiBioskopi.service.KorisniciService;
import isa.tim13.PozoristaiBioskopi.service.PredstaveProjekcijeService;
import isa.tim13.PozoristaiBioskopi.service.RezervacijaService;
import isa.tim13.PozoristaiBioskopi.service.TerminiService;

@Controller
@RequestMapping("/rezervacija")

public class RezervacijaController {

	@Autowired
	PredstaveProjekcijeService predstaveProjekcijeServis;
	@Autowired
	TerminiService terminiServis;
	@Autowired
	KorisniciService korisniciServis;
	@Autowired
	KartaService karteServis;
	@Autowired
	RezervacijaService rezervacijaServis;
	
	@Autowired
	private EmailService emailThread;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@RequestMapping(method = RequestMethod.POST, path = "/termin")
	public String repertoar(HttpSession sesion, @RequestParam("id") int ID) {

		PredstavaProjekcija predstava = predstaveProjekcijeServis.findById(ID).get();

		ArrayList<LocalDate> datumiPrikazivanja = new ArrayList<LocalDate>();
		ArrayList<Termin> prosliTermini = new ArrayList<Termin>();


//		for (Termin i : predstava.getTermini()) {
//			// pravljenje matrices svih slobodnih mesta, nemamo studenta koji
//			// radi unos termina i inicijalizaciju sale
//			int redovi = i.getSala().getBrojVrsta();
//			int kolone = i.getSala().getBrojKolona();
//			boolean[][] sala = new boolean[redovi][kolone];
//			i.setMesta(sala);
//		}
//		
//		for(int i=0; i<predstava.getTermini().size();i++) {
//			servis1.dodajTermin(predstava.getTermini().get(i));
//		}
		
		
		for (Termin i : predstava.getTermini()) {
			LocalDateTime termin = LocalDateTime.of(i.getDatum(), i.getVreme());

			if (termin.compareTo(LocalDateTime.now()) < 0) {
				prosliTermini.add(i);
			} else {
				if (!datumiPrikazivanja.contains(i.getDatum())) {

					datumiPrikazivanja.add(i.getDatum());
				}
			}

		}
		// uklanjanje termina koji su prosli
		for (Termin i : prosliTermini) {
			predstava.getTermini().remove(i);
		}
		sesion.setAttribute("predstavaZaRezervaciju", predstava);
		sesion.setAttribute("datumiPrikazivanja", datumiPrikazivanja);

		return "redirect:/rezervacija";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/mesta")
	public String odabirMesta(HttpSession session, @RequestParam("id") int ID) {

		Termin zakazanTermin = terminiServis.findById(ID).get();

		Rezervacija r = new Rezervacija();
		r.setTermin(zakazanTermin);

		session.setAttribute("rezervacija", r);
		return "../odabirMesta";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/zakazivanje")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	@Lock(value = LockModeType.PESSIMISTIC_WRITE)
	public String rezervacijaMesta(HttpSession sesion, @RequestParam("odabranaMesta") List<String> mesta){
		Rezervacija r = (Rezervacija) sesion.getAttribute("rezervacija");
		Termin t = r.getTermin();
		Korisnik ulogovan = (Korisnik) korisniciServis.pronadjiKorisnikaPoEmailu(((Osoba)sesion.getAttribute("korisnik")).getEmail());
		r.setKorisnik(ulogovan);
		ArrayList<Karta> karte = new ArrayList<>();
		String potvrdaZaMail = "Uspesno ste izvrsili rezervaciju:\n\n" + "Bioskop/Pozoriste: "
				+ r.getTermin().getpredProj().getInstitucija().getNaziv() + "\nPredstava/Projekcija: "
				+ r.getTermin().getpredProj().getNaziv() + "\nTermin: " + r.getTermin().getDatum() + ", "
				+ r.getTermin().getVreme() + "\nPrijatelji:";

		for (String mesto : mesta) {
			Karta k = new Karta();
			k.setLinkZaOtkazivanje(UUID.randomUUID().toString());
			k.setRezervacija(r);
			karte.add(k);
			String[] sediste_korisnik = mesto.split("-");

			String email = sediste_korisnik.length > 1 ? sediste_korisnik[1] : "ulogovan";
			String[] kordinate = sediste_korisnik[0].split("_");
			int x = Integer.parseInt(kordinate[0]) - 1;
			int y = Integer.parseInt(kordinate[1]) - 1;
			if(t.getMesta()[x][y] == false) {
				t.getMesta()[x][y] = true;
			}else {
				sesion.setAttribute("PorukaRezervacija", "Mesto zauzeto u medjuvremenu. Pokusajte ponovo.");
				return "redirect:/rezervacija";
			}

			int[] koordinateMesta = { x, y + 1 };
			k.setSediste(koordinateMesta);
			if (email.equals("ulogovan")) {
				k.setOsoba(ulogovan);
				ulogovan.getKarte().add(k);

			} else {
				Korisnik prijatelj = null;
				for (Osoba i : ulogovan.getPrijatelji()) {
					if (i.getEmail().equals(email)) {
						prijatelj = (Korisnik) i;
						
						mailPrijateljima(i.getEmail(), k.getLinkZaOtkazivanje(),
								r.getKorisnik().getIme() + " " + r.getKorisnik().getPrezime(),
								r.getTermin().getpredProj(), r.getTermin());
						potvrdaZaMail += "\n" + i.getIme() + " " + i.getPrezime();
						break;
					}
				}
				k.setOsoba(prijatelj);
				prijatelj.getKarte().add(k);
			}

		}
		ulogovan.getRezervacije().add(r);
		r.setBodovi(mesta.size() * 5);
		r.setUkupnaCena(r.getTermin().getCena() * mesta.size());
		r.setKarte(karte);
		ulogovan.setBrojBodova(ulogovan.getBrojBodova() + mesta.size() * 5);

		korisniciServis.dodajKorisnika(ulogovan);
		terminiServis.dodajTermin(t);
		mailPotvrdaRezervacije(ulogovan.getEmail(), potvrdaZaMail);
		sesion.setAttribute("korisnik", (Korisnik)korisniciServis.pronadjiKorisnikaPoEmailu(ulogovan.getEmail()));
		return "../repertoar";
	}

	private void mailPrijateljima(String mailTo, String registracioniLink, String prijatelj,
			PredstavaProjekcija predstava, Termin termin) {
		String naslov = "Poziv prijatelja na predstavu/projekciju";
		String sadrzaj = prijatelj + " Vas je pozvao/la na projekciju/predstavu.\n\nBioskop/Pozoriste: "
				+ predstava.getInstitucija().getNaziv() + "\nProjekcija/Predstava: " + predstava.getNaziv()
				+ "\nTermin: " + termin.getDatum() + ", " + termin.getVreme()
				+ "\n\nUkoliko zelite da otkazete poziv posetite sledeci link:\nhttp://localhost:8080/otkazivanje/"
				+ registracioniLink;
		emailThread.setup(mailTo, naslov, sadrzaj);
		taskExecutor.execute(emailThread);
	}

	private void mailPotvrdaRezervacije(String mailTo, String poruka) {
		String naslov = "Rezervacija";
		emailThread.setup(mailTo, naslov, poruka);
		taskExecutor.execute(emailThread);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/matrica")
	@ResponseBody
	public boolean[][] matricaTermina(HttpSession session) {
		Rezervacija r = (Rezervacija) session.getAttribute("rezervacija");
		r.setTermin(terminiServis.findById(r.getTermin().getId()).get());
		return r.getTermin().getMesta();
	}
}
