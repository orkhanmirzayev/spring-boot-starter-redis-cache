package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

@RestController
public class PersonController {

	private static Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	PersonService service;
	
	@GetMapping(value="/find-all")
	public Iterable<Person> findAll(){
		
		return service.findPeople();
	}
	@GetMapping(value="/find-user/{id}")
	@Cacheable(cacheNames="person",key="#id")
	public Person findUser(@PathVariable("id") Long id){
		long startTime = System.currentTimeMillis();    
		logger.error("FindById("+id+") will be invoked");
		Person p = service.findById(id);
		logger.error("FindById("+id+") invoked");
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.err.println("Time elapsed: "+estimatedTime);
		return p;
	}
	@PostMapping(value="/create-user")
	@CachePut(cacheNames="person",key="#result.id")
	public Person saveUser(@RequestBody Person p){
		long startTime = System.currentTimeMillis();    
		logger.error("saveUser() will be invoked");
		Person p1 = service.createPerson(p);
		logger.error("saveUser() invoked");
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.err.println("Time elapsed: "+estimatedTime);
		return p1;
	}
	@DeleteMapping(value="/create-user")
	@CacheEvict(cacheNames="person",key="#id")
	public void deleteUser(@PathVariable("id") Long id){
		long startTime = System.currentTimeMillis();    
		logger.error("deleteUser("+id+") will be invoked");
		service.removePerson(id);
		logger.error("deleteUser("+id+") invoked");
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.err.println("Time elapsed: "+estimatedTime);
		
	}
//	@GetMapping(value="/find-all")
//	public ResponseEntity<Iterable<Person>> findAll(){
//		
//		return new ResponseEntity<Iterable<Person>>(r.findAll(), HttpStatus.OK);
//	}
}
