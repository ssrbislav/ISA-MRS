package isa.tim13.PozoristaiBioskopi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Objava je istekla.")
public class ObjavaIstekla extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1140175911040002295L;

	@Override
	public String getMessage() {
		return "Objava je istekla.";
	}
	
	

}
