package isa.tim13.PozoristaiBioskopi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BodovnaSkalaDTO {
	
	@JsonProperty(value="bronzaBodovi")
	private int bronzaBodovi;
	
	@JsonProperty(value="srebroBodovi")
	private int srebroBodovi;
	
	@JsonProperty(value="zlatoBodovi")
	private int zlatoBodovi;

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
