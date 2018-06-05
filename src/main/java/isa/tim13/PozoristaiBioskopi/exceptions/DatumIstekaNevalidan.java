package isa.tim13.PozoristaiBioskopi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Datum isteka ne moze biti pre danasnjeg datuma.")
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
