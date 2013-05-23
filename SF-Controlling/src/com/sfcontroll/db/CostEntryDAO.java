package com.sfcontroll.db;


import java.util.List;

import javafx.scene.control.TreeItem;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.sfcontroll.business.Entry;

public class CostEntryDAO
{
	public static List<DTOCostsEntry> getAllEntriesFromDB(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction =  session.beginTransaction();
		Criteria criteria = session.createCriteria(DTOCostsEntry.class);
		transaction.commit();
		return criteria.list();
	}
        
        public static void saveOrUpdateEntry(Entry newEntry)
        {               
            Session session = HibernateUtil.getSessionFactory().openSession();
            DTOCostsEntry costEntry = new DTOCostsEntry();
            Transaction tx = session.beginTransaction();
            costEntry.setName(newEntry.getName());
            costEntry.setCategory(newEntry.getCategory());
            costEntry.setDate(newEntry.getDate());
            session.saveOrUpdate(costEntry);
            tx.commit();
        }

		public static DTOCostsEntry getEntryByPossibleIdAndName(int id,
				String selectedName) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction =  session.beginTransaction();
			DTOCostsEntry dtoEntry = (DTOCostsEntry) session.get(DTOCostsEntry.class, id+1);
			transaction.commit();
			if(dtoEntry.getName().equals(selectedName)){
				return dtoEntry;
			}
			else{
				return getEntryByPossibleIdAndName(id++, selectedName);
			}
		}

		public static List<DTOCostsEntry> getEntriesByName(String name) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction =  session.beginTransaction();
			Criteria criteria = session.createCriteria(DTOCostsEntry.class)
					.add(Restrictions.disjunction()
					.add(Restrictions.like("name", name+"%"))
					.add(Restrictions.like("name", "%"+name))
					.add(Restrictions.like("name", "%"+name+"%")));
			
			transaction.commit();
			return criteria.list();
		}

		public static int getEntrieID(Entry entry) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction =  session.beginTransaction();
			Criteria criteria = session.createCriteria(DTOCostsEntry.class)
					.add(Restrictions.conjunction()
					.add(Restrictions.eq("name",entry.getName()))
					.add(Restrictions.like("category",entry.getCategory()))
					.add(Restrictions.like("date", entry.getDate())));
			
			transaction.commit();
			return ((DTOCostsEntry) criteria.list().get(0)).getId();
		}
}