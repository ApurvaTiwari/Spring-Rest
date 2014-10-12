package org.spring.rest.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.rest.exception.NotFoundException;
import org.spring.rest.model.Person;
import org.springframework.transaction.annotation.Transactional;

public class HomeDAOImpl implements HomeDAO {
	private static final Logger logger = LoggerFactory.getLogger(HomeDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public Person getPersonById(int id) throws NotFoundException {
		logger.info(" >>>>> DAO IMPL . ID is {} ", id );
		Session session = this.sessionFactory.getCurrentSession();
		Person p = null;
		try{
		p = (Person) session.load(Person.class, new Integer(id));
		}catch(Exception e){
			logger.info(" :::::::::::::: THROWING NOT FOUND EXCEPTION ::::::::::::::");
			throw new NotFoundException(String.valueOf(id));
		}
		logger.info("Person loaded successfully, Person details="+p!=null?p.toString():null);
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

	@Override
	@Transactional
	public int deletePerson(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		logger.debug(" ----- Inside Delete Person --------- ID =====   {}" , id);
		Person person = (Person) session.get(Person.class, id);
		logger.info(" :::::  Person To be deleted is ::::  " + person.toString());
		session.delete(person);
		logger.info("--Deleted Successfully");
		return person.getId();
	}

	@Override
	@Transactional
	public int updatePerson(Person person) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(person);
		return person.getId();
	}

}
