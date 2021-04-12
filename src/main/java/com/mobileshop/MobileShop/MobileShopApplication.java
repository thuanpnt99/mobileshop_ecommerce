package com.mobileshop.MobileShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.mobileshop.MobileShop.entity.User;
import com.mobileshop.MobileShop.service.UserService;

@SpringBootApplication
public class MobileShopApplication{

	public static void main(String[] args) {
		SpringApplication.run(MobileShopApplication.class, args);
	}


//	@Autowired
//	private UserService userService;
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//
//	@Override
//	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setFirst_name("phan van");
//		user.setLast_name("duc");
//		user.setUsername("ducduc");
//		user.setPassword(passwordEncoder.encode("duc123"));
//		user.setAddress("ha noi");
//		user.setPhone("0123456789");
//		user.setStatus(true);
//		userService.save(user);
//	}
}
