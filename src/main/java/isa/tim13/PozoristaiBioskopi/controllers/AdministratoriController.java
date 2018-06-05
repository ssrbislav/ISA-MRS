package isa.tim13.PozoristaiBioskopi.controllers;


import java.util.LinkedHashMap;

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
	
	
	
	@RequestMapping(value="/pribaviOpcije",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody LinkedHashMap<String,String> pribaviOpcije(HttpSession s) throws NeovlascenPristupException{
		 	Administrator a = AuthService.adminProvera(s);
			return servis.pribaviOpcije(a);
	}
	
	@RequestMapping(method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registrujAdministratora(@RequestBody AdministratorDTO dto,HttpSession s) throws NeovlascenPristupException, InstitucijaNePostojiException, OsobaVecPostojiException{
		AuthService.adminProvera(s, TipAdministratora.SISTEMSKI);
		servis.registrujAdministratora(dto);
		
		return new ResponseEntity<String>("Administrator uspesno registrovan. ",HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Iterable<Administrator> prikaziAdministratore(HttpSession s) throws NeovlascenPristupException{
		AuthService.adminProvera(s, TipAdministratora.SISTEMSKI);
		return servis.prikaziAdministratore();
		
	}
	
}
