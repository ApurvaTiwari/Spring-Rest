package org.spring.rest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javassist.NotFoundException;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.rest.exception.ExceptionData;
import org.spring.rest.exception.ExceptionDetails;
import org.spring.rest.model.Person;
import org.spring.rest.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public @ResponseBody ResponseEntity<Person> getPersonDetails(@PathVariable int personId) throws org.spring.rest.exception.NotFoundException,JsonGenerationException, JsonMappingException, IOException {
		logger.info("--------------- Inside Get Person ------------ The person ID  is {}.", personId);

		Person person = this.homeService.getPersonById(personId);	
		return new ResponseEntity<Person>(person, getHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="",method = RequestMethod.POST )
	public ResponseEntity<Void> addPerson(@RequestBody Person person){
		int id= this.homeService.addPerson(person);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(linkTo(HomeController.class).slash(id).toUri());
		return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="", method = RequestMethod.PUT)
	public ResponseEntity<Void> updatePerson(@RequestBody Person person){
		logger.info(" >>>>>>>>> Update Person Start . Person is =  {} "+ person.toString());
		int id = this.homeService.updatePerson(person);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(linkTo(HomeController.class).slash(id).toUri());
		return new ResponseEntity<Void>(httpHeaders,HttpStatus.NO_CONTENT);
}
	
	@RequestMapping(value="/{personId}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePerson(@PathVariable int personId){
		int id = this.homeService.deletePerson(personId);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(linkTo(HomeController.class).slash(id).toUri());
		return new ResponseEntity<Void>(httpHeaders, HttpStatus.NO_CONTENT);
	}
	
	public  MultiValueMap<String, String> getHeaders(){
		 MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		 map.add("Hello", "sheetal-Kutte");
		 return map;
	}
	
	@ExceptionHandler(org.spring.rest.exception.NotFoundException.class)
	public ResponseEntity<String> resourceNotFoundExceptionHandler(Exception e){
		return new ResponseEntity<String>(e.getMessage(),getHeaders(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionData> genericExceptionHandler(Exception e){
		List<String> listOfCauses = new ArrayList<String>();
		listOfCauses.add("DUDE!!!!!! WHY DID YOU DO THAT!");
		ExceptionDetails exceptionDetails = new ExceptionDetails(HttpStatus.INTERNAL_SERVER_ERROR, "Request Could Not Be Completed", 
				listOfCauses);
		Date date= new Date(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("dd:MMM:yy:HH:mm:ss");
		String dt = null;
		dt  = df.format(date);
		ExceptionData exceptionData = new ExceptionData(exceptionDetails, dt);
		return new ResponseEntity<ExceptionData>(exceptionData,getHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
