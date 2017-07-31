package com.profactus.directory.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.profactus.directory.dao.BirdDirectoryServiceDAO;
import com.profactus.directory.exception.BirdDirectoryServiceErrorCodes;
import com.profactus.directory.exception.BirdDirectoyServiceException;
import com.profactus.directory.model.Bird;
import com.profactus.directory.model.BirdEntity;

@Service("birdDirectoryService")
@Transactional
public class BirdDirectoryServiceImpl implements BirdDirectoyService{

	@Autowired
	public BirdDirectoryServiceDAO birdServiceDao;
	
	public void setBirdServiceDao(BirdDirectoryServiceDAO birdServiceDao) {
		this.birdServiceDao = birdServiceDao;
	}

	public Bird addBird(Bird bird) throws BirdDirectoyServiceException{
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

	public Bird getBirdById(String id) {
		int objId = validateAndConvertId(id);
		BirdEntity entity = birdServiceDao.getBirdById(objId);
		if(entity != null) {
			return convertEntityToBean(entity);
		} else return null;
	}

	public void deleteBird(String id) {
		
		int objId = validateAndConvertId(id);
		BirdEntity entity = birdServiceDao.getBirdById(objId);
		if(entity != null) {
			birdServiceDao.deleteBird(objId);
		}
	}

	private int validateAndConvertId(String id) {
		
		int objId = 0;
		
		if(id == null || id.equals("") || id.equals(" ")) {
			throw new BirdDirectoyServiceException(BirdDirectoryServiceErrorCodes.INVALID_ID);
		} else {
			try{
				objId = Integer.parseInt(id);
			} catch (Exception ex) {
				throw new RuntimeException("Conversion Error");
			}
		}
		return objId;
		
	}
	
	private void validateRequest(Bird bird) {
		
		if(bird == null) {
			throw new BirdDirectoyServiceException(BirdDirectoryServiceErrorCodes.NULL_REQUEST);
		}
		
		if(bird.getName() == null || bird.getName().equals("") || bird.getName().equals(" ")) {
			throw new BirdDirectoyServiceException(BirdDirectoryServiceErrorCodes.INVALID_BIRD_NAME);
		}
		
		if(bird.getFamily() == null || bird.getFamily().equals("") || bird.getFamily().equals(" ")) {
			throw new BirdDirectoyServiceException(BirdDirectoryServiceErrorCodes.INVALID_BIRD_NAME);
		}
		
		if(bird.getName() == null || bird.getName().equals("") || bird.getName().equals(" ")) {
			throw new BirdDirectoyServiceException(BirdDirectoryServiceErrorCodes.INVALID_FAMILY);
		}
		
		if(bird.getContinents().length <= 0){
			throw new BirdDirectoyServiceException(BirdDirectoryServiceErrorCodes.INVALID_CONTINENTS);
		} else {
			for(String continent : bird.getContinents()) {
				if(continent.equals("") || continent.equals(" ") || continent == null) {
					throw new BirdDirectoyServiceException(BirdDirectoryServiceErrorCodes.INVALID_CONTINENTS);
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
		entity.setContinents(Arrays.toString(bird.getContinents()));
		
		Date date = Calendar.getInstance().getTime(); 
		entity.setAdded(date);
		
		return entity;
	}
	
	private Bird convertEntityToBean(BirdEntity entity) {
		
		Bird bird = new Bird();
		bird.setId(Integer.valueOf(entity.getId()).toString());
		bird.setName(entity.getName());
		bird.setFamily(entity.getFamily());
		bird.setVisible(entity.isVisible());
		
		String[] continents = entity.getContinents().split(",");
		bird.setContinents(continents);
		
		bird.setAdded(entity.getAdded().toString());
		// Create an instance of SimpleDateFormat used for formatting 
		DateFormat df = new SimpleDateFormat("YYYY/MM/DD");
		Date today = Calendar.getInstance().getTime();        
		String reportDate = df.format(today);

		bird.setAdded(reportDate);
		
		return bird;
	}
}
