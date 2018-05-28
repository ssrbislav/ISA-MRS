package isa.tim13.PozoristaiBioskopi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PonudaDTO {
	
	@JsonProperty("idPonude")
	private int idPonude;
	
	@JsonProperty("idObjave")
	private int idObjave;
	
	@JsonProperty("naslov")
	private String naslov;
	
	@JsonProperty("opis")
	private String opis;
	
	@JsonProperty("cena")
	private double cena;
	
	@JsonProperty("autor")
	private String autor;
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public PonudaDTO() {
		
	}
	
	public int getIdPonude() {
		return idPonude;
	}



	public void setIdPonude(int idPonude) {
		this.idPonude = idPonude;
	}



	public int getIdObjave() {
		return idObjave;
	}
	
	public void setIdObjave(int idObjave) {
		this.idObjave = idObjave;
	}
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	
}
