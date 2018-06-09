package isa.tim13.PozoristaiBioskopi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Notifikacija s trazenim id-jem ne postoji")
public class NotifikacijaNePostoji  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3854547959097385019L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Notifikacija s trazenim id-jem ne postoji";
	}
	
	

}
