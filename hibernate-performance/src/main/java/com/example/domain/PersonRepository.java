package com.example.domain;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {


	public Collection<PersonProjection> findAllProjectedBy();
	
	public Collection<PersonDto> findAllDtoedBy();

}
