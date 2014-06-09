package br.com.fluentcode.daointegrationtesting.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.fluentcode.daointegrationtesting.entity.Document;
import br.com.fluentcode.daointegrationtesting.infra.HibernateUtil;

public class DocumentDAOTest {

	private Session session;
	private DocumentDAO dao;

	@Before
	public void begin() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
		dao = new DocumentDAO(session);
		session.beginTransaction();
	}

	@After
	public void end() {
		// Rollback transaction is a good practice
		session.getTransaction().rollback();
		session.close();
	}

	@Test
	public void shouldFindDocumentByType() {
		// Prepares the scenario
		Document document = new Document();
		document.setType("Proposal");
		dao.insert(document);

		// Find by type
		List<Document> proposalList = dao.findByType("Proposal");
		assertThat(proposalList.size(), equalTo(1));
		assertThat(proposalList.get(0).getType(), equalTo("Proposal"));
	}

}
