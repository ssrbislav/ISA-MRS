package isa.tim13.PozoristaiBioskopi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class RezervacijaRekvizita {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", updatable=false, nullable=false)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Korisnik narucilac;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private TematskiRekvizit rekvizit;
	
	public Korisnik getNarucilac() {
		return narucilac;
	}

	public void setNarucilac(Korisnik narucilac) {
		this.narucilac = narucilac;
	}

	public TematskiRekvizit getRekvizit() {
		return rekvizit;
	}

	public void setRekvizit(TematskiRekvizit rekvizit) {
		this.rekvizit = rekvizit;
	}

	public int getId() {
		return id;
	}

	
}
