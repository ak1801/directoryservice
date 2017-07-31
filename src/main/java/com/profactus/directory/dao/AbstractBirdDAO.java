package com.profactus.directory.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.profactus.directory.entity.BirdEntity;

public class AbstractBirdDAO {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public BirdEntity persist(Object entity) {
		Session session = getSession();
		session.persist(entity);
		session.flush();
		return (BirdEntity) entity;
	}

	public void delete(Object entity) {
		getSession().delete(entity);
	}
}
