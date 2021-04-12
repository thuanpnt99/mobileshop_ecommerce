package com.mobileshop.MobileShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobileshop.MobileShop.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	
}
