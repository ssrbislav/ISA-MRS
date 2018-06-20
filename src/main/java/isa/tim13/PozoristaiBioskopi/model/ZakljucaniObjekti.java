package isa.tim13.PozoristaiBioskopi.model;

import java.util.concurrent.ConcurrentHashMap;

public class ZakljucaniObjekti {
	private ConcurrentHashMap<Integer,Object> kljucevi;
	
	public ZakljucaniObjekti() {
		kljucevi = new ConcurrentHashMap<Integer,Object>();
	}
	
	public Object dobaviObjekat(int id) {
		if(kljucevi.containsKey(id)) {
			return kljucevi.get(id);
		}
		kljucevi.put(id, new Object());
		return kljucevi.get(id);
	}
}
