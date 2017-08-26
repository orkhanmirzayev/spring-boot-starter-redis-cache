package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.PersonRepository;
import com.example.demo.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;
	
	 
	@Override
	public Person createPerson(Person p) {
		return personRepository.save(p);
	}

	@Override
	public Person updatePerson(Person p) {
		Person pUpdate = personRepository.findById(p.getId());
		pUpdate.setAge(p.getAge());
		pUpdate.setName(p.getName());
		return personRepository.save(pUpdate);
	}

	@Override
	public Iterable<Person> findPeople() {
		// TODO Auto-generated method stub
		return personRepository.findAll();
	}

	@Override
	public Person findById(long id) {
		// TODO Auto-generated method stub
		return personRepository.findById(id);
	}

	@Override
	public void removePerson(long id) {
		// TODO Auto-generated method stub
		 personRepository.delete(id);
	}

}
