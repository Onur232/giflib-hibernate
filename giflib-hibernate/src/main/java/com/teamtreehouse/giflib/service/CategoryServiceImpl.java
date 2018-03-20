package com.teamtreehouse.giflib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamtreehouse.giflib.dao.CategoryDao;
import com.teamtreehouse.giflib.model.Category;

@Service
public class CategoryServiceImpl  implements CategoryService{
	
	@Autowired
	private CategoryDao categoryDao;

	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	public Category findById(Long id) {
		return categoryDao.findById(id);
	}

	public void save(Category category) {
		categoryDao.save(category);
	}

	public void delete(Category category) {
		categoryDao.delete(category);
		//controller'da yapılan list'in boş olduğunda silinmesi check'i burda yapılabilirdi. Eğer boş değilse
		//exception fırlatılırdı.
	}

}
