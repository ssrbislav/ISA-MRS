package isa.tim13.PozoristaiBioskopi.exceptions;

public class RekvizitVecPostojiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7133640122754715719L;

	@Override
	public String getMessage() {
		return "Rekvizit sa istim nazivom vec postoji!";
	}
	
	

}
