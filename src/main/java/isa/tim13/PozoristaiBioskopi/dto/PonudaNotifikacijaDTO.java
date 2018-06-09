package isa.tim13.PozoristaiBioskopi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PonudaNotifikacijaDTO {
	
	@JsonProperty
	private int id;
	@JsonProperty
	private String imePonude;
	@JsonProperty
	private String imeObjave;
	@JsonProperty
	private boolean prihvacena;
	
	@JsonProperty
	private String datum;
	
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImePonude() {
		return imePonude;
	}
	public void setImePonude(String imePonude) {
		this.imePonude = imePonude;
	}
	public String getImeObjave() {
		return imeObjave;
	}
	public void setImeObjave(String imeObjave) {
		this.imeObjave = imeObjave;
	}
	public boolean isPrihvacena() {
		return prihvacena;
	}
	public void setPrihvacena(boolean prihvacena) {
		this.prihvacena = prihvacena;
	}
}
