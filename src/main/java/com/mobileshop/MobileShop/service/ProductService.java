package com.mobileshop.MobileShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mobileshop.MobileShop.entity.Product;
import com.mobileshop.MobileShop.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo; 
	
	public Page<Product> listAll(int pageNum, String sortField, String sortDir){
		int pageSize = 8;
		
		Pageable pageable = PageRequest.of(pageNum-1, pageSize,
				sortDir.equals("asc") ? Sort.by(sortField).ascending():
					Sort.by(sortField).descending());
		
		return repo.findAll(pageable);
	}
	public Product get(Integer id) {
		return repo.findById(id).get();
	}
	public Page<Product> search(String keyword, int pageNum){
		int pageSize = 8;
		Pageable pageable = PageRequest.of(pageNum-1, pageSize);
		if(keyword != null) {
			return repo.findAllByNameContaining(keyword, pageable);
		}
		return repo.findAll(pageable);
	}
}
