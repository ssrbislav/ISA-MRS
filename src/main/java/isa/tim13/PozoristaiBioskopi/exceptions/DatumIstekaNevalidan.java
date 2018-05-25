package isa.tim13.PozoristaiBioskopi.exceptions;

public class DatumIstekaNevalidan extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1987073618389511607L;

	@Override
	public String getMessage() {
		return "Datum isteka ne moze biti pre danasnjeg datuma.";
	}
	
	

}
