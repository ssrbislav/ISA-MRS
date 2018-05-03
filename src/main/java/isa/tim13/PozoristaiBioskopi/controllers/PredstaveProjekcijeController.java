package isa.tim13.PozoristaiBioskopi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import isa.tim13.PozoristaiBioskopi.dto.SaleDTO;
import isa.tim13.PozoristaiBioskopi.model.PredstavaProjekcija;
import isa.tim13.PozoristaiBioskopi.service.PredstaveProjekcijeService;

@Controller
@RequestMapping("/predstaveprojekcije")
public class PredstaveProjekcijeController {

	@Autowired
	PredstaveProjekcijeService servis;
	
	@RequestMapping(value = "/dodaj", method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PredstavaProjekcija> dodajPredstavu(@RequestBody PredstavaProjekcija k) {
		servis.dodajPredstavuProjekciju(k);
		return new ResponseEntity<PredstavaProjekcija>(k,HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/sale", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SaleDTO> izlistaj_sale(@RequestBody SaleDTO s) {
		return new ResponseEntity<SaleDTO>(s,HttpStatus.CREATED);
	}
}
