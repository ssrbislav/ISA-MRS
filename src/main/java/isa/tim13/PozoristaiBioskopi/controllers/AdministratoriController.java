package isa.tim13.PozoristaiBioskopi.controllers;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import isa.tim13.PozoristaiBioskopi.dto.AdministratorDTO;
import isa.tim13.PozoristaiBioskopi.exceptions.InstitucijaNePostojiException;
import isa.tim13.PozoristaiBioskopi.exceptions.NeovlascenPristupException;
import isa.tim13.PozoristaiBioskopi.exceptions.OsobaVecPostojiException;
import isa.tim13.PozoristaiBioskopi.model.Administrator;
import isa.tim13.PozoristaiBioskopi.model.TipAdministratora;
import isa.tim13.PozoristaiBioskopi.service.AdministratoriService;
import isa.tim13.PozoristaiBioskopi.service.AuthService;


@Controller
@RequestMapping("/administratori")
public class AdministratoriController {
	
	@Autowired
	AdministratoriService servis; 
	
	@RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registrujAdministratora(@RequestBody AdministratorDTO dto,HttpSession s){
		
		try {
			AuthService.adminProvera(s, TipAdministratora.SISTEMSKI);
			servis.registrujAdministratora(dto);
		} catch (InstitucijaNePostojiException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		catch(OsobaVecPostojiException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch (NeovlascenPristupException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<String>("Administrator uspesno registrovan. ",HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Iterable<Administrator> prikaziAdministratore(HttpSession s){
		try {
			AuthService.adminProvera(s, TipAdministratora.SISTEMSKI);
			return servis.prikaziAdministratore();
		} catch (NeovlascenPristupException e) {
			return new ArrayList<Administrator>();
		}
		
	}
	
}
