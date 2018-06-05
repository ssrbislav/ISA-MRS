package isa.tim13.PozoristaiBioskopi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "Nemate ovlascenja da pristupite ovom sadrzaju")
public class NeovlascenPristupException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1960514700956481758L;

	@Override
	public String getMessage() {
		
		return "Nemate ovlascenja da pristupite ovom sadrzaju";
	}
	
	

}
