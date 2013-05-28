package com.sfcontroll.db;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

public class SubcostDAO {

	public static void saveOrUpedateSubcost(DTOSubcosts subcosts) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(subcosts);
        transaction.commit();		
	}

	public static long findNextId() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction =  session.beginTransaction();
		Criteria criteria = session.createCriteria(DTOSubcosts.class).setProjection(Projections.max("subcostid"));
		transaction.commit();
		long maxId = 0;
		if(criteria.uniqueResult() != null){
			maxId = (long) criteria.uniqueResult();
		}
		return ++maxId;
	}

}
