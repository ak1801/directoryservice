package com.profactus.directory.service;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.profactus.directory.dao.BirdDirectoryServiceDAO;
import com.profactus.directory.entity.BirdEntity;

public class BirdDirectoryServiceImplTest {

	@Mock
	BirdDirectoryServiceDAO dao;
	
	@InjectMocks
	BirdDirectoryServiceImpl birdDirectoryService;
	
	@Spy
	List<BirdEntity> birds = new ArrayList<BirdEntity>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		birds = getBirdList();
	}

	@Test
	public void getById(){
		BirdEntity bird = birds.get(0);
		when(dao.getBirdById(anyInt())).thenReturn(bird);
		try {
			Assert.assertEquals(birdDirectoryService.getBirdById(String.valueOf(bird.getId())),bird);
		} catch (UnrecognizedPropertyException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAllVisibleBirds(){
		when(dao.getAllVisibleBirds()).thenReturn(birds);
		Assert.assertEquals(birdDirectoryService.getAllVisibleBirds(), birds);
	}
	
	public List<BirdEntity> getBirdList(){
		BirdEntity b1 = new BirdEntity();
		b1.setId(1);
		b1.setName("Crow");
		b1.setFamily("Eagle");
		String[] continents = {"ASIA", "PACIFIC", "AUSTRALIA"};
		b1.setContinents(format(Arrays.toString(continents)));
		b1.setVisible(true);
		Date date = new Date();
		b1.setAdded(date);
		
		BirdEntity b2 = new BirdEntity();
		b2.setName("White Pegion");
		b2.setFamily("Pegions");
		b2.setContinents(format(Arrays.toString(continents)));
		b2.setVisible(true);
		b1.setAdded(date);
		
		birds.add(b1);
		birds.add(b2);
		return birds;
	}
	
	private static String format(String str) {
		
		return str.replaceAll("[\\[\\]]", "");
	}
}
