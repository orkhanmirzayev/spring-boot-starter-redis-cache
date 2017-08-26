package com.example.demo.service;

import com.example.demo.model.Person;

public interface PersonService {

	Person createPerson(Person p);
	Person updatePerson(Person p);
	Iterable<Person> findPeople();
	Person findById(long id);
	void removePerson(long id);
}
