package isa.tim13.PozoristaiBioskopi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.tim13.PozoristaiBioskopi.dto.AdministratorDTO;
import isa.tim13.PozoristaiBioskopi.exceptions.InstitucijaNePostojiException;
import isa.tim13.PozoristaiBioskopi.exceptions.OsobaVecPostojiException;
import isa.tim13.PozoristaiBioskopi.model.Administrator;
import isa.tim13.PozoristaiBioskopi.service.AdministratoriService;


@RestController
@RequestMapping("/administratori")
public class AdministratoriController {
	
	@Autowired
	AdministratoriService servis; 
	
	@RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registrujAdministratora(@RequestBody AdministratorDTO dto){
		
		try {
			servis.registrujAdministratora(dto);
		} catch (InstitucijaNePostojiException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		catch(OsobaVecPostojiException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Administrator uspesno registrovan. ",HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Iterable<Administrator> prikaziAdministratore(){
		return servis.prikaziAdministratore();
	}
	
}
