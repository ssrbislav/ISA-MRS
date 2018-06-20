package isa.tim13.PozoristaiBioskopi.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import isa.tim13.PozoristaiBioskopi.model.BodovnaSkala;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.Osoba;
import isa.tim13.PozoristaiBioskopi.model.TipClana;
import isa.tim13.PozoristaiBioskopi.repository.BodovnaSkalaRepository;
import isa.tim13.PozoristaiBioskopi.repository.KorisnikRepository;

@Service
public class KorisniciService {
	@Autowired
	KorisnikRepository rep;
	
	@Autowired
	BodovnaSkalaRepository skalaRep;
	
	private static LinkedHashMap<TipClana,String> bedzPutanje;
	
	static {
		bedzPutanje = new LinkedHashMap<TipClana,String>();
		bedzPutanje.put(TipClana.NISTA, "");
		bedzPutanje.put(TipClana.BRONZANI, "inicijalne-slike/bronza.png");
		bedzPutanje.put(TipClana.SREBRNI, "inicijalne-slike/srebro.png");
		bedzPutanje.put(TipClana.ZLATNI, "inicijalne-slike/zlato.png");
	}
	
	public void dodajKorisnika(Osoba kor) {
		rep.save(kor);
	}
	public ArrayList<Korisnik> pronadjiSveKorisnike() {
		Iterable<Osoba> osobe = rep.findAll();
		ArrayList<Korisnik> korisnici = new ArrayList<>();
		for (Osoba osoba : osobe) {
			if(osoba instanceof Korisnik) {
				korisnici.add((Korisnik)osoba);
			}
		}
		return korisnici;
	}
	public Osoba pronadjiKorisnikaPoEmailu(String email) {
		return rep.findByEmail(email);
	}
	public Optional<Osoba> pronadjiPoId(int id) {
		return rep.findById(id);
	}
	public Korisnik pronadjiNeaktivnogKorisnika(String registracioniLink) {
		return rep.findByRegistracioniLink(registracioniLink);
	}

	public String dobaviBedz(Korisnik kor) {
		TipClana tip = dobaviTipClana(kor);
		return bedzPutanje.get(tip);
	}

	public TipClana dobaviTipClana(Korisnik kor) {
		BodovnaSkala skala = skalaRep.dobaviSkalu();
		if(skala==null) { return TipClana.NISTA;}
		if(kor.getBrojBodova() < skala.getBronzaBodovi()) {
			return TipClana.NISTA;
		}
		else if(kor.getBrojBodova() < skala.getSrebroBodovi()) {
			return TipClana.BRONZANI;
		}
		else if(kor.getBrojBodova() < skala.getZlatoBodovi()) {
			return TipClana.SREBRNI;
		}
		else {
			return TipClana.ZLATNI;
		}
	}

}
