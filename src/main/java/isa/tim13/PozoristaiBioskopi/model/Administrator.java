package isa.tim13.PozoristaiBioskopi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class Administrator extends Osoba {
	
	@Column(name = "tipAdministratora")
	private TipAdministratora tipAdministratora;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private InstitucijaKulture inst; //null ako tip nije institucionalni
	
	
	public TipAdministratora getTip() {
		return tipAdministratora;
	}
	public void setTip(TipAdministratora tip) {
		this.tipAdministratora = tip;
	}
	public InstitucijaKulture getInst() {
		return inst;
	}
	public void setInst(InstitucijaKulture inst) {
		this.inst = inst;
	}
}
