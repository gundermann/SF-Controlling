package com.sfcontroll.db;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class CategoryDAO {
	

	public static List<DTOCategory> getAllCategoriesFromDB() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction =  session.beginTransaction();
		Criteria criteria = session.createCriteria(DTOCategory.class);
		transaction.commit();
		return criteria.list();
	}

	public static DTOCategory getCategoryByName(String selectedItem) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction =  session.beginTransaction();
		Criteria criteria = session.createCriteria(DTOCategory.class).add(Restrictions.eq("categoryName", selectedItem));
		transaction.commit();
		if(criteria.list().isEmpty())
			return null;
		else
			return (DTOCategory) criteria.list().get(0);
	}

	public static void saveOrUpdateCategory(DTOCategory cat) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(cat);
        transaction.commit();
		// TODO Auto-generated method stub
		
	}

}
