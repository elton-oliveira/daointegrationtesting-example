package br.com.fluentcode.daointegrationtesting.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.fluentcode.daointegrationtesting.entity.Document;

public class DocumentDAO {
	
	private final DAO<Document> dao;
	
	private final Session session;
	
	public DocumentDAO(Session session) {
		dao = new DAO<Document>(session, Document.class);
		this.session = session;
	}
	
	public void insert(Document document){
		this.dao.insert(document);
	}

	@SuppressWarnings("unchecked")
	public List<Document> findByType(String type){
		Query query = session.createQuery("SELECT d FROM Document d WHERE d.type = :type");
		query.setParameter("type", type);
		return query.list();
	}

}
