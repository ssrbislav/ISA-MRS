package isa.tim13.PozoristaiBioskopi.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjekcijaPredstavaDTO {

	@JsonProperty(value = "naziv")
	private String naziv;
	
	@JsonProperty(value = "spisak_glumaca")
	private String spisak_glumaca;
	
	@JsonProperty(value = "reditelj")
	private String reditelj;
	
	@JsonProperty(value = "zanr")
	private String zanr;
	
	@JsonProperty(value = "trajanje")
	private double trajanje;
	
	@JsonProperty(value = "naziv_slike")
	private String naziv_slike;
	
	@JsonProperty(value = "opis")
	private String opis;
	
	@JsonProperty(value = "cena_karte")
	private double cena_karte;
	
	@JsonProperty(value = "lista_sala")
	List<SaleDTO> lista_sala = new ArrayList<SaleDTO>();
	
	@JsonProperty(value = "termin")
	List<String> lista_termina = new ArrayList<String>();
	
	
}
