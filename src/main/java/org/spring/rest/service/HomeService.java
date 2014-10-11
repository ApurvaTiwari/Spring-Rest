/**
 * 
 */
package org.spring.rest.service;

import org.spring.rest.model.Person;

/**
 * @author Sheetal
 *
 */
public interface HomeService {
	public Person getPersonById(int id);
	public int addPerson(Person person);
}
