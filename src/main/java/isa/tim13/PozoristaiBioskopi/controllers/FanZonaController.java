package isa.tim13.PozoristaiBioskopi.controllers;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
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
import isa.tim13.PozoristaiBioskopi.dto.PonudaNotifikacijaDTO;
import isa.tim13.PozoristaiBioskopi.dto.RekvizitDTO;
import isa.tim13.PozoristaiBioskopi.dto.TematskiRekvizitiDTO;
import isa.tim13.PozoristaiBioskopi.exceptions.DatumIstekaNevalidan;
import isa.tim13.PozoristaiBioskopi.exceptions.NemaViseRekvizita;
import isa.tim13.PozoristaiBioskopi.exceptions.NeovlascenPristupException;
import isa.tim13.PozoristaiBioskopi.exceptions.NotifikacijaNePostoji;
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
	
	@RequestMapping(value="/obrisiObavestenje",method=RequestMethod.PUT)
	public ResponseEntity<String> obrisiObavestenje(HttpSession s,@RequestParam("id") int id) throws NeovlascenPristupException, NotifikacijaNePostoji{
		AuthService.korisnikProvera(s);
		servis.obrisiObavestenje(id);
		return new ResponseEntity<String>("Uklonjeno obavestenje",HttpStatus.OK);
	}
	
	@RequestMapping(value="/pribaviObavestenja",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Iterable<PonudaNotifikacijaDTO> pribaviObavestenja(HttpSession s) throws NeovlascenPristupException{
		Korisnik kor = AuthService.korisnikProvera(s);
		return servis.pribaviObavestenja(kor);
	}
	
	@RequestMapping(value="/prihvatiPonudu",method=RequestMethod.PUT)
	public ResponseEntity<String> prihvatiPonudu(HttpSession s,@RequestParam("id") int id) throws NeovlascenPristupException{
		Korisnik kor = AuthService.korisnikProvera(s);
		servis.prihvatiPonudu(kor,id);
		return new ResponseEntity<String>("Ponuda prihvacena. Svi ucesnici ce biti obavesteni.",HttpStatus.OK);
	}
	
	@RequestMapping(value="/rezervisanjeRekvizita",method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody LinkedHashMap<String,String> rezervisanjeRekvizita(HttpSession s,@RequestParam("id") int id) throws RekvizitNePostoji, NemaViseRekvizita, NeovlascenPristupException{
		Korisnik kor = AuthService.korisnikProvera(s);
	    return servis.rezervisanjeRekvizita(kor,id);
		
	}
	
	@RequestMapping(value="/pribaviObjavu",method=RequestMethod.GET)
	public ResponseEntity<String> pribaviObjavu(HttpSession s,@RequestParam("id") int id) throws NeovlascenPristupException, ObjavaNePostoji{
		try {
			Korisnik kor = AuthService.korisnikProvera(s);
			String retVal = servis.pribaviObjavu(kor,objMapper,id);
			return new ResponseEntity<String>(retVal,HttpStatus.OK);
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Doslo je do greske prilikom baratanja s JSONOM",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/dodajPonudu",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> dodajPonuduNaObjavu(HttpSession s,@RequestBody PonudaDTO ponuda) throws NeovlascenPristupException, ObjavaNePostoji{
		try {
			Korisnik kor = AuthService.korisnikProvera(s);
			String retVal = servis.dodajPonuduNaObjavu(objMapper,kor,ponuda);
			return new ResponseEntity<String>(retVal,HttpStatus.OK);
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Doslo je do greske prilikom baratanja s JSONOM",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/dodajObjavu",method=RequestMethod.POST)
	public ResponseEntity<String> dodajObjavu(HttpSession s,@RequestParam(value="objava")String objavaJson,@RequestParam(value="file",required=false) MultipartFile file) throws NeovlascenPristupException, DatumIstekaNevalidan{
		try {
			Korisnik kor = AuthService.korisnikProvera(s);
			ObjavaDTO objava = objMapper.readValue(objavaJson, ObjavaDTO.class);
			servis.dodajObjavu(kor,objava,file);
			return new ResponseEntity<String>("Objava uspesno dodata",HttpStatus.OK);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Doslo je do greske prilikom baratanja s jsonom ili fajlom.",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/evaluirajObjavu",method=RequestMethod.PUT)
	public ResponseEntity<String> evaluirajObjavu(HttpSession s,@RequestParam(value="id")int id,@RequestParam("prihvacena")boolean prihvacena) throws NeovlascenPristupException{
		try{
			FanZonaAdministrator admin = (FanZonaAdministrator)AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
			servis.evaluirajObjavu(admin,id,prihvacena);
			return new ResponseEntity<String>("Objava evaluirana.",HttpStatus.OK);
		}
		catch (ObjectOptimisticLockingFailureException e) {
			return new ResponseEntity<String>("Objava vec evuluirana",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping(value="/prikaziTematskeRekvizite",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TematskiRekvizitiDTO prikaziSveTematskeRekvizite(HttpSession s) throws NeovlascenPristupException{
		Osoba o = AuthService.osobaProvera(s,FanZonaAdministrator.class,Korisnik.class);
		return servis.prikaziSveTematskeRekvizite(o instanceof FanZonaAdministrator);
	}
	
	@RequestMapping(value="/prikaziObjave",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Iterable<ObjavaDTO> prikaziObjave(HttpSession s,@RequestParam(value="status")StatusObjave status) throws NeovlascenPristupException{
		//samo objavljene oglase mogu videti drugi osim administratora fan zone
		if(status!=StatusObjave.OBJAVLJEN) {
			AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
		}
		return servis.prikaziObjave(status);
	}
	
	@RequestMapping(value="/prikaziRazmatraneObjave",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Iterable<ObjavaDTO> prikaziRazmatraneObjave(HttpSession s) throws NeovlascenPristupException{
		FanZonaAdministrator admin = (FanZonaAdministrator)AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
		return servis.prikaziRazmatraneObjave(admin);
	}
	
	
	
	@RequestMapping(value="/preuzmiObjavu",method=RequestMethod.PUT)
	public ResponseEntity<String> preuzmiObjavuNaUvid(HttpSession s,@RequestParam(value="id")int id) throws ObjavaNePostoji, ObjavaNijeNeobjavljena, NeovlascenPristupException{
		try {
			FanZonaAdministrator admin = (FanZonaAdministrator)AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
			servis.preuzmiObjavu(admin,id);
			return new ResponseEntity<String>("Objava preuzeta na uvid!",HttpStatus.OK);
		}
		catch(ObjectOptimisticLockingFailureException e) {
			System.out.println(e.getClass());
			return new ResponseEntity<String>("Drugi administrator je u medjuvremenu preuzeo objavu!",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@RequestMapping(value = "/obrisiTematskiRekvizit", method=RequestMethod.PUT)
	public ResponseEntity<String> brisanjeTematskogRekvizita(HttpSession s,@RequestParam(value="id")int id) throws NeovlascenPristupException, RekvizitNePostoji{
		AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
		servis.brisanjeTematskogRekvizita(id);
		return new ResponseEntity<String>("Rekvizit uspesno obrisan",HttpStatus.OK);
	}
	
	@RequestMapping(value="/pretraziTematskeRekvizite",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TematskiRekvizitiDTO pretraziTematskeRekvizite(HttpSession s,@RequestParam(value="nazivRekvizita") String nazivRekvizita,@RequestParam(value="gornjaCena", defaultValue = ""+Double.MAX_VALUE)double gornjaCena) throws NeovlascenPristupException{
		Osoba o = AuthService.osobaProvera(s, FanZonaAdministrator.class,Korisnik.class);
		return servis.pretraziTematskeRekvizite(nazivRekvizita,gornjaCena, o instanceof FanZonaAdministrator);
		
	}
	
	
	@RequestMapping(value = "/modifikujSlikuTematskogRekvizita", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> modifikujSlikuTematskogRekvizita(HttpSession s,@RequestParam(value= "id") String id,@RequestParam(value = "file") MultipartFile file) throws NeovlascenPristupException, NumberFormatException, RekvizitNePostoji {
		try {
			AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
			String novaPutanja = servis.modifikujSlikuRekvizita(Integer.parseInt(id), file);
			return new ResponseEntity<String>(novaPutanja,HttpStatus.OK);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Doslo je do greske prilikom baratanja s fajlom ili JSONOM",HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
	
	@RequestMapping(value="/modifikujTematskiRekvizitInformacije",method=RequestMethod.PUT)
	public ResponseEntity<String> modifikujTematskiRekvizitInformacije(HttpSession s,@RequestParam("id") int id,@RequestParam("rekvizit") String rekvizitJson) throws NeovlascenPristupException, RekvizitNePostoji, RekvizitVecPostojiException {
		try {
			AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
			RekvizitDTO rekvizit = objMapper.readValue(rekvizitJson,RekvizitDTO.class);
			servis.modifikujRekvizit(id,rekvizit);
			return new ResponseEntity<String>("Rekvizit uspesno modifikovan",HttpStatus.OK);
		}
		catch(IOException e) {
			return new ResponseEntity<String>("Doslo je do greske prilikom baratanja s fajlom ili JSONOM",HttpStatus.BAD_REQUEST);
		} 
		
	}
	
	
	
	@RequestMapping(value = "/dodajTematskiRekvizit", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> dodajTematskiRekvizit(HttpSession s,@RequestParam("rekvizit") String rekvizitJson,@RequestParam("file") MultipartFile file) throws NeovlascenPristupException, RekvizitVecPostojiException {
		try {
			AuthService.adminProvera(s, TipAdministratora.FAN_ZONA);
			RekvizitDTO rekvizit = objMapper.readValue(rekvizitJson,RekvizitDTO.class);
			servis.dodajTematskiRekvizit(rekvizit,file);
		} catch (IOException e) {
			return new ResponseEntity<String>("Doslo je do greske prilikom baratanja s fajlom ili JSONOM",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Rekvizit uspesno dodat",HttpStatus.CREATED);
	}
}
