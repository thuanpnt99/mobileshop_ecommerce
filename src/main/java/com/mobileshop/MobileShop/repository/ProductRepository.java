package com.mobileshop.MobileShop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobileshop.MobileShop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	Page<Product> findAllByNameContaining(String name, Pageable pageable);
}
