package br.com.fluentcode.daointegrationtesting.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class DAO<T>{
	
	private final Session session;
	private final Class<T> classe;
	
	public DAO(Session session, Class<T> classe){
		this.session = session;
		this.classe = classe;
	}
	
	@SuppressWarnings("unchecked")
	public T findById(Serializable id){
		return (T) session.get(classe, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		Query query = session.createQuery("SELECT e FROM "+classe.getName()+" e");
		return query.list();
	}
	
	public void insert(T t){
		session.persist(t);
	}
	
	public void delete(T t){
		session.delete(t);
	}
	

}
