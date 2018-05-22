package isa.tim13.PozoristaiBioskopi.model;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.util.Pair;

@Entity
public class Rezervacija {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", updatable=false, nullable=false)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
	private Termin termin;
	
	@Column(name="cena")
	private double cena;
	
	@Column(name="mesta")
	private ArrayList<Pair<Integer,Integer>> mesta;
	
	
	public ArrayList<Pair<Integer,Integer>> getMesta() {
		return mesta;
	}

	public void setMesta(ArrayList<Pair<Integer,Integer>> mesta) {
		this.mesta = mesta;
	}
	public Termin getTermin() {
		return termin;
	}

	public void setTermin(Termin termin) {
		this.termin = termin;
	}
	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
	
	public Rezervacija() {}

}
