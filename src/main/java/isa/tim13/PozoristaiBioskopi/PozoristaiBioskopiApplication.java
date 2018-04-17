package isa.tim13.PozoristaiBioskopi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class PozoristaiBioskopiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PozoristaiBioskopiApplication.class, args);
	}
	
	@RequestMapping(value="/")
	public String hello() {
		return "Hello world!";
		
	}
}
