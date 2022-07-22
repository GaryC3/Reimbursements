package com.skillstorm.data;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.skillstorm.beans.Expenses;

public class DAO {
	
	Session session;
	
	public DAO(Session session) {
		this.session = session;
	}
	
	public List<Expenses> getAll(){
		// HQL - Hibernate Query Language (class, variables) not tables/columns
		String hql = "from com.skillstorm.beans.Expenses"; // grab all columns ( you can get specific columns OR a single result )
		Query query = session.createQuery(hql);
		return query.list();
		// return session.createQuery("from Artist").list();
	}

	// after the save, the artist has a generated Id  (HQL + "userinput")
	public Expenses save(Expenses expense) {
		Transaction tx = session.beginTransaction();
		session.save(expense);
		tx.commit();
		return expense; // (SQLException) (rethrow as RuntimeException)
	}
	
	public void approve(Expenses expense) {
		Transaction tx = session.beginTransaction();
		session.update(expense);
		tx.commit(); // (SQLException) (rethrow as RuntimeException)
	}
	
	public void deny(Expenses expense) {
		Transaction tx = session.beginTransaction();
		session.update(expense);
		tx.commit(); // (SQLException) (rethrow as RuntimeException)
	}
	
	public void delete(Expenses expense) {
		Transaction tx = session.beginTransaction();
		session.delete(expense);
		tx.commit(); // (SQLException) (rethrow as RuntimeException)
	}
	
}