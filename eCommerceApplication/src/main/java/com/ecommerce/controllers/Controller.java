package com.ecommerce.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Item;
import com.ecommerce.model.Orders;
import com.ecommerce.repository.ItemRepository;
import com.ecommerce.repository.OrderRepository;

@RestController
@RequestMapping(value = "/api/eca")
public class Controller {

	@Autowired
	ItemRepository itemRepository;
	@Autowired
	OrderRepository orderRepository;

	@RequestMapping("/index")
	public String index() {
		return "welcome";
	}

	@GetMapping(value = "/items")
	public List<Item> getAllItem(HttpServletRequest request) {
		return itemRepository.findAll();
	}

	@RequestMapping("/items")
	public List<Item> createItem(@RequestBody final Item item, HttpServletRequest request) {
		itemRepository.save(item);
		return itemRepository.findAll();
	}

	@RequestMapping(value = "/items/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateItem(@PathVariable(value = "id") Integer itemId,
			@Valid @RequestBody Item itemDetails) {

		Item item = itemRepository.findByitemid(itemId);

		if (item == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

		item.setItemcategory(itemDetails.getItemcategory());
		item.setItemdesc(itemDetails.getItemdesc());
		item.setItemname(itemDetails.getItemname());
		item.setItempicture(itemDetails.getItempicture());
		item.setItemprice(itemDetails.getItemprice());
		item.setItemstatus(itemDetails.getItemstatus());
		itemRepository.save(item);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteItemId(@PathVariable("id") int id) {

		Item item = (Item) itemRepository.findByitemid(id);
		if (item == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		itemRepository.deleteByitemid(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping(value = "/orders")
	public List<Orders> getAllOrders(HttpServletRequest request) {
		return orderRepository.findAll();
	}

	@PostMapping("/orders/{itemid}")
	public Map<String, String> createOrder(@PathVariable("itemid") int id) {
		Item item = (Item) itemRepository.findByitemid(id);
		Map<String, String> result = new HashMap<>();
		if (item == null) {
			result.put("status", "Item Not Found");
			return result;
		}
		if (item.getItemstatus().equals("sold")) {
			result.put("status", " out of stock");
			return result;
		}
		item.setItemstatus("sold");
		itemRepository.save(item);
		Date date = new Date();
		Orders orders = new Orders();
		orders.setItemid(id);
		orders.setUserid(1);
		orders.setOrderdate(date);
		orderRepository.save(orders);
		result.put("status", "Order Succesful");
		return result;
	}

	@RequestMapping(value = "/orders", params = "ids", method = RequestMethod.POST)
	@ResponseBody
	public Map<Integer, String> getBooksById_params(@RequestParam List<Integer> ids) {
		Map<Integer, String> result = new HashMap<>();
		for (Object obj : ids) {
			Integer itemid = (Integer) obj;
			Item item = (Item) itemRepository.findByitemid(itemid);
			if (item == null) {
				result.put(itemid, "Item Not Found");
			} else if (item.getItemstatus().equals("sold")) {
				result.put(itemid, " out of stock");

			} else {
				item.setItemstatus("sold");
				itemRepository.save(item);
				Date date = new Date();
				Orders orders = new Orders();
				orders.setItemid(itemid);
				orders.setUserid(1);
				orders.setOrderdate(date);
				orderRepository.save(orders);
				result.put(itemid, "Order Succesful");
			}
		}
		return result;
	}

}
