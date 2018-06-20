package isa.tim13.PozoristaiBioskopi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Rezervacija {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", updatable=false, nullable=false)
	private int id;
	
	@OneToOne(cascade = CascadeType.DETACH, orphanRemoval = false, fetch=FetchType.LAZY)
	private Termin termin;
	
	@Column(name="ukupnaCena")
	private double ukupnaCena;

	
	@OneToOne(cascade = CascadeType.DETACH, orphanRemoval = false, fetch=FetchType.LAZY)
	private Korisnik korisnik;

	@Column(name="bodovi")
	private int bodovi;
	
	@OneToMany(cascade = CascadeType.ALL,  fetch=FetchType.LAZY, mappedBy="rezervacija")
	private List<Karta> karte;

	public int getId() {
		return id;
	}

	public Termin getTermin() {
		return termin;
	}

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public int getBodovi() {
		return bodovi;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik k) {
		
		this.korisnik=k;
	}
	
	public List<Karta> getKarte() {
		return karte;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTermin(Termin termin) {
		this.termin = termin;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}

	public void setKarte(List<Karta> karte) {
		this.karte = karte;
	}

	public Rezervacija() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
