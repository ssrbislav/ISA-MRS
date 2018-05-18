package isa.tim13.PozoristaiBioskopi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

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

import isa.tim13.PozoristaiBioskopi.model.Administrator;
import isa.tim13.PozoristaiBioskopi.model.InstitucijaKulture;
import isa.tim13.PozoristaiBioskopi.model.SistemskiAdministrator;
import isa.tim13.PozoristaiBioskopi.model.TipAdministratora;
import isa.tim13.PozoristaiBioskopi.model.TipInstitucijeKulture;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PozoristaIBioskopiControllerTest {
	
	private static final String URL_PREFIX = "/pozoristaibioskopi";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
    MockHttpSession session;
	

	@PostConstruct
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		Administrator a = new SistemskiAdministrator();
		a.setAktivan(true);
		a.setIme("Test");
		a.setPrezime("Testic");
		a.setEmail("majic@majic.com");
		session.setAttribute("korisnik", a);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void dodavanjeInstitucijeTest() throws JsonProcessingException, Exception {
		InstitucijaKulture i = new InstitucijaKulture();
		i.setNaziv("Bioskop 1");
		i.setAdresa("Adresa 1");
		i.setOpis("Opis 1");
		i.setTelefon("065456877");
		i.setTip(TipInstitucijeKulture.BIOSKOP);
		
		mockMvc.perform(post(URL_PREFIX+"/registruj")
		.contentType(contentType)
		.session(session)
		.content(TestUtil.toJson(i))).andExpect(status().isCreated());
	}
	
	@Test
	public void pribaviSveInstitucije() throws Exception {
		mockMvc.perform(get(URL_PREFIX).session(session)).andExpect(status().isOk());
	}
}
