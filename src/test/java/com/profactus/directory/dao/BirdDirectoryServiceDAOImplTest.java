package com.profactus.directory.dao;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BirdDirectoryServiceDAOImplTest extends EntityDaoImplTest{

	@Autowired
	BirdDirectoryServiceDAO birdDirectoryServiceDao;

	@Override
	protected IDataSet getDataSet() throws Exception{
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Bird.xml"));
		return dataSet;
	}

	@Test
	public void getById(){
		Assert.assertNotNull(birdDirectoryServiceDao.getBirdById(1));
		Assert.assertNull(birdDirectoryServiceDao.getBirdById(3));
	}

}
