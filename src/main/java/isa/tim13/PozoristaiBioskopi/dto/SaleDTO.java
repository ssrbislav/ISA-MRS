package isa.tim13.PozoristaiBioskopi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaleDTO {

	@JsonProperty(value = "oznaka_sale")
	private String oznaka_sale;
	
	@JsonProperty(value = "broj_sedista")
	private int broj_sedista;
	
}
