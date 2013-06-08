package com.sfcontroll.db;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class SubcostDAO {

	public static void saveOrUpedateSubcost(DTOSubcosts subcosts) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(subcosts);
        transaction.commit();		
	}

	public static int findNextId() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction =  session.beginTransaction();
		Criteria criteria = session.createCriteria(DTOSubcosts.class).setProjection(Projections.max("subcostid"));
		transaction.commit();
		int maxId = 0;
		if(criteria.uniqueResult() != null){
			maxId = (int) criteria.uniqueResult();
		}
		return ++maxId;
	}

	public static List<DTOSubcosts> getAllSubcostsByEntryId(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction =  session.beginTransaction();
		Criteria criteria = session.createCriteria(DTOSubcosts.class).add(Restrictions.eq("entryid", id));
		transaction.commit();
		return criteria.list();
	}

	public static DTOSubcosts findSubcostByData(String costtypeName, int entryId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction =  session.beginTransaction();
		Criteria criteria = session.createCriteria(DTOSubcosts.class).add(Restrictions.conjunction().add(Restrictions.eq("entryid", entryId)).add(Restrictions.eq("costtype", costtypeName)));
		transaction.commit();
		
		if(criteria.list().isEmpty())
			return null;
		else
			return (DTOSubcosts) criteria.list().get(0);
	}

}
