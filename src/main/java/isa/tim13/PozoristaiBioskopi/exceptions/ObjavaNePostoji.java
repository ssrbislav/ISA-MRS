package isa.tim13.PozoristaiBioskopi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Objava s trazenim id-jem ne postoji")
public class ObjavaNePostoji extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4362216624220091816L;

	@Override
	public String getMessage() {
		return "Objava s trazenim id-jem ne postoji";
	}
	
	

}
