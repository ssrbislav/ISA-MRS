package isa.tim13.PozoristaiBioskopi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isa.tim13.PozoristaiBioskopi.model.Karta;
import isa.tim13.PozoristaiBioskopi.model.Korisnik;
@Repository
public interface KartaRepository extends CrudRepository<Karta, Integer>{
	Karta findByLinkZaOtkazivanje(String linkZaOtkazivanje);
}
