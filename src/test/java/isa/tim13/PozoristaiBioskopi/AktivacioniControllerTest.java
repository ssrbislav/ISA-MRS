package isa.tim13.PozoristaiBioskopi;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.PredstavaProjekcija;
import isa.tim13.PozoristaiBioskopi.repository.KorisnikRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AktivacioniControllerTest {

	private static final String URL_PREFIX = "/aktivacija";
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private KorisnikRepository repozitorijum;
	
	private Korisnik korisnik = noviKorisnik();
	@PostConstruct
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Before
	public void before() {
		repozitorijum.save(korisnik);
	}
	
	@After
	public void after() {
		Korisnik zaBrisanje = repozitorijum.findByEmail(korisnik.getEmail());
        if(zaBrisanje != null) {
        	repozitorijum.delete(zaBrisanje);
        	zaBrisanje = null;
        }
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void registracioniTest() throws JsonProcessingException, Exception {
		mockMvc.perform(get(URL_PREFIX+"/test")).andExpect(status().isMovedTemporarily());
		assertTrue(repozitorijum.findByEmail("test").getAktivan());
	}
	
	private Korisnik noviKorisnik() {
		Korisnik noviKorisnik = new Korisnik();
		noviKorisnik.setEmail("test");
		noviKorisnik.setIme("test");
		noviKorisnik.setPrezime("test");
		noviKorisnik.setTelefon("test");
		noviKorisnik.setGrad("test");
		noviKorisnik.setLozinka("test");
		
		noviKorisnik.setRegistracioniLink("test");
		noviKorisnik.setAktivan(false);
		noviKorisnik.setLokacijaSlike("");
		noviKorisnik.setBrojBodova(0.0);
		noviKorisnik.setIstorijatPoseta(new ArrayList<PredstavaProjekcija>());
		noviKorisnik.setPrijatelji(new ArrayList<Korisnik>());
		noviKorisnik.setZahtevi(new ArrayList<Korisnik>());
		return noviKorisnik;
	}

}
