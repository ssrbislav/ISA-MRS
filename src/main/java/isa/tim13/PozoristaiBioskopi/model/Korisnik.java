package isa.tim13.PozoristaiBioskopi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Korisnik extends Osoba implements Serializable {

	private static final long serialVersionUID = 431530785022847233L;

	@Column(name = "brojBodova")
	Double brojBodova;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
	List<Korisnik> prijatelji;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
	List<Korisnik> zahtevi;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	List<PredstavaProjekcija> istorijatPoseta;

	public Double getBrojBodova() {
		return brojBodova;
	}

	public List<Korisnik> getPrijatelji() {
		return prijatelji;
	}

	public List<Korisnik> getZahtevi() {
		return zahtevi;
	}

	public List<PredstavaProjekcija> getIstorijatPoseta() {
		return istorijatPoseta;
	}

	public void setBrojBodova(Double brojBodova) {
		this.brojBodova = brojBodova;
	}

	public void setPrijatelji(List<Korisnik> prijatelji) {
		this.prijatelji = prijatelji;
	}

	public void setZahtevi(List<Korisnik> zahtevi) {
		this.zahtevi = zahtevi;
	}

	public void setIstorijatPoseta(List<PredstavaProjekcija> istorijatPoseta) {
		this.istorijatPoseta = istorijatPoseta;
	}

	public Korisnik() {
	}
	
	
	
}
