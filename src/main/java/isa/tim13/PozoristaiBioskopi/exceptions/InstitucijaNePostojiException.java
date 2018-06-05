package isa.tim13.PozoristaiBioskopi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Institucija za registrovanog administratora ne postoji.")
public class InstitucijaNePostojiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6209631281333217143L;

	@Override
	public String getMessage() {
		return "Institucija za registrovanog administratora ne postoji.";
	}

}
