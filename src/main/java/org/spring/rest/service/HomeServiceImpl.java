/**
 * 
 */
package org.spring.rest.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.rest.dao.HomeDAO;
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
	public Person getPersonById(int id) {
		logger.debug(" --- ID Is -- {}", id);
		return this.homeDAO.getPersonById(id);
	}

}
