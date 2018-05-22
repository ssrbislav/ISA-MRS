package isa.tim13.PozoristaiBioskopi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import isa.tim13.PozoristaiBioskopi.model.Sala;
import isa.tim13.PozoristaiBioskopi.model.TipInstitucijeKulture;

public class InstitucijaDTO {
	
	@JsonProperty(value="tip")
	private TipInstitucijeKulture tip;
	
	@JsonProperty(value="naziv")
    private String naziv;
	
	@JsonProperty(value="adresa")
	private String adresa;
	
	@JsonProperty(value="grad")
	private String grad;
	
	@JsonProperty(value="telefon")
	private String telefon;
    
	@JsonProperty(value="opis")
	private String opis;
	
	@JsonProperty(value="sale")
    private List<Sala> sale;
	
	
	public InstitucijaDTO() {}

	public TipInstitucijeKulture getTip() {
		return tip;
	}

	public void setTip(TipInstitucijeKulture tip) {
		this.tip = tip;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Sala> getSale() {
		return sale;
	}

	public void setSale(List<Sala> sale) {
		this.sale = sale;
	}
	
	
}
