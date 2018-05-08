package isa.tim13.PozoristaiBioskopi.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import isa.tim13.PozoristaiBioskopi.dto.RekvizitDTO;
import isa.tim13.PozoristaiBioskopi.exceptions.NeovlascenPristupException;
import isa.tim13.PozoristaiBioskopi.exceptions.RekvizitVecPostojiException;
import isa.tim13.PozoristaiBioskopi.model.TipAdministratora;
import isa.tim13.PozoristaiBioskopi.service.AuthService;
import isa.tim13.PozoristaiBioskopi.service.FanZonaService;

@Controller
@RequestMapping("/fanzona")
public class FanZonaController {
	
	@Autowired
	FanZonaService servis;
	
	@RequestMapping(value = "/dodajTematskiRekvizit", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> dodajTematskiRekvizit(HttpSession s,@RequestParam("rekvizit") String rekvizitJson,@RequestParam("file") MultipartFile file) {
		try {
			
			AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
			ObjectMapper objMapper = new ObjectMapper();
			RekvizitDTO rekvizit = objMapper.readValue(rekvizitJson,RekvizitDTO.class);
			servis.dodajTematskiRekvizit(rekvizit,file);
		
		} catch (IOException e) {
			return new ResponseEntity<String>("Doslo je do greske prilikom baratanja s fajlom ili JSONOM",HttpStatus.BAD_REQUEST);
		} catch (RekvizitVecPostojiException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch (NeovlascenPristupException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<String>("Rekvizit uspesno dodat",HttpStatus.CREATED);
	}
}
