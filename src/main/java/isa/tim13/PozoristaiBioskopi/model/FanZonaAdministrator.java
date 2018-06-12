package isa.tim13.PozoristaiBioskopi.model;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class FanZonaAdministrator extends Administrator {
	
	@Column
	private boolean promenioSifru;
	
	public boolean isPromenioSifru() {
		return promenioSifru;
	}



	public void setPromenioSifru(boolean promenioSifru) {
		this.promenioSifru = promenioSifru;
	}



	public FanZonaAdministrator() {
		super();
	}
}
