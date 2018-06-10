package isa.tim13.PozoristaiBioskopi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Objava nije objavljena.")
public class ObjavaNijeObjavljena extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 82701907238916650L;

	@Override
	public String getMessage() {
		return "Objava nije objavljena.";
	}
	
	

}
