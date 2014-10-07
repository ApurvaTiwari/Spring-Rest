package org.spring.rest;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.rest.model.Person;
import org.spring.rest.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/person")
public class HomeController {
	


	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private HomeService homeService;

	/**
	 * @param homeService the homeService to set
	 */
	@Autowired
	@Qualifier(value="homeService")
	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@RequestMapping(value = "/{personId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> getPersonDetails(@PathVariable int personId) throws JsonGenerationException, JsonMappingException, IOException {
		logger.info("--------------- Inside Get Person ------------ The person ID  is {}.", personId);

		Person p = this.homeService.getPersonById(personId);

		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		
		String formattedDate = dateFormat.format(date);
		
		Map<String, Object> treeMap = new  TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
		
		treeMap.put("UserId",  personId);
		treeMap.put("Name", p.getName());
		treeMap.put("country", p.getCountry());
		treeMap.put("timeOfQuerying", formattedDate);
		
		ObjectMapper jsonMapper = new ObjectMapper();	
		
		String strResponse = jsonMapper.writeValueAsString(treeMap);
		
		return new ResponseEntity<String>(strResponse, getHeaders(), HttpStatus.OK);
	}
	
	public  MultiValueMap<String, String> getHeaders(){
		 MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		 map.add("Hello", "sheetal");
		 return map;
	}
}
