package com.skillstorm.data;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.skillstorm.beans.Expenses;
import com.skillstorm.beans.ReimbStatus;

public class DAO {

	Session session;

	public DAO(Session session) {
		this.session = session;
	}

	public List<Expenses> getAll() {
		String hql = "from com.skillstorm.beans.Expenses"; // grab all columns
		Query query = session.createQuery(hql);
		return query.list();
	}

	public Expenses save(Expenses expense) {
		Transaction tx = session.beginTransaction();
		session.save(expense);
		tx.commit();
		return expense;
	}

	public void approve(int id, String notes) {
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Expenses.class);
		criteria.add(Restrictions.like("id", id));
		
		Expenses expense = (Expenses) criteria.uniqueResult();
		ReimbStatus status = new ReimbStatus();
		status.setStatusId(2);
		status.setStatus("Approved");
		
		expense.setStatus(status);
		expense.setNotes(notes);
		session.update(expense);
		tx.commit();
	}

	public void deny(int id, String notes) {
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Expenses.class);
		criteria.add(Restrictions.like("id", id));
		
		Expenses expense = (Expenses) criteria.uniqueResult();
		ReimbStatus status = new ReimbStatus();
		status.setStatusId(3);
		status.setStatus("Denied");
		expense.setStatus(status);
		
		expense.setNotes(notes);
		session.update(expense);
		tx.commit();
	}

	public void delete(int id) {
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Expenses.class);
		criteria.add(Restrictions.like("id", id));
		session.delete(criteria.uniqueResult());
		tx.commit();
	}

}