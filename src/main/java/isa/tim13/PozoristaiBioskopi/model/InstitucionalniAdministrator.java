package isa.tim13.PozoristaiBioskopi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class InstitucionalniAdministrator extends Administrator {
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private InstitucijaKulture inst;
	
	public InstitucijaKulture getInst() {
		return inst;
	}

	public void setInst(InstitucijaKulture inst) {
		this.inst = inst;
	}

	public InstitucionalniAdministrator() {
		super();
	}
}
