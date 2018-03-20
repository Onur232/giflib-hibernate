package com.teamtreehouse.giflib.dao;

import java.util.List;


import com.teamtreehouse.giflib.model.Gif;

public interface GifDao {
	
	List<Gif> findAll();
	Gif findById(Long id);
	void save(Gif gif);
	void delete(Gif gif);

}
