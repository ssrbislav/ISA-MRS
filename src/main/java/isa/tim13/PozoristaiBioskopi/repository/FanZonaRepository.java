package isa.tim13.PozoristaiBioskopi.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import isa.tim13.PozoristaiBioskopi.model.TematskiRekvizit;

@Repository
public interface FanZonaRepository extends CrudRepository<TematskiRekvizit, Integer> {
	
	TematskiRekvizit findByNazivRekvizita(String naziv);
}
