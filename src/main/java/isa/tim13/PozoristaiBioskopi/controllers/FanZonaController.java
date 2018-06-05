package isa.tim13.PozoristaiBioskopi.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import isa.tim13.PozoristaiBioskopi.dto.ObjavaDTO;
import isa.tim13.PozoristaiBioskopi.dto.PonudaDTO;
import isa.tim13.PozoristaiBioskopi.dto.RekvizitDTO;
import isa.tim13.PozoristaiBioskopi.dto.TematskiRekvizitiDTO;
import isa.tim13.PozoristaiBioskopi.exceptions.DatumIstekaNevalidan;
import isa.tim13.PozoristaiBioskopi.exceptions.NeovlascenPristupException;
import isa.tim13.PozoristaiBioskopi.exceptions.ObjavaNePostoji;
import isa.tim13.PozoristaiBioskopi.exceptions.ObjavaNijeNeobjavljena;
import isa.tim13.PozoristaiBioskopi.exceptions.RekvizitNePostoji;
import isa.tim13.PozoristaiBioskopi.exceptions.RekvizitVecPostojiException;
import isa.tim13.PozoristaiBioskopi.model.FanZonaAdministrator;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.Osoba;
import isa.tim13.PozoristaiBioskopi.model.StatusObjave;
import isa.tim13.PozoristaiBioskopi.model.TipAdministratora;
import isa.tim13.PozoristaiBioskopi.service.AuthService;
import isa.tim13.PozoristaiBioskopi.service.FanZonaService;

@Controller
@RequestMapping("/fanzona")
public class FanZonaController {
	
	@Autowired
	FanZonaService servis;
	
	private static ObjectMapper objMapper;
	
	static {
		objMapper = new ObjectMapper();
	}
	
