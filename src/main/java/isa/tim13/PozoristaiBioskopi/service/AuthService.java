package isa.tim13.PozoristaiBioskopi.service;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;

import isa.tim13.PozoristaiBioskopi.exceptions.NeovlascenPristupException;
import isa.tim13.PozoristaiBioskopi.exceptions.NijePromenjenaLozinka;
import isa.tim13.PozoristaiBioskopi.model.Administrator;
import isa.tim13.PozoristaiBioskopi.model.FanZonaAdministrator;
import isa.tim13.PozoristaiBioskopi.model.InstitucionalniAdministrator;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.Osoba;
import isa.tim13.PozoristaiBioskopi.model.SistemskiAdministrator;
import isa.tim13.PozoristaiBioskopi.model.TipAdministratora;

public class AuthService {
	
	private static LinkedHashMap<TipAdministratora,Administrator> tipovi;
	
	static {
		tipovi = new LinkedHashMap<TipAdministratora,Administrator>();
		tipovi.put(TipAdministratora.FAN_ZONA, new FanZonaAdministrator());
		tipovi.put(TipAdministratora.SISTEMSKI, new SistemskiAdministrator());
		tipovi.put(TipAdministratora.INSTITUCIONALNI, new InstitucionalniAdministrator());
	}
	
	public static Korisnik korisnikProvera(HttpSession s) throws NeovlascenPristupException {
		Osoba o = (Osoba)s.getAttribute("korisnik");
		if(o instanceof Korisnik) {
			return (Korisnik)o;
		}
		throw new NeovlascenPristupException();
	}
	
	public static Administrator adminProvera(HttpSession s,TipAdministratora tip) throws NeovlascenPristupException {
		Osoba o = (Osoba)s.getAttribute("korisnik");
		if(adminPravogTipa(o,tip)) {
			return (Administrator)o;
		}
		
		throw new NeovlascenPristupException();
	}
	
	public static Osoba osobaProvera(HttpSession s,Class<?>... args) throws NeovlascenPristupException {
		Osoba o = (Osoba)s.getAttribute("korisnik");
		if(o !=null) {
			for(Class<?> cl:args) {
				if(o.getClass().equals(cl)) {
					return o;
				}
			
			}
		}
		throw new NeovlascenPristupException();
	}
	
	private static  boolean adminPravogTipa(Osoba o, TipAdministratora tip) {
		if(o==null) {
			return false;
		}
		return tipovi.get(tip).getClass().equals(o.getClass());
	}

	public static Administrator adminProvera(HttpSession s) throws NeovlascenPristupException {
		Osoba o = (Osoba)s.getAttribute("korisnik");
		if(o!=null && o instanceof Administrator) {
			return (Administrator)o;
		}
		
		throw new NeovlascenPristupException();
	}
	
	//ukoliko je administrator fan zone a nije promenio lozinku baca izuzetak
	public static void fanAdminPromenaLozinkeProvera(HttpSession s) throws NijePromenjenaLozinka {
		Osoba o = (Osoba)s.getAttribute("korisnik");
		if(o instanceof FanZonaAdministrator) {
			if(!((FanZonaAdministrator)o).isPromenioSifru()) {
				throw new NijePromenjenaLozinka();
			}
		}
		
	}
}
