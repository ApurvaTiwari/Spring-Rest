package org.spring.rest.dao;

import org.spring.rest.exception.NotFoundException;
import org.spring.rest.model.Person;

public interface HomeDAO {
	Person getPersonById(int id) throws NotFoundException;
	public int addPerson(Person person);
	public int deletePerson(int id);
	public int updatePerson(Person person);
	
}
