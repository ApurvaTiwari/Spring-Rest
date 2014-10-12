/**
 * 
 */
package org.spring.rest.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.rest.dao.HomeDAO;
import org.spring.rest.exception.NotFoundException;
import org.spring.rest.model.Person;
import org.springframework.stereotype.Service;

/**
 * @author Sheetal
 *
 */
@Service
public class HomeServiceImpl implements HomeService {

	Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);
	
	private HomeDAO homeDAO;
	
	public void setHomeDAO(HomeDAO homeDAO) {
		this.homeDAO = homeDAO;
	}	
	
	@Override
	@Transactional
	public Person getPersonById(int id) throws NotFoundException {
		logger.debug(" --- ID Is -- {}", id);
		return this.homeDAO.getPersonById(id);
	}

	@Override
	public int addPerson(Person person) {
		logger.debug(" -------- Adding Person ------- ");
		logger.debug(" ====  Input Is =====   {}", person.toString() );
		
		return this.homeDAO.addPerson(person);
	}

	@Override
	@Transactional
	public int deletePerson(int id) {
		logger.debug(" -------- Inside Delete . ID  {}",id);
		
		return this.homeDAO.deletePerson(id);
	}

	@Override
	public int updatePerson(Person person) {
		return this.homeDAO.updatePerson(person);
	}

}
