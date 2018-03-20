package com.teamtreehouse.giflib.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teamtreehouse.giflib.model.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Category> findAll() {
		// Open a session
		Session session = sessionFactory.openSession();

		// Get all categories with a hibernate criteria
		List<Category> categories = session.createCriteria(Category.class).list();

		// Close session
		session.close();
		return categories;
	}

	public Category findById(Long id) {
		Session session = sessionFactory.openSession();
		Category category = (Category) session.get(Category.class, id);
		Hibernate.initialize(category.getGifs());
		session.close();
		return category;
	}

	public void save(Category category) {
		// Open a session
		Session session = sessionFactory.openSession();

		// Begin a transaction
		session.beginTransaction();

		// Save the category
		session.saveOrUpdate(category);

		// Commit the transaction
		session.getTransaction().commit();

		// Close the session
		session.close();
	}

	public void delete(Category category) {
		// Open a session
		Session session = sessionFactory.openSession();

		// Begin a transaction
		session.beginTransaction();

		// Save the category
		session.delete(category);

		// Commit the transaction
		session.getTransaction().commit();

		// Close the session
		session.close();
	}

}
