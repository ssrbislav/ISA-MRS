package isa.tim13.PozoristaiBioskopi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Vec postoji osoba s ovim mejlom.")
public class OsobaVecPostojiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1935656499421103954L;

	@Override
	public String getMessage() {
		return "Vec postoji osoba s ovim mejlom.";
	}
	
	

}
