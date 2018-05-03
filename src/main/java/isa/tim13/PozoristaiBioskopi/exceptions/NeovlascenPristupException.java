package isa.tim13.PozoristaiBioskopi.exceptions;

public class NeovlascenPristupException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1960514700956481758L;

	@Override
	public String getMessage() {
		
		return "Nemate ovlascenja da pristupite ovom sadrzaju";
	}
	
	

}
