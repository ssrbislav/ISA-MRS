package isa.tim13.PozoristaiBioskopi;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

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

import isa.tim13.PozoristaiBioskopi.dto.RekvizitDTO;
import isa.tim13.PozoristaiBioskopi.model.Administrator;
import isa.tim13.PozoristaiBioskopi.model.TipAdministratora;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FanZonaControllerTest {
	
	private static final String URL_PREFIX = "/fanzona";
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    MockHttpSession session;
	
	

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@PostConstruct
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		Administrator a = new Administrator();
		a.setAktivan(true);
		a.setIme("Test");
		a.setPrezime("Testic");
		a.setEmail("majic@majic.com");
		a.setTip(TipAdministratora.FAN_ZONA);
		session.setAttribute("korisnik", a);
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void dodavanjeTematskogRekvizitaTest1() throws JsonProcessingException, Exception {
		RekvizitDTO rekvizit = new RekvizitDTO();
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/images/index.png");
		rekvizit.setNazivRekvizita("Test1");
		rekvizit.setCenaRekvizita(50);
		rekvizit.setOpisRekvizita("Opis rekvizita test 1");
		MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpg", inputStream);
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajTematskiRekvizit")
                .file(mockFile)
                .session(session)
                .param("rekvizit", TestUtil.toJson(rekvizit)))
            .andExpect(status().isCreated());
	}
	
	
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void dodavanjeTematskogRekvizitaTest2() throws JsonProcessingException, Exception {
		RekvizitDTO rekvizit = new RekvizitDTO();
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/images/index.png");
		rekvizit.setNazivRekvizita("Test2");
		rekvizit.setCenaRekvizita(50);
		rekvizit.setOpisRekvizita("Opis rekvizita test 1");
		MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpg", inputStream);
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajTematskiRekvizit")
                .file(mockFile)
                .session(session)
                .param("rekvizit", TestUtil.toJson(rekvizit)));
		
		rekvizit.setCenaRekvizita(60);
		rekvizit.setOpisRekvizita("Novi opis");
		
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajTematskiRekvizit")
                .file(mockFile)
                .session(session)
                .param("rekvizit", TestUtil.toJson(rekvizit))).andExpect(status().isBadRequest());
       
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void dodavanjeTematskogRekvizitaTest3() throws JsonProcessingException, Exception {
		RekvizitDTO rekvizit = new RekvizitDTO();
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("static/images/index.png");
		rekvizit.setNazivRekvizita("Test3");
		rekvizit.setCenaRekvizita(50);
		rekvizit.setOpisRekvizita("Opis rekvizita test 1");
		MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpg", inputStream);
		
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajTematskiRekvizit")
                .file(mockFile)
                .session(session)
                .param("rekvizit", TestUtil.toJson(rekvizit))).andExpect(status().isCreated());
		
		rekvizit.setNazivRekvizita("Test4");
		rekvizit.setCenaRekvizita(60);
		rekvizit.setOpisRekvizita("Novi opis");
		
		mockMvc.perform(fileUpload(URL_PREFIX+"/dodajTematskiRekvizit")
                .file(mockFile)
                .session(session)
                .param("rekvizit", TestUtil.toJson(rekvizit))).andExpect(status().isCreated());
       
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void prikazRekvizitaTest() throws Exception {
		mockMvc.perform(get(URL_PREFIX+"/prikaziTematskeRekvizite").session(session)).andExpect(status().isOk());
	}
	
	
}
