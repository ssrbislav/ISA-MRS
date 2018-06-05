package isa.tim13.PozoristaiBioskopi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import isa.tim13.PozoristaiBioskopi.model.TematskiRekvizit;

public class TematskiRekvizitiDTO {
	
	@JsonProperty("rekviziti")
	private Iterable<TematskiRekvizit> rekviziti;
	
	@JsonProperty("adminFanZone")
	private boolean adminFanZone;

	public Iterable<TematskiRekvizit> getRekviziti() {
		return rekviziti;
	}

	public void setRekviziti(Iterable<TematskiRekvizit> rekviziti) {
		this.rekviziti = rekviziti;
	}

	public boolean isAdminFanZone() {
		return adminFanZone;
	}

	public void setAdminFanZone(boolean adminFanZone) {
		this.adminFanZone = adminFanZone;
	} 
	
	
}
