package org.spring.rest.dao;

import org.spring.rest.model.Person;

public interface HomeDAO {
	Person getPersonById(int id);
	public int addPerson(Person person);
}
