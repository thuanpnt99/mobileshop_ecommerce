package com.mobileshop.MobileShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileshop.MobileShop.entity.OrderDetail;
import com.mobileshop.MobileShop.entity.User;
import com.mobileshop.MobileShop.repository.OrderRepository;

@Service
public class ShoppingCartService {
	
	@Autowired
	OrderRepository cartRepo;
	
}
