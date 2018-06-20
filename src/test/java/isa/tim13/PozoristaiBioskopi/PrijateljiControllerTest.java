package isa.tim13.PozoristaiBioskopi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import isa.tim13.PozoristaiBioskopi.dto.PrijateljDTO;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
import isa.tim13.PozoristaiBioskopi.model.Osoba;
import isa.tim13.PozoristaiBioskopi.repository.KorisnikRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PrijateljiControllerTest {
private static final String URL_PREFIX = "/prijatelji";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    MockHttpSession session;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private KorisnikRepository repozitorijum;
	
	@Autowired
	private ObjectMapper objMapper;
	
	
	Korisnik kor1;
	Korisnik kor2;
	Korisnik kor3;
	
	@Before
	public void before() {
		session.setAttribute("korisnik", new Korisnik());
	}
	
	@PostConstruct
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		ArrayList<Osoba> korisniciLista = new ArrayList<Osoba>();
		Iterable<Osoba> korisnici = repozitorijum.findAll();
		for (Osoba osoba : korisnici) {
			korisniciLista.add(osoba);
		}
		if(korisniciLista.size()==0) {
			kor1 = new Korisnik();
			kor1.setAktivan(true);
			kor1.setEmail("test@test1.com");
			kor1.setIme("Test");
			kor1.setPrezime("Testic");
			kor1.setGrad("Novi Sad");
			repozitorijum.save(kor1);
			
			
			kor2 = new Korisnik();
			kor2.setAktivan(true);
			kor2.setEmail("test@test2.com");
			kor2.setIme("Neko");
			kor2.setPrezime("Nekic");
			kor2.setGrad("Novi Sad");
			repozitorijum.save(kor2);
			
			
			kor3 = new Korisnik();
			kor3.setAktivan(true);
			kor3.setEmail("test@test3.com");
			kor3.setIme("Neko2");
			kor3.setPrezime("Nekic2");
			kor3.setGrad("Novi Sad");
			repozitorijum.save(kor3);
		} else {
			kor1 = (Korisnik) repozitorijum.findByEmail("test@test1.com");
			kor2 = (Korisnik) repozitorijum.findByEmail("test@test2.com");
			kor3 = (Korisnik) repozitorijum.findByEmail("test@test3.com");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@Transactional
	@Rollback(true)
	public void pretragaOsobaTest() throws Exception {
		session.setAttribute("korisnik", kor1);
		MvcResult rezultat = mockMvc.perform(post(URL_PREFIX+"/pretragaPrijatelja")
				.session(session).param("stringZaPretragu", "nek"))
				.andExpect(status().isOk()).andReturn();
		ArrayList<PrijateljDTO> lista = new ArrayList<PrijateljDTO>();
		String jsonString = rezultat.getResponse().getContentAsString();
		lista = objMapper.readValue(jsonString, objMapper.getTypeFactory().constructCollectionType(ArrayList.class, PrijateljDTO.class));
		assertNotEquals(0,lista.size());
	}
	
//	@Test
//	public void dodajIOdbijPrijateljaTest() throws Exception {
//		session.setAttribute("korisnik", kor1);
//		mockMvc.perform(post(URL_PREFIX+"/dodavanje")
//				.session(session).param("id", ""+kor2.getId()))
//				.andExpect(status().isMovedTemporarily());
//		kor2 = (Korisnik)repozitorijum.findById(kor2.getId()).get();
//		assertNotEquals(0,kor2.getZahtevi().size());
//		session.setAttribute("korisnik", kor2);
//		mockMvc.perform(post(URL_PREFIX+"/odbijZahtev")
//				.session(session).param("id", ""+kor1.getId(),"from","/profilKorisnika.jsp"))
//				.andExpect(status().isMovedTemporarily());
//		kor2 = (Korisnik)repozitorijum.findById(kor2.getId()).get();
//		assertEquals(0,kor2.getZahtevi().size());
//	}
}
