package org.spring.rest.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.rest.model.Person;
import org.springframework.transaction.annotation.Transactional;

public class HomeDAOImpl implements HomeDAO {
	private static final Logger logger = LoggerFactory.getLogger(HomeDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public Person getPersonById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Person p = (Person) session.load(Person.class, new Integer(id));
		logger.info("Person loaded successfully, Person details="+p.toString());
		return p;
	}

	@Override
	@Transactional
	public int addPerson(Person person) {
		Session session = this.sessionFactory.getCurrentSession();
		int id =  (Integer) session.save(person);
		logger.info(" :: Person ID is :: {}", id);
		return id;
	}

}
