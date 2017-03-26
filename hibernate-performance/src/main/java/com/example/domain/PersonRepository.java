package com.example.domain;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {

	//@Query("select new com.example.domain.IdName(p.id, p.name) from Person p")
	public Collection<PersonProjection> findAllProjectedBy();
	
	public Collection<PersonDto> findAllDtoedBy();

}
