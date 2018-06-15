package isa.tim13.PozoristaiBioskopi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BodovnaSkala {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column
	private int bronzaBodovi;
	@Column
	private int srebroBodovi;
	@Column
	private int zlatoBodovi;
	
	public int getId() {
		return id;
	}
	
	public int getBronzaBodovi() {
		return bronzaBodovi;
	}
	public void setBronzaBodovi(int bronzaBodovi) {
		this.bronzaBodovi = bronzaBodovi;
	}
	public int getSrebroBodovi() {
		return srebroBodovi;
	}
	public void setSrebroBodovi(int srebroBodovi) {
		this.srebroBodovi = srebroBodovi;
	}
	public int getZlatoBodovi() {
		return zlatoBodovi;
	}
	public void setZlatoBodovi(int zlatoBodovi) {
		this.zlatoBodovi = zlatoBodovi;
	}
	
	
}
