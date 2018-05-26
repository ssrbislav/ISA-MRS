package isa.tim13.PozoristaiBioskopi.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjavaDTO {
	
	@JsonProperty(value="naziv")
	private String naziv;
	
	@JsonProperty(value="opis")
	private String opis;
	
	@JsonProperty(value="datumIsteka")
	private Date datumIsteka;
	
	@JsonProperty(value="putanjaDoSlike")
	private String putanjaDoSlike;
	
	public String getPutanjaDoSlike() {
		return putanjaDoSlike;
	}
	public void setPutanjaDoSlike(String putanjaDoSlike) {
		this.putanjaDoSlike = putanjaDoSlike;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Date getDatumIsteka() {
		return datumIsteka;
	}
	public void setDatumIsteka(Date datumIsteka) {
		this.datumIsteka = datumIsteka;
	}
	
}
