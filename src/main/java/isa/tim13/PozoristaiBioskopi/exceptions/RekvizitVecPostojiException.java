package isa.tim13.PozoristaiBioskopi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Rekvizit sa istim nazivom vec postoji!")
public class RekvizitVecPostojiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7133640122754715719L;

	@Override
	public String getMessage() {
		return "Rekvizit sa istim nazivom vec postoji!";
	}
	
	

}
