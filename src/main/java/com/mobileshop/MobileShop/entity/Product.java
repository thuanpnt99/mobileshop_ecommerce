package com.mobileshop.MobileShop.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private double price;
	private Date created_time;
	private Date updated_time;
	private String brand;
	private int inventory;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	
	
	public Integer getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public Date getUpdated_time() {
		return updated_time;
	}

	public void setUpdated_time(Date updated_time) {
		this.updated_time = updated_time;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}





	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}


	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}


	@Override
	public int hashCode() {
		int hashcode = 0;
		hashcode = id*20;
		return hashcode;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product) {
			Product product = (Product) obj;
			if (this.getId() == product.getId()) {
				return true;
			}
		}
		return false;

	}
}
