package com.sfcontroll.db;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class SubcostDAO {

	public static void saveOrUpedateSubcost(DTOSubcosts subcosts) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(subcosts);
        transaction.commit();		
	}

}
