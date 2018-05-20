package isa.tim13.PozoristaiBioskopi.exceptions;

public class ObjavaNijeNeobjavljena extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 670387617854802936L;

	@Override
	public String getMessage() {
		
		return "Objava je ili vec objavljena, ili uzeta u razmatranje.";
	}
	
	

}
