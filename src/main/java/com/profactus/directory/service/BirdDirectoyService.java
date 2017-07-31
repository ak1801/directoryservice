package com.profactus.directory.service;

import com.profactus.directory.exception.BirdDirectoyServiceException;
import com.profactus.directory.model.Bird;

public interface BirdDirectoyService {

	public Bird addBird(Bird bird) throws BirdDirectoyServiceException;
	
	public void deleteBird(String id);
	
	public Bird[] getAllVisibleBirds();
	
	public Bird getBirdById(String id);
}
