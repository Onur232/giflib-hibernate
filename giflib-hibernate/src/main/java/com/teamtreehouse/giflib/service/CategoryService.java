package com.teamtreehouse.giflib.service;

import java.util.List;

import com.teamtreehouse.giflib.model.Category;

public interface CategoryService {
	
	List<Category> findAll();
	Category findById(Long id);
	void save(Category category);
	void delete(Category category);

}
