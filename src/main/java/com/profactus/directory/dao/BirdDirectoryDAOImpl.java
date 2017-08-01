package com.profactus.directory.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.profactus.directory.entity.BirdEntity;

@Repository("birdDirectoryServiceDAO")
@Transactional
public class BirdDirectoryDAOImpl extends AbstractBirdDAO implements BirdDirectoryDAO{

	public BirdEntity addBird(BirdEntity birdEntity) {
		return (BirdEntity) persist(birdEntity);
	}

	public void deleteBird(int id) {
		Query query = getSession().createSQLQuery("delete from bird where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<BirdEntity> getAllVisibleBirds() {		
		Criteria criteria = getSession().createCriteria(BirdEntity.class);
		criteria.add(Restrictions.eq("visible", true));
		return (List<BirdEntity>) criteria.list();
	}

	public BirdEntity getBirdById(int birdId) {
		Criteria criteria = getSession().createCriteria(BirdEntity.class);
		criteria.add(Restrictions.eq("id", birdId));
		return (BirdEntity) criteria.uniqueResult();
	}
	
}
