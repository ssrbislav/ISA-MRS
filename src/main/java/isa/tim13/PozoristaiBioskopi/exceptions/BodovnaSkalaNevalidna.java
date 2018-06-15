package isa.tim13.PozoristaiBioskopi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Granice bodova kod skale nisu podesene kako treba")
public class BodovnaSkalaNevalidna extends Exception {

	@Override
	public String getMessage() {
		return "Granice bodova kod skale nisu podesene kako treba";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2975202153394375996L;
	
	

}
