package com.example.demo.dao;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.*;


public interface PersonRepository extends CrudRepository<Person,Long> {

	Person findById(long id);
}
