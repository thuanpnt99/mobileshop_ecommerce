package com.mobileshop.MobileShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileshop.MobileShop.entity.Role;
import com.mobileshop.MobileShop.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired RoleRepository repo;
	
	public Role getRole(String name) {
		return repo.findByName(name);
	}
}
