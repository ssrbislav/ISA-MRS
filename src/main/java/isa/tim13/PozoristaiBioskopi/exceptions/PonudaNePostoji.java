package isa.tim13.PozoristaiBioskopi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Ponuda sa trazenim id-om ne postoji!")
public class PonudaNePostoji extends Exception {

	@Override
	public String getMessage() {
		return "Ponuda sa trazenim id-om ne postoji!";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7079829919125559724L;

}
