package com.teamtreehouse.giflib.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.teamtreehouse.giflib.model.Gif;

public interface GifService {
	
	List<Gif> findAll();
	Gif findById(Long id);
	void save(Gif gif, MultipartFile file);
	void delete(Gif gif);
	void toggleFavorite(Gif gif);

}
