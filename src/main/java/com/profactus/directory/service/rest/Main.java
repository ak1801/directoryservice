package com.profactus.directory.service.rest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.profactus.directory.configuration.AppConfig;
import com.profactus.directory.service.BirdDirectoyService;

public class Main {

	public static void main(String args[]) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		BirdDirectoyService service = (BirdDirectoyService) context.getBean("birdDirectoryService");

		/*Bird bird = new Bird();
		bird.setName("BlackSparrow");
		bird.setFamily("Small-bird");
		String[] continents = {"America", "Pacific", "Argentina"};
		bird.setContinents(continents);
		bird.setVisible(true);
		System.out.println("Bird Added : "+service.addBird(bird));*/
		
		//getAllVisibleBirds
		/*for(Bird b : service.getAllVisibleBirds()) {
			System.out.println(b);
		}*/
		
		//GetBirdById
		System.out.println(service.getBirdById("3"));
		
		//DeleteById
		//service.deleteBird("2");
		
		

		context.close();
	}
}
