package com.profactus.directory.service;

import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;

import com.profactus.directory.model.Bird;

/**
 * Service API for providing CRUD operations on Bird type.
 * 
 * @author Akshit Mahajan
 *
 */
public interface BirdDirectoyService {

	/**
	 * Adds a bird to the directory.
	 * 
	 * @param bird input
	 * @return Bird added
	 * @throws UnrecognizedPropertyException
	 */
	public Bird addBird(Bird bird) throws UnrecognizedPropertyException;
	
	/**
	 * Removes a bird from the directory.
	 * 
	 * @param id of bird
	 * @return isDeleted
	 * @throws UnrecognizedPropertyException
	 */
	public boolean deleteBird(String id) throws UnrecognizedPropertyException;
	
	/**
	 * Returns an array of all the birds in the directory which are visible.
	 * 
	 * @return Bird[]
	 */
	public Bird[] getAllVisibleBirds();
	
	/**
	 * Returns a bird from directory based on id.
	 * 
	 * @param id of bird
	 * @return Bird
	 * @throws UnrecognizedPropertyException
	 */
	public Bird getBirdById(String id) throws UnrecognizedPropertyException;
}
