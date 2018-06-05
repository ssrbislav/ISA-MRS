package isa.tim13.PozoristaiBioskopi.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.fasterxml.jackson.databind.ObjectMapper;

import isa.tim13.PozoristaiBioskopi.dto.InstitucijaDTO;
import isa.tim13.PozoristaiBioskopi.exceptions.NeovlascenPristupException;
import isa.tim13.PozoristaiBioskopi.model.InstitucijaKulture;
import isa.tim13.PozoristaiBioskopi.model.TipAdministratora;
import isa.tim13.PozoristaiBioskopi.service.AuthService;
import isa.tim13.PozoristaiBioskopi.service.PozoristaIBioskopiService;

@Controller
@RequestMapping("/pozoristaibioskopi")
public class PozoristaIBioskopiController {
	
	@Autowired
	PozoristaIBioskopiService servis;
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<InstitucijaKulture> prikaziInstitucije() {
		return servis.prikaziInstitucije();
	}
	
	@RequestMapping(value = "/registruj", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> registrujInstituciju(@RequestParam(value="institucija") String kJson,@RequestParam(value="file",required=false) MultipartFile file,HttpSession s) throws NeovlascenPristupException {
		InstitucijaDTO k;
		try {
			AuthService.adminProvera(s, TipAdministratora.SISTEMSKI);
			ObjectMapper objMapper = new ObjectMapper();
			k = objMapper.readValue(kJson, InstitucijaDTO.class);
			servis.dodajInstitucijuKulture(k,file);
			return new ResponseEntity<String>("Uspesno registrovana nova institucija!",HttpStatus.CREATED);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Doslo je do greske prilikom baratanja s fajlom ili JSONOM.",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}
