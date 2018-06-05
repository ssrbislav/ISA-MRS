package isa.tim13.PozoristaiBioskopi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Rekvizit sa trazenim id-om ne postoji!")
public class RekvizitNePostoji extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -228628769653450415L;

	@Override
	public String getMessage() {
		return "Rekvizit sa trazenim id-om ne postoji!";
	}
	
	
	

}
