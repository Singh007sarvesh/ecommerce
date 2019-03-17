package com.ecommerce.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.ecommerce.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	@Modifying
	@Transactional
	void deleteByitemid(Integer id);

	public Item findByitemid(Integer id);
}
