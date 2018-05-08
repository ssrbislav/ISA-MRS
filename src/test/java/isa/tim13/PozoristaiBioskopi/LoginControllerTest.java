package isa.tim13.PozoristaiBioskopi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URLEncoder;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.Osoba;
import isa.tim13.PozoristaiBioskopi.model.PredstavaProjekcija;
import isa.tim13.PozoristaiBioskopi.repository.KorisnikRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	private static final String URL_PREFIX = "/login";

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
		Osoba zaBrisanje = repozitorijum.findByEmail(korisnik.getEmail());
		if (zaBrisanje != null) {
			repozitorijum.delete(zaBrisanje);
			zaBrisanje = null;
		}
	}

	@After
	public void after() {
		Osoba zaBrisanje = repozitorijum.findByEmail(korisnik.getEmail());
		if (zaBrisanje != null) {
			repozitorijum.delete(zaBrisanje);
			zaBrisanje = null;
		}
	}

	@Test
	public void loginTest() throws JsonProcessingException, Exception {
		korisnik.setAktivan(true);
		repozitorijum.save(korisnik);
		mockMvc.perform(post(URL_PREFIX).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity("email", "test", "lozinka", "test")))
				.andExpect(status().isMovedTemporarily());
	}
	
	@Test
	public void loginNeaktivanTest() throws JsonProcessingException, Exception {
		korisnik.setAktivan(false);
		repozitorijum.save(korisnik);
		mockMvc.perform(post(URL_PREFIX).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity("email", "test", "lozinka", "test")))
				.andExpect(status().isOk());
	}
	
	@Test
	public void loginLosEmailTest() throws JsonProcessingException, Exception {
		repozitorijum.save(korisnik);
		mockMvc.perform(post(URL_PREFIX).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity("email", "nepostojeci", "lozinka", "test")))
				.andExpect(status().isOk());
	}
	
	@Test
	public void loginLosaLozinkaTest() throws JsonProcessingException, Exception {
		repozitorijum.save(korisnik);
		mockMvc.perform(post(URL_PREFIX).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity("email", "test", "lozinka", "pogresna")))
				.andExpect(status().isOk());
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
		noviKorisnik.setAktivan(true);
		noviKorisnik.setLokacijaSlike("");
		noviKorisnik.setBrojBodova(0.0);
		noviKorisnik.setIstorijatPoseta(new ArrayList<PredstavaProjekcija>());
		noviKorisnik.setPrijatelji(new ArrayList<Korisnik>());
		noviKorisnik.setZahtevi(new ArrayList<Korisnik>());
		return noviKorisnik;
	}

	public String buildUrlEncodedFormEntity(String... params) {
		if ((params.length % 2) > 0) {
			throw new IllegalArgumentException("Need to give an even number of parameters");
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < params.length; i += 2) {
			if (i > 0)
				result.append('&');
			result.append(URLEncoder.encode(params[i])).append('=').append(URLEncoder.encode(params[i + 1]));
		}
		return result.toString();
	}

}
