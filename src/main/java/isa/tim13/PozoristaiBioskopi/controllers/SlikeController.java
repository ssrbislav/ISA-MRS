package isa.tim13.PozoristaiBioskopi.controllers;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import isa.tim13.PozoristaiBioskopi.service.SlikeService;

@Controller
@RequestMapping("/upravljanjeSlikama")
public class SlikeController {
	
	@Autowired
	SlikeService servis;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<byte[]> dobaviSliku(@RequestParam("putanjaDoSlike")String putanjaDoSlike) throws IOException {
		byte[] slika;
		try {
			slika = servis.dobaviSliku(putanjaDoSlike);
		} catch (IOException e) {
			slika = servis.dobaviSliku("inicijalne-slike/profilna.jpg");
		}
		HttpHeaders headers = new HttpHeaders();
	    headers.setCacheControl(CacheControl.noCache().getHeaderValue());
	     
	    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(slika, headers, HttpStatus.OK);
	    return responseEntity;
	}
}
