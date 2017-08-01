package com.profactus.directory.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.testng.Assert;

import com.profactus.directory.configuration.AppConfig;
import com.profactus.directory.dao.BirdDirectoryServiceDAO;
import com.profactus.directory.entity.BirdEntity;
import com.profactus.directory.util.CommonUtil;
import com.profactus.directory.util.DBUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class BirdDirectoryDAOImplTest {

	@Autowired
	public BirdDirectoryServiceDAO birdServiceDao;
	
	@Autowired
    private static Environment environment;
	
	private static final String driverName = environment.getRequiredProperty("jdbc.driverClassName");
	private static final String url = environment.getRequiredProperty("jdbc.url");
	private static final String username = environment.getRequiredProperty("jdbc.username");
	private static final String password = environment.getRequiredProperty("jdbc.password");
	
	public static final String BIRD_TABLE = "create table BIRD ( "
		      + "   id INT NOT NULL auto_increment, name VARCHAR(50) NOT NULL, family VARCHAR(50) NOT NULL, "
		      + "   continents VARCHAR(200) NOT NULL, added_on DATE NOT NULL, visible BOOL NOT NULL DEFAULT 1, "
		      + " PRIMARY KEY (id))";
	
	@BeforeClass
	public static void setUp(){

	    Connection conn = null;
	    Statement stmt = null;
	    
	    try {
	      conn = DBUtil.getConnection(driverName, url, username, password);
	      stmt = conn.createStatement();
	      
	      stmt.execute("select * from bird");
	      stmt.executeUpdate(BIRD_TABLE);
	      stmt.executeUpdate("Insert into bird (name, family, continents, added_on) values('Sparrow', 'small-birds', 'Asia, Australia', '2017-08-01');");
	      stmt.executeUpdate("Insert into bird (name, family, continents, added_on) values('White Pegion', 'medium-birds', 'Asia, Australia', '2018-08-01');");
	      System.out.println("Bird table created.");
	    } catch (ClassNotFoundException e) {
	      System.out.println("error: failed to load MySQL driver.");
	      e.printStackTrace();
	    } catch (SQLException e) {
	      System.out.println("error: failed to create a connection object.");
	      e.printStackTrace();
	    } catch (Exception e) {
	      System.out.println("other error:");
	      e.printStackTrace();
	    } finally {
	      try {
	        stmt.close();
	        conn.close();        
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	  
	}

	@Test
	public void testBirdDirectoryService() {
		assertEquals("class com.profactus.directory.dao.BirdDirectoryServiceDAOImpl",
				this.birdServiceDao.getClass().toString());
		for(BirdEntity bird : birdServiceDao.getAllVisibleBirds()) {
			System.out.println(bird.toString());
		}
	}
	
	@Test
	public void testAddBird() {
		BirdEntity b1 = new BirdEntity();
		b1.setId(1);
		b1.setName("Crow");
		b1.setFamily("Eagle");
		String[] continents = {"ASIA", "PACIFIC", "AUSTRALIA"};
		b1.setContinents(CommonUtil.format(Arrays.toString(continents)));
		b1.setVisible(true);
		Date date = new Date();
		b1.setAdded(date);
		
		equals(b1.equals(birdServiceDao.addBird(b1)));
	}
	
	@Test
	public void testGetById(){
		Assert.assertNotNull(birdServiceDao.getBirdById(1));
		Assert.assertNull(birdServiceDao.getBirdById(3));
	}
	
	@Test
	public void testGetAllVisibleBirds() {
		Assert.assertNotNull(birdServiceDao.getAllVisibleBirds());
	}

	@Test
	public void testDeleteById() {
		birdServiceDao.deleteBird(1);
		Assert.assertNull(birdServiceDao.getBirdById(1));
	}
}
