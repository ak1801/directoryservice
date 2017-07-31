package com.profactus.directory.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.profactus.directory.common.DirectoryContants;
import com.profactus.directory.dao.BirdDirectoryServiceDAO;
import com.profactus.directory.entity.BirdEntity;
import com.profactus.directory.exception.BirdDirectoryServiceErrorCodes;
import com.profactus.directory.model.Bird;

@Service("birdDirectoryService")
@Transactional
public class BirdDirectoryServiceImpl implements BirdDirectoyService{

	@Autowired
	public BirdDirectoryServiceDAO birdServiceDao;
	
	public void setBirdServiceDao(BirdDirectoryServiceDAO birdServiceDao) {
		this.birdServiceDao = birdServiceDao;
	}

	public Bird addBird(Bird bird) throws UnrecognizedPropertyException{
		validateRequest(bird);
		BirdEntity entity = birdServiceDao.addBird(convertBeanToEntity(bird));
		return convertEntityToBean(entity);
	}

	public Bird[] getAllVisibleBirds() {
		List<BirdEntity> entities = birdServiceDao.getAllVisibleBirds();
		
		int size = entities.size();
		
		if(size > 0) {
			Bird[] birds = new Bird[size];
			for(int i = 0; i < size; i++) {
				birds[i] = convertEntityToBean(entities.get(i));
			}
			return birds;
		} else {
			return null;
		}
	}

	public Bird getBirdById(String id) throws UnrecognizedPropertyException {
		int objId = validateAndConvertId(id);
		BirdEntity entity = birdServiceDao.getBirdById(objId);
		if(entity != null) {
			return convertEntityToBean(entity);
		} else return null;
	}

	public boolean deleteBird(String id) throws UnrecognizedPropertyException {
		int objId = validateAndConvertId(id);
		BirdEntity entity = birdServiceDao.getBirdById(objId);
		if(entity != null) {
			birdServiceDao.deleteBird(objId);
			return true;
		}
		return false;
	}

	private int validateAndConvertId(String id) throws UnrecognizedPropertyException {
		
		int objId = 0;
		
		if(id == null || id.equals("") || id.equals(" ")) {
			//throw new BirdDirectoyServiceException(BirdDirectoryServiceErrorCodes.INVALID_ID);
			throw new UnrecognizedPropertyException(BirdDirectoryServiceErrorCodes.INVALID_ID, null, Bird.class, "id");
		} else {
			try{
				objId = Integer.parseInt(id);
			} catch (Exception ex) {
				throw new RuntimeException("Conversion Error");
			}
		}
		return objId;
		
	}
	
	private void validateRequest(Bird bird) throws UnrecognizedPropertyException {
		
		if(bird == null) {
			throw new UnrecognizedPropertyException(BirdDirectoryServiceErrorCodes.INVALID_REQUEST_OBJECT, null, Bird.class, "bird");
		}
		
		if(bird.getName() == null || bird.getName().equals("") || bird.getName().equals(" ")) {
			throw new UnrecognizedPropertyException(BirdDirectoryServiceErrorCodes.INVALID_BIRD_NAME, null, Bird.class, "name");
		}
		
		if(bird.getFamily() == null || bird.getFamily().equals("") || bird.getFamily().equals(" ")) {
			throw new UnrecognizedPropertyException(BirdDirectoryServiceErrorCodes.INVALID_BIRD_FAMILY, null, Bird.class, "family");
		}
		
		if(bird.getContinents().length <= 0){
			throw new UnrecognizedPropertyException(BirdDirectoryServiceErrorCodes.INVALID_BIRD_CONTINENTS, null, Bird.class, "continents");
		} else {
			for(String continent : bird.getContinents()) {
				if(continent.equals("") || continent.equals(" ") || continent == null) {
					throw new UnrecognizedPropertyException(BirdDirectoryServiceErrorCodes.INVALID_BIRD_CONTINENTS, null, Bird.class, "continents");
				}
			}
			bird.setContinents(removeDuplicateContinents(bird.getContinents()));
		}
	}
	
	private String[] removeDuplicateContinents(String[] continents) {
		Set<String> uniqueSet = new HashSet<String>();
		for(String continent : continents) {
			uniqueSet.add(continent);
		}
		String[] uniqueContinents = new String[uniqueSet.size()];
		
		Iterator<String> iterator = uniqueSet.iterator(); 
	    int i = 0;
	    
		while (iterator.hasNext()){
			uniqueContinents[i] = iterator.next();
		  i++;
		}
		
		return uniqueContinents;
	}
	
	private BirdEntity convertBeanToEntity(Bird bird) {
		
		BirdEntity entity = new BirdEntity();
		entity.setName(bird.getName());
		entity.setFamily(bird.getFamily());
		entity.setVisible(bird.isVisible() ? true : false);
		entity.setContinents(format(Arrays.toString(bird.getContinents())));
		
		Date date = Calendar.getInstance().getTime(); 
		entity.setAdded(date);
		
		return entity;
	}
	
	private static String format(String str) {
		
		return str.replaceAll("[\\[\\]]", "");
	}
	
	private Bird convertEntityToBean(BirdEntity entity) {
		
		Bird bird = new Bird();
		bird.setId(Integer.valueOf(entity.getId()).toString());
		bird.setName(entity.getName());
		bird.setFamily(entity.getFamily());
		bird.setVisible(entity.isVisible());
		
		String[] continents = entity.getContinents().split(", ");
		bird.setContinents(continents);
		
		Date date = entity.getAdded();
		SimpleDateFormat format = new SimpleDateFormat(DirectoryContants.DATE_FORMAT);
		bird.setAdded(format.format(date));
		
		return bird;
	}
}
