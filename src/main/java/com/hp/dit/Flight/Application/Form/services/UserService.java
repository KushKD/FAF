package com.hp.dit.Flight.Application.Form.services;

import java.util.List;


import com.hp.dit.Flight.Application.Form.entities.UserEntity;
import com.hp.dit.Flight.Application.Form.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;


	

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	
	
	public UserEntity getUserDetails(Long mobileNumber) {
		return userRepository.getUserDetails(mobileNumber);
		
	}

	public UserEntity saveUser(UserEntity entity) {
		return userRepository.save(entity);

	}

	public List<Object[] > getUserId(String username){
		return userRepository.getUserID(username);
	}

}
