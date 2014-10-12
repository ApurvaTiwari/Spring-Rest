/**
 * 
 */
package org.spring.rest.service;

import org.spring.rest.exception.NotFoundException;
import org.spring.rest.model.Person;

/**
 * @author Sheetal
 *
 */
public interface HomeService {
	public Person getPersonById(int id) throws NotFoundException;
	public int addPerson(Person person);
	public int deletePerson(int id);
	public int updatePerson(Person person);
}
