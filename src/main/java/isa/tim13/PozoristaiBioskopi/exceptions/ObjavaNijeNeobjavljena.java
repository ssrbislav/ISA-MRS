package isa.tim13.PozoristaiBioskopi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Objava je ili vec objavljena, ili uzeta u razmatranje.")
public class ObjavaNijeNeobjavljena extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 670387617854802936L;

	@Override
	public String getMessage() {
		
		return "Objava je ili vec objavljena, ili uzeta u razmatranje.";
	}
	
	

}