	@RequestMapping(value="/pribaviObjavu",method=RequestMethod.GET)
	public ResponseEntity<String> pribaviObjavu(HttpSession s,@RequestParam("id") int id){
		try {
			Korisnik kor = AuthService.korisnikProvera(s);
			String retVal = servis.pribaviObjavu(kor,objMapper,id);
			return new ResponseEntity<String>(retVal,HttpStatus.OK);
		} catch (NeovlascenPristupException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Doslo je do greske prilikom baratanja s JSONOM",HttpStatus.BAD_REQUEST);
		} catch (ObjavaNePostoji e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/dodajPonudu",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> dodajPonuduNaObjavu(HttpSession s,@RequestBody PonudaDTO ponuda){
		try {
			Korisnik kor = AuthService.korisnikProvera(s);
			String retVal = servis.dodajPonuduNaObjavu(objMapper,kor,ponuda);
			return new ResponseEntity<String>(retVal,HttpStatus.OK);
		} catch (NeovlascenPristupException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch (ObjavaNePostoji e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Doslo je do greske prilikom baratanja s JSONOM",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/dodajObjavu",method=RequestMethod.POST)
	public ResponseEntity<String> dodajObjavu(HttpSession s,@RequestParam(value="objava")String objavaJson,@RequestParam(value="file",required=false) MultipartFile file){
		try {
			Korisnik kor = AuthService.korisnikProvera(s);
			ObjavaDTO objava = objMapper.readValue(objavaJson, ObjavaDTO.class);
			servis.dodajObjavu(kor,objava,file);
			return new ResponseEntity<String>("Objava uspesno dodata",HttpStatus.OK);
		} catch (NeovlascenPristupException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.FORBIDDEN);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch (DatumIstekaNevalidan e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/evaluirajObjavu",method=RequestMethod.PUT)
	public ResponseEntity<String> evaluirajObjavu(HttpSession s,@RequestParam(value="id")int id,@RequestParam("prihvacena")boolean prihvacena){
		try {
			FanZonaAdministrator admin = (FanZonaAdministrator)AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
			servis.evaluirajObjavu(admin,id,prihvacena);
			return new ResponseEntity<String>("Objava evaluirana.",HttpStatus.OK);
		} catch (NeovlascenPristupException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.FORBIDDEN);
		} catch(Exception e) {
			return new ResponseEntity<String>("Doslo je do greske. Objava verovatno vec evaluirana!",HttpStatus.BAD_REQUEST);
	}
		
	}
	
	
	@RequestMapping(value="/prikaziTematskeRekvizite",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TematskiRekvizitiDTO prikaziSveTematskeRekvizite(HttpSession s){
		try {
			Osoba o = AuthService.osobaProvera(s,FanZonaAdministrator.class,Korisnik.class);
			return servis.prikaziSveTematskeRekvizite(o instanceof FanZonaAdministrator);
		} catch (NeovlascenPristupException e) {
			return new TematskiRekvizitiDTO();
		}
		
	}
	
	@RequestMapping(value="/prikaziObjave",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Iterable<ObjavaDTO> prikaziObjave(HttpSession s,@RequestParam(value="status")StatusObjave status){
		//samo objavljene oglase mogu videti drugi osim administratora fan zone
		if(status!=StatusObjave.OBJAVLJEN) {
			try {
				AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
			} catch (NeovlascenPristupException e) {
				return new ArrayList<ObjavaDTO>();
			}
		}
		return servis.prikaziObjave(status);
	}
	
	@RequestMapping(value="/prikaziRazmatraneObjave",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Iterable<ObjavaDTO> prikaziRazmatraneObjave(HttpSession s){
		try {
			FanZonaAdministrator admin = (FanZonaAdministrator)AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
			return servis.prikaziRazmatraneObjave(admin);
		} catch (NeovlascenPristupException e) {
			return new ArrayList<ObjavaDTO>();
		}
	}
	
	
	
	@RequestMapping(value="/preuzmiObjavu",method=RequestMethod.PUT)
	public ResponseEntity<String> preuzmiObjavuNaUvid(HttpSession s,@RequestParam(value="id")int id){
		try {
			FanZonaAdministrator admin = (FanZonaAdministrator)AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
			servis.preuzmiObjavu(admin,id);
			return new ResponseEntity<String>("Objava preuzeta na uvid!",HttpStatus.OK);
		} catch (NeovlascenPristupException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.FORBIDDEN);
		} catch (ObjavaNePostoji e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch (ObjavaNijeNeobjavljena e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch(Exception e) {
			return new ResponseEntity<String>("Objava vec preuzeta na uvid, ili obrisana!",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@RequestMapping(value = "/obrisiTematskiRekvizit", method=RequestMethod.PUT)
	public ResponseEntity<String> brisanjeTematskogRekvizita(HttpSession s,@RequestParam(value="id")int id){
		try {
			AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
			servis.brisanjeTematskogRekvizita(id);
			return new ResponseEntity<String>("Rekvizit uspesno obrisan",HttpStatus.OK);
		} catch (NeovlascenPristupException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.FORBIDDEN);
		} catch (RekvizitNePostoji e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/pretraziTematskeRekvizite",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TematskiRekvizitiDTO pretraziTematskeRekvizite(HttpSession s,@RequestParam(value="nazivRekvizita") String nazivRekvizita,@RequestParam(value="gornjaCena", defaultValue = ""+Double.MAX_VALUE)double gornjaCena){
		Osoba o;
		try {
			o = AuthService.osobaProvera(s, FanZonaAdministrator.class,Korisnik.class);
			return servis.pretraziTematskeRekvizite(nazivRekvizita,gornjaCena, o instanceof FanZonaAdministrator);
		} catch (NeovlascenPristupException e) {
			return new TematskiRekvizitiDTO();
		}
		
	}
	
	
	@RequestMapping(value = "/modifikujSlikuTematskogRekvizita", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> modifikujSlikuTematskogRekvizita(HttpSession s,@RequestParam(value= "id") String id,@RequestParam(value = "file") MultipartFile file) {
		try {
			AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
			String novaPutanja = servis.modifikujSlikuRekvizita(Integer.parseInt(id), file);
			return new ResponseEntity<String>(novaPutanja,HttpStatus.OK);
		} catch (NeovlascenPristupException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.FORBIDDEN);
		} catch (RekvizitNePostoji e) {
			return  new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Doslo je do greske prilikom baratanja s fajlom ili JSONOM",HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
	
	@RequestMapping(value="/modifikujTematskiRekvizitInformacije",method=RequestMethod.PUT)
	public ResponseEntity<String> modifikujTematskiRekvizitInformacije(HttpSession s,@RequestParam("id") int id,@RequestParam("rekvizit") String rekvizitJson) {
		try {
			AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
			RekvizitDTO rekvizit = objMapper.readValue(rekvizitJson,RekvizitDTO.class);
			servis.modifikujRekvizit(id,rekvizit);
		} catch (NeovlascenPristupException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
		catch(IOException e) {
			return new ResponseEntity<String>("Doslo je do greske prilikom baratanja s fajlom ili JSONOM",HttpStatus.BAD_REQUEST);
		} 
		catch (RekvizitNePostoji e) {
			return  new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		} 
		catch (RekvizitVecPostojiException e) {
			return  new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Rekvizit uspesno modifikovan",HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/dodajTematskiRekvizit", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> dodajTematskiRekvizit(HttpSession s,@RequestParam("rekvizit") String rekvizitJson,@RequestParam("file") MultipartFile file) {
		try {
			
			AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
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
