package isa.tim13.PozoristaiBioskopi.model;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class Termin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private PredstavaProjekcija predProj;

	@Column(name = "vreme")
	private LocalTime vreme;

	@Column(name = "datum")
	private LocalDate datum;

	@Column(name = "cena")
	private double cena;

	@OneToOne(cascade = CascadeType.MERGE, orphanRemoval = false, fetch = FetchType.LAZY)
	private Sala sala;

	@Column(name = "mesta")
	private boolean[][] mesta;

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getId() {
		return id;
	}

	public LocalTime getVreme() {
		return vreme;
	}

	public void setVreme(LocalTime vreme) {
		this.vreme = vreme;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public boolean[][] getMesta() {
		return mesta;
	}

	public void setMesta(boolean[][] mesta) {
		this.mesta = mesta;
	}

	public PredstavaProjekcija getpredProj() {
		return predProj;
	}

	public void setpredProj(PredstavaProjekcija predProj) {
		this.predProj = predProj;
	}

	public Termin() {
	}

}
