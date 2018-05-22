package isa.tim13.PozoristaiBioskopi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Termin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", updatable=false, nullable=false)
	private int id;
	
	@Column(name="vreme")
	private String vreme;
	
	@Column(name="datum")
	private String datum;
	
	@Column(name="cena")
	private double cena;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
	private Sala sala;
	
	@Column(name="mesta")
	private boolean [][] mesta;
	
	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
	public int getId() {
		return id;
	}

	public String getVreme() {
		return vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}
	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	public boolean [][] getMesta() {
		return mesta;
	}

	public void setMesta(boolean [][] mesta) {
		this.mesta = mesta;
	}
	
	public Termin() {}
		
}
