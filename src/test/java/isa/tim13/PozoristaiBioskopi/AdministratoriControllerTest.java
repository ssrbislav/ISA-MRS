package isa.tim13.PozoristaiBioskopi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.aspectj.lang.annotation.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

import isa.tim13.PozoristaiBioskopi.dto.AdministratorDTO;
import isa.tim13.PozoristaiBioskopi.model.Administrator;
import isa.tim13.PozoristaiBioskopi.model.InstitucijaKulture;
import isa.tim13.PozoristaiBioskopi.model.Osoba;
import isa.tim13.PozoristaiBioskopi.model.TipAdministratora;
import isa.tim13.PozoristaiBioskopi.model.TipInstitucijeKulture;
import isa.tim13.PozoristaiBioskopi.repository.KorisnikRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdministratoriControllerTest {
	
	private static final String URL_PREFIX = "/administratori";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    MockHttpSession session;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private KorisnikRepository repozitorijum;
	

	@PostConstruct
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		Administrator a = new Administrator();
		a.setAktivan(true);
		a.setIme("Test");
		a.setPrezime("Testic");
		a.setEmail("majic@majic.com");
		a.setTip(TipAdministratora.SISTEMSKI);
		session.setAttribute("korisnik", a);
		
	}
	
	@After(value = "")
	public void after() {
		ArrayList<Osoba> osobeZaBrisanje = new ArrayList<Osoba>();
		osobeZaBrisanje.add(repozitorijum.findByEmail("test@test.com"));
		osobeZaBrisanje.add(repozitorijum.findByEmail("test@test2.com"));
		osobeZaBrisanje.add(repozitorijum.findByEmail("test@test3.com"));
		osobeZaBrisanje.add(repozitorijum.findByEmail("test@test4.com"));
		
		for(Osoba o : osobeZaBrisanje) {
			if(o !=null) {
				repozitorijum.delete(o);
			}
		}
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void dodavanjeAdministratoraTest1() throws JsonProcessingException, Exception {
		AdministratorDTO adDTO  = new AdministratorDTO();
		adDTO.setIme("Test");
		adDTO.setPrezime("Testic");
		adDTO.setLozinka("LOZINKA1234");
		adDTO.setEmail("test@test.com");
		adDTO.setTip(TipAdministratora.SISTEMSKI);
		mockMvc.perform(post(URL_PREFIX)
				.session(session)
				.contentType(contentType)
				.content(TestUtil.toJson(adDTO))).andExpect(status().isCreated());
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void dodavanjeAdministratoraTest2() throws JsonProcessingException, Exception {
		AdministratorDTO adDTO  = new AdministratorDTO();
		adDTO.setIme("Test");
		adDTO.setPrezime("Testic");
		adDTO.setLozinka("LOZINKA1234");
		adDTO.setEmail("test@test2.com");
		adDTO.setTip(TipAdministratora.SISTEMSKI);
		mockMvc.perform(post(URL_PREFIX)
				.session(session)
				.contentType(contentType)
				.content(TestUtil.toJson(adDTO)));
		
		//mejl vec postoji pa se ocekuje bad request
		adDTO.setIme("Test2");
		adDTO.setTip(TipAdministratora.FAN_ZONA);
		
		mockMvc.perform(post(URL_PREFIX)
				.session(session)
				.contentType(contentType)
				.content(TestUtil.toJson(adDTO))).andExpect(status().isBadRequest());
		
	}
	
	

	@Test
	@Transactional
	@Rollback(true)
	public void dodavanjeAdministratoraTest3() throws JsonProcessingException, Exception {
		AdministratorDTO adDTO  = new AdministratorDTO();
		adDTO.setIme("Test");
		adDTO.setPrezime("Testic");
		adDTO.setLozinka("LOZINKA1234");
		adDTO.setEmail("test@test3.com");
		adDTO.setIdInstitucije(5); //institucija ne postoji, zato ocekujemo bad request
		adDTO.setTip(TipAdministratora.INSTITUCIONALNI);
		mockMvc.perform(post(URL_PREFIX)
				.session(session)
				.contentType(contentType)
				.content(TestUtil.toJson(adDTO))).andExpect(status().isBadRequest());
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void dodavanjeAdministratoraTest4() throws JsonProcessingException, Exception {
		
		//prvo kreiramo instituciju
		
		InstitucijaKulture i = new InstitucijaKulture();
		i.setNaziv("Bioskop 1");
		i.setAdresa("Adresa 1");
		i.setOpis("Opis 1");
		i.setTelefon("0654545877");
		i.setTip(TipInstitucijeKulture.BIOSKOP);
		
		mockMvc.perform(post("/pozoristaibioskopi"+"/registruj")
		.contentType(contentType)
		.session(session)
		.content(TestUtil.toJson(i)));
		
		
		
		AdministratorDTO adDTO  = new AdministratorDTO();
		adDTO.setIme("Test");
		adDTO.setPrezime("Testic");
		adDTO.setLozinka("LOZINKA1235");
		adDTO.setEmail("test@test4.com");
		
		adDTO.setIdInstitucije(1);
		adDTO.setTip(TipAdministratora.INSTITUCIONALNI);
		mockMvc.perform(post(URL_PREFIX)
				.session(session)
				.contentType(contentType)
				.content(TestUtil.toJson(adDTO))).andExpect(status().isCreated());
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void prikazAdminaTest() throws Exception {
		mockMvc.perform(get(URL_PREFIX).session(session)).andExpect(status().isOk());
	}
	
	
}
