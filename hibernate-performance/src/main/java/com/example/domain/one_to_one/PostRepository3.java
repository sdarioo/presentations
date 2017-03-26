package com.example.domain.one_to_one;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository3 extends CrudRepository<Post3, Integer> {
	
	@Query("SELECT p FROM Post3 p JOIN FETCH p.details")
	public List<Post3> findAllWithDetails();
}
