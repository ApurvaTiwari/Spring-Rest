/**
 * 
 */
package org.spring.rest.service;

import javax.transaction.Transactional;

import org.spring.rest.dao.HomeDAO;
import org.spring.rest.model.Person;
import org.springframework.stereotype.Service;

/**
 * @author Sheetal
 *
 */
@Service
public class HomeServiceImpl implements HomeService {

	private HomeDAO homeDAO;
	
	public void setHomeDAO(HomeDAO homeDAO) {
		this.homeDAO = homeDAO;
	}	
	
	@Override
	@Transactional
	public Person getPersonById(int id) {
		return this.homeDAO.getPersonById(id);
	}

}
