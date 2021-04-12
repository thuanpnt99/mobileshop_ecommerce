package com.mobileshop.MobileShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobileshop.MobileShop.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}
