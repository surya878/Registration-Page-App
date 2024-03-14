package com.nt.surya.service;

import java.util.Map;

import com.nt.surya.bindings.User;

public interface RegistrationService {

	public boolean uniqueEmail(String email);
	
	public Map<Integer, String> getContries();
	
	public Map<Integer, String> getStates(Integer countryId);
	
	public Map<Integer, String> getCities(Integer stateId);
	
	public boolean registerUser(User user);
}
