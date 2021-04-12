package com.mobileshop.MobileShop.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.mobileshop.MobileShop.entity.Product;

@Service
public class CartService {
	public Map<Product, Integer> getCart(HttpSession session){
		
		Map<Product, Integer> cart = (HashMap<Product, Integer>) session.getAttribute("cart");
		if(cart==null) {
			cart = new HashMap<Product, Integer>();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
}
