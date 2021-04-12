package com.mobileshop.MobileShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobileshop.MobileShop.entity.OrderDetail;

@Repository
public interface OrderDetailRepository  extends JpaRepository<OrderDetail, Integer>{
	
}
