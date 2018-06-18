package isa.tim13.PozoristaiBioskopi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Karta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", updatable=false, nullable=false)
	private int id;
	
	@ManyToOne(cascade = CascadeType.DETACH,  fetch=FetchType.EAGER)
	private Rezervacija rezervacija;
	
	@Column(name="sediste")
	private int [] sediste;
	
	@OneToOne(cascade = CascadeType.DETACH,  fetch=FetchType.EAGER)
	private Korisnik osoba;
	
	@Column(name = "linkZaOtkazivanje")
	private String linkZaOtkazivanje;

	
	public int getId() {
		return id;
	}
	public Rezervacija getRezervacija() {
		return rezervacija;
	}

	public int[] getSediste() {
		return sediste;
	}

	public Korisnik getOsoba() {
		return osoba;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}

	public void setSediste(int[] sediste) {
		this.sediste = sediste;
	}

	public void setOsoba(Korisnik osoba) {
		this.osoba = osoba;
	}
	
	public String getLinkZaOtkazivanje() {
		return linkZaOtkazivanje;
	}
	
	public void setLinkZaOtkazivanje(String linkZaOtkazivanje) {
		this.linkZaOtkazivanje = linkZaOtkazivanje;
	}
	public Karta() {
		super();
		// TODO Auto-generated constructor stub
	}
}
