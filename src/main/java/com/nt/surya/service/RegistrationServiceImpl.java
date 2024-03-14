package com.nt.surya.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.surya.bindings.User;
import com.nt.surya.entities.CityEntity;
import com.nt.surya.entities.CountryEntity;
import com.nt.surya.entities.StateEntity;
import com.nt.surya.entities.UserEntity;
import com.nt.surya.repositories.CityRepository;
import com.nt.surya.repositories.CountryRepository;
import com.nt.surya.repositories.StateRepository;
import com.nt.surya.repositories.UserRepository;
import com.nt.surya.utils.EmailUtils;

@Service // to make class as spring bean use this anno.
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public boolean uniqueEmail(String email) {
		UserEntity userEntity = userRepo.findByUserEmail(email);
		if (userEntity != null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Map<Integer, String> getContries() {
		List<CountryEntity> findAll = countryRepo.findAll();

		// convert list to map
		Map<Integer, String> countryMap = new HashMap<>();

		for (CountryEntity entity : findAll) {
			countryMap.put(entity.getCountryId(), entity.getCountryName());
		}
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<StateEntity> statesList = stateRepo.findByCountryId(countryId);
		Map<Integer, String> statesMap = new HashMap<>();
		for (StateEntity state : statesList) {
			statesMap.put(state.getStateId(), state.getStateName());
		}
		return statesMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<CityEntity> citiesList = cityRepo.findByStateId(stateId);
		Map<Integer, String> citiesMap = new HashMap<>();
		for (CityEntity city : citiesList) {
			citiesMap.put(city.getCityId(), city.getCityName());
		}
		return citiesMap;
	}

	@Override
	public boolean registerUser(User user) {
		//
		user.setUserPwd(generateTempPwd());
		user.setUserAccStatus("LOCKED");

		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(user, entity);
		UserEntity save = userRepo.save(entity);

		if (null != save.getUserId()) {
			sendRegEmail(user);
		}

		return false;
	}

	private String generateTempPwd() {

		String tempPwd = null;

		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 6;
		Random random = new Random();
		tempPwd = random.ints(leftLimit, rightLimit + 1)
				 .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				 .limit(targetStringLength)
				 .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				 .toString();

		return tempPwd;
	}

	private boolean sendRegEmail(User user) {

		boolean emailSent = false;

		String subject=" User Registration Success";
		String body=readMailBody("UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt",user);
		
		emailUtils.sendEmail(subject, body, user.getUserEmail());

		return emailSent;
	}

	public String readMailBody(String fileName, User user) {
		String mailBody=null;
		
		StringBuffer buffer=new StringBuffer();
		
		Path path=Paths.get(fileName);
		
		try(Stream<String> stream=Files.lines(path)) {
			stream.forEach(line-> {
				buffer.append(line);
			});
			
			mailBody =buffer.toString();
			mailBody.replace("{FNAME}", user.getUserFName());
			mailBody.replace("{EMAIL}", user.getUserEmail());
			mailBody.replace("{TEMP=PWD}", user.getUserPwd());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mailBody;
	}
}
