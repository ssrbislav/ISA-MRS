package isa.tim13.PozoristaiBioskopi.exceptions;

public class ObjavaNePostoji extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4362216624220091816L;

	@Override
	public String getMessage() {
		return "Objava s trazenim id-jem ne postoji";
	}
	
	

}
