package isa.tim13.PozoristaiBioskopi.exceptions;

public class RekvizitNePostoji extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -228628769653450415L;

	@Override
	public String getMessage() {
		return "Rekvizit sa trazenim id-om ne postoji!";
	}
	
	
	

}
