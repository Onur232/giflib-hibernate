package com.teamtreehouse.giflib.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teamtreehouse.giflib.model.Category;
import com.teamtreehouse.giflib.model.Gif;

@Repository
public class GifDaoImpl implements GifDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Gif> findAll() {
		// Open a session
		Session session = sessionFactory.openSession();

		// Get all categories with a hibernate criteria
		List<Gif> gifs = session.createCriteria(Gif.class).list();

		// Close session
		session.close();
		return gifs;
	}

	public Gif findById(Long id) {
		// Open a session
		Session session = sessionFactory.openSession();
		Gif gif = (Gif) session.get(Gif.class, id);
		session.close();
		return gif;
	}

	public void save(Gif gif) {
		// Open a session
		Session session = sessionFactory.openSession();
		// Begin a transaction
		session.beginTransaction();
		// Save the category
		session.saveOrUpdate(gif);
		// Commit the transaction
		session.getTransaction().commit();
		// Close the session
		session.close();

	}

	public void delete(Gif gif) {
		// Open a session
		Session session = sessionFactory.openSession();
		// Begin a transaction
		session.beginTransaction();
		// Save the category
		session.delete(gif);
		// Commit the transaction
		session.getTransaction().commit();
		session.close();

	}

}
