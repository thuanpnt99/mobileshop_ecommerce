package com.mobileshop.MobileShop.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mobileshop.MobileShop.entity.Order;
import com.mobileshop.MobileShop.entity.OrderDetail;
import com.mobileshop.MobileShop.entity.Product;
import com.mobileshop.MobileShop.entity.User;
import com.mobileshop.MobileShop.repository.OrderDetailRepository;
import com.mobileshop.MobileShop.repository.OrderRepository;
import com.mobileshop.MobileShop.repository.UserRepository;
import com.mobileshop.MobileShop.service.CartService;
import com.mobileshop.MobileShop.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CartService cartService;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	@RequestMapping(value = {"/","/home"})
	public String viewHomePage(Model model) {
		return showPage(model, 1,"asc");
	}
	
	@RequestMapping(value = "/page/{pageNum}")
	public String showPage(Model model, 
			@PathVariable(name="pageNum") int currentPage,
			@Param("sortDir") String sortDir) {
		Page<Product> pageProducts = productService.listAll(currentPage, "price", sortDir);
		List<Product> listProducts = pageProducts.getContent();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", pageProducts.getTotalPages());
		model.addAttribute("totalItems", pageProducts.getTotalElements());
		model.addAttribute("sortDir", sortDir);
		
		model.addAttribute("listProducts", listProducts);
		return "index";
	}
	
	@RequestMapping("/search")
	public String search(Model model,
			@Param("keyword") String keyword,
			@Param("page") int page) {
		Page<Product> pageProducts = productService.search(keyword,page);
		List<Product> listProducts = pageProducts.getContent();
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", pageProducts.getTotalPages());
		model.addAttribute("totalItems", pageProducts.getTotalElements());
		model.addAttribute("listProducts", listProducts);
		return "index";
	}
	
	@RequestMapping("/addtocart/{id}")
	public String addToCart(@PathVariable(name = "id") Integer id,HttpSession session) {
		Product product = productService.get(id);
		
		if(product != null) {
			Map<Product, Integer> cart = cartService.getCart(session);
			if(cart.containsKey(product)) {
				Integer quantity = cart.get(product)+1;
				cart.remove(product);
				cart.put(product, quantity);
			}else {
				cart.put(product, 1);
			}
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/cart")
	public String showCart(HttpSession session) {
		session.setAttribute("cart", cartService.getCart(session));
		return "shopping_cart";
	}
	
	@RequestMapping("/deleteCartItem/{id}")
	public String deleteProduct(@PathVariable(name = "id") Integer id,HttpSession session) {
		Product product = productService.get(id);
		Map<Product, Integer> cart = cartService.getCart(session);
		cart.remove(product);
		
		return "redirect:/cart";
	}
	
	@RequestMapping("/checkout")
	public String checkout(HttpSession session) {
		User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		session.setAttribute("currentUser", user);
		Date date = new java.sql.Date(System.currentTimeMillis());
		Map<Product, Integer> cart = cartService.getCart(session);
		Set<Product> keySet = cart.keySet();
		double amount = 0;
		for (Product p : keySet) {
			amount+=p.getPrice()*cart.get(p);
		}
		Order order = new Order(date, amount,user, false);
		session.setAttribute("order", order);
		
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		for (Product p : keySet) {
			list.add(new OrderDetail(p, order, cart.get(p)));
		}
		session.setAttribute("listOrderDetail", list);
		
		return "payment_info";
	}
	
	@RequestMapping("/checkoutDone")
	public String checkoutDone(HttpSession session) {
		Order order = (Order)session.getAttribute("order");
		if(order!=null) {
			orderRepo.save(order);
			session.removeAttribute("order");
			List<OrderDetail> list = (ArrayList<OrderDetail>) session.getAttribute("listOrderDetail");
			orderDetailRepo.saveAll(list);
			session.removeAttribute("listOrderDetail");
			session.removeAttribute("cart");
		}
		return "checkout_done";
	}
}
