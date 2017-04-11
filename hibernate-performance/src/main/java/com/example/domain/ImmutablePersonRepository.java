package com.example.domain;

import org.springframework.data.repository.CrudRepository;

public interface ImmutablePersonRepository extends CrudRepository<ImmutablePerson, Integer> {
	
	
}
