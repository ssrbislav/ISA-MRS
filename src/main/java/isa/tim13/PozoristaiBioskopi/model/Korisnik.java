package isa.tim13.PozoristaiBioskopi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Korisnik extends Osoba implements Serializable {

	private static final long serialVersionUID = 431530785022847233L;

	@Column(name = "brojBodova")
	int brojBodova;
	
	@ManyToMany(cascade = { CascadeType.MERGE,CascadeType.PERSIST }, fetch=FetchType.LAZY)
	List<Korisnik> prijatelji;
	
	@ManyToMany(cascade = { CascadeType.MERGE,CascadeType.PERSIST }, fetch=FetchType.LAZY)
	List<Korisnik> zahtevi;
	
	@ManyToMany(cascade = { CascadeType.MERGE,CascadeType.PERSIST }, fetch=FetchType.LAZY)
	List<PredstavaProjekcija> istorijatPoseta;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
	List<Rezervacija> rezervacije;
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	List<Karta> karte;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getBrojBodova() {
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


	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}


	public List<Karta> getKarte() {
		return karte;
	}


	public void setBrojBodova(int brojBodova) {
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


	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}


	public void setKarte(List<Karta> karte) {
		this.karte = karte;
	}


	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
}
