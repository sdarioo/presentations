package com.example.domain;

import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	private String name;
	
	public Person() {
		this("Person: " + ThreadLocalRandom.current().nextInt());
	}
	
	public Person(String name) {
		this.name = name;
	}
}
