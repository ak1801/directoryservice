package com.profactus.directory.dao;

import java.util.List;

import com.profactus.directory.model.BirdEntity;


public interface BirdDirectoryServiceDAO {

	public BirdEntity addBird(BirdEntity birdEntity);
	
	public void deleteBird(int id);
	
	public List<BirdEntity> getAllVisibleBirds();
	
	public BirdEntity getBirdById(int id);
}