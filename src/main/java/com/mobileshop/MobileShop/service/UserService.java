package com.mobileshop.MobileShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mobileshop.MobileShop.entity.User;
import com.mobileshop.MobileShop.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("Khong tim thay user!");
		}
		return new UserDetailsImpl(user);
	}
	
	public void save(User user) {
		userRepo.save(user);
	}
}
