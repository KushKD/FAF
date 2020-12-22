package com.hp.dit.Flight.Application.Form.services;


import com.hp.dit.Flight.Application.Form.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RolesService {
	
	@Autowired
	private RolesRepository rolesRepository;


	public RolesRepository getRolesRepository() {
		return rolesRepository;
	}

	public void setRolesRepository(RolesRepository rolesRepository) {
		this.rolesRepository = rolesRepository;
	}

	public List<Object[]> getRoles(){
		return rolesRepository.getRoles();
	}



}
