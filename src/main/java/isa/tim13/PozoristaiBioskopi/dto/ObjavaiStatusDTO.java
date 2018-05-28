package isa.tim13.PozoristaiBioskopi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

//pomocni dto za stranicu u kojoj se prikazuje detaljan prikaz objave sa ponudama
public class ObjavaiStatusDTO {
	
	@JsonProperty("objava")
	private ObjavaDTO objava;
	
	@JsonProperty("dodavanjePonudeVidljivo")
	private boolean dodavanjePonudeVidljivo;
	
	public ObjavaiStatusDTO() {
		
	}
	
	public ObjavaDTO getObjava() {
		return objava;
	}
	public void setObjava(ObjavaDTO objava) {
		this.objava = objava;
	}
	public boolean isDodavanjePonudeVidljivo() {
		return dodavanjePonudeVidljivo;
	}
	public void setDodavanjePonudeVidljivo(boolean dodavanjePonudeVidljivo) {
		this.dodavanjePonudeVidljivo = dodavanjePonudeVidljivo;
	}
}
