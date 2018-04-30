package isa.tim13.PozoristaiBioskopi.exceptions;

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
