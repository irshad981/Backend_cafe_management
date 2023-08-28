package com.cafe.proejct.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe.proejct.POJO.Category;

public interface CategoryDao extends JpaRepository<Category , Integer> {
	
	List<Category> getAllCategory();

}
