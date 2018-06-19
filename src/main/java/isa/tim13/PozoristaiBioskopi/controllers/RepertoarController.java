package isa.tim13.PozoristaiBioskopi.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import isa.tim13.PozoristaiBioskopi.model.InstitucijaKulture;
import isa.tim13.PozoristaiBioskopi.service.PozoristaIBioskopiService;

@Controller
@RequestMapping("/repertoarC")
public class RepertoarController {
	@Autowired
	PozoristaIBioskopiService servis;
	
	@RequestMapping(method=RequestMethod.POST)
	public String repertoar(HttpSession sesion, @RequestParam ("id") int  ID ) {
		
			InstitucijaKulture institucija= servis.findById(ID).get();
			
			sesion.setAttribute("institucija", institucija);
			return "redirect:/repertoar";
	}
}
