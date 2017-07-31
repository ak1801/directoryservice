package com.profactus.directory.service;

import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;

import com.profactus.directory.model.Bird;

public interface BirdDirectoyService {

	public Bird addBird(Bird bird) throws UnrecognizedPropertyException;
	
	public boolean deleteBird(String id) throws UnrecognizedPropertyException;
	
	public Bird[] getAllVisibleBirds();
	
	public Bird getBirdById(String id) throws UnrecognizedPropertyException;
}
