package isa.tim13.PozoristaiBioskopi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Svi rekviziti ovog tipa su rezervisani.")
public class NemaViseRekvizita extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7812895653083231981L;
	
	private static String poruka = "Svi rekviziti ovog tipa su rezervisani.";

	@Override
	public String getMessage() {
		return poruka;
	}
	
	

}
