package com.profactus.directory.dao;

import java.util.List;

import com.profactus.directory.entity.BirdEntity;

public interface BirdDirectoryDAO {

	public BirdEntity addBird(BirdEntity birdEntity);
	
	public void deleteBird(int id);
	
	public List<BirdEntity> getAllVisibleBirds();
	
	public BirdEntity getBirdById(int id);
}
