package com.example.domain;

import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class ImmutablePerson extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	private String name;
	
	public ImmutablePerson() {
		this("Person: " + ThreadLocalRandom.current().nextInt());
	}
	
	public ImmutablePerson(String name) {
		this.name = name;
	}
}
