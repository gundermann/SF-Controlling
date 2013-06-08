package com.sfcontroll.db;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sfcontroll.business.Costtype;

public class CosttypeDAO {

	public static void saveOrUpdateType(Costtype cat)
      {               
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(cat);
        transaction.commit();
      }
	
	public static List<Costtype> getAllCosttypesFromDB(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction =  session.beginTransaction();
		Criteria criteria = session.createCriteria(Costtype.class);
		transaction.commit();
		return criteria.list();
	}

	public static Costtype getCategoryById(String selectedItem) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction =  session.beginTransaction();
		Costtype cat = (Costtype) session.get(Costtype.class, selectedItem);
		transaction.commit();
		return cat;
	}

	public static void getDeleteCategory(String selectedItem) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction =  session.beginTransaction();
		Costtype cat = (Costtype) session.get(Costtype.class, selectedItem);
		session.delete(cat);
		transaction.commit();
	}
}
