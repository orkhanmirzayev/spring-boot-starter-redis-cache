package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.dao.PersonRepository;
import com.example.demo.model.Person;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class SpringBootStarterRedisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterRedisApplication.class, args);
	}
	
	@Scheduled(fixedDelay = 60*1000)
	@CacheEvict(allEntries=true,cacheNames="person")
	public void reportCacheEvict() {
	    System.out.println("Flush Cache " + new SimpleDateFormat().format(new Date()));
	    
	}
	@Autowired
	PersonRepository repo;
	
	@Override
	public void run(String... arg0) throws Exception {
		for(int i = 0; i <1000; i++){
		Person p1 = new Person("Orkhan",29);
		Person p2 = new Person("Aysel",30);
		Person p3 = new Person("Adile",52);
		Person p4 = new Person("Atilla",62);
		Person p5 = new Person("Hayat",26);
		Person p6 = new Person("Sarp",31);
		Person p7 = new Person("Saban",33);
		Person p8 = new Person("Konul",28);
		List<Person> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p7);
		list.add(p8);
		repo.save(list);
		}
	}
}
