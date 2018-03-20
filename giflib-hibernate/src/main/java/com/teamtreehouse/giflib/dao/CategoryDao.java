package com.teamtreehouse.giflib.dao;

import java.util.List;

import com.teamtreehouse.giflib.model.Category;

public interface CategoryDao {
	
	List<Category> findAll();
	Category findById(Long id);
	void save(Category category);
	void delete(Category category);

}
