package isa.tim13.PozoristaiBioskopi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

import isa.tim13.PozoristaiBioskopi.dto.InstitucijaDTO;
import isa.tim13.PozoristaiBioskopi.model.Administrator;
import isa.tim13.PozoristaiBioskopi.model.Sala;
import isa.tim13.PozoristaiBioskopi.model.SistemskiAdministrator;
import isa.tim13.PozoristaiBioskopi.model.TipInstitucijeKulture;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PozoristaIBioskopiControllerTest {
	
	private static final String URL_PREFIX = "/pozoristaibioskopi";


    
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
	
	
	@After
	public void obrisiSveTestFajlove() {
		File folder = new File("institucije-slike/");
		File[] listaFajlova = folder.listFiles();

	    for (int i = 0; i < listaFajlova.length; i++) {
	      if (listaFajlova[i].isFile() && listaFajlova[i].getName().startsWith("Test")) {
	       listaFajlova[i].delete();
	      }
	    }
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void dodavanjeInstitucijeTest() throws JsonProcessingException, Exception {
		InstitucijaDTO i = new InstitucijaDTO();
		i.setNaziv("Test");
		i.setAdresa("Adresa 1");
		i.setOpis("Opis 1");
		i.setTelefon("065456877");
		i.setTip(TipInstitucijeKulture.BIOSKOP);
		i.setGrad("Neki tamo");
		i.setSale(new ArrayList<Sala>());
		i.getSale().add(new Sala());
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/images/index.png");
		MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpg", inputStream);
		
		mockMvc.perform(fileUpload(URL_PREFIX+"/registruj").file(mockFile)
		.session(session)
		.param("institucija",TestUtil.toJson(i))).andExpect(status().isCreated());
	}
	
	@Test
	public void pribaviSveInstitucije() throws Exception {
		mockMvc.perform(get(URL_PREFIX).session(session)).andExpect(status().isOk());
	}
}
