package isa.tim13.PozoristaiBioskopi.exceptions;

public class OsobaVecPostojiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1935656499421103954L;

	@Override
	public String getMessage() {
		return "Vec postoji osoba s ovim mejlom.";
	}
	
	

}
