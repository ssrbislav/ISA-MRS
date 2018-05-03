package isa.tim13.PozoristaiBioskopi.service;

import javax.servlet.http.HttpSession;

import isa.tim13.PozoristaiBioskopi.exceptions.NeovlascenPristupException;
import isa.tim13.PozoristaiBioskopi.model.Administrator;
import isa.tim13.PozoristaiBioskopi.model.Osoba;
import isa.tim13.PozoristaiBioskopi.model.TipAdministratora;

public class AuthService {
	
	public static void adminProvera(HttpSession s,TipAdministratora tip) throws NeovlascenPristupException {
		Osoba o = (Osoba)s.getAttribute("korisnik");
		if(o!= null && o instanceof Administrator) {
			if(((Administrator)o).getTip().equals(tip)) {
				return;
			}
		}
		
		throw new NeovlascenPristupException();
	}
}
