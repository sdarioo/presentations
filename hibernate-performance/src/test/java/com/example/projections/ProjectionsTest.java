package com.example.projections;

import javax.persistence.Query;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.AbstractTest;
import com.example.domain.Person;
import com.example.domain.PersonRepository;

public class ProjectionsTest extends AbstractTest {

	@Autowired
	private PersonRepository personRepository;
	
	private static int OBJ_COUNT = 50_000;
	
	
	@Test
	public void projectionsTest() throws Exception {
		setUp();
		
		try {
			findAll();
			
			findProjectedBy();
			
			findDtoedBy();
		} finally {
			tearDown();
		}
	}
	
	public void setUp() throws Exception {
		createAndPersist(Person.class, OBJ_COUNT, 50, null);
	}
	
	
	public void tearDown() {
		doInJPA(() -> {
			Query query = em.createQuery("Delete from Person");
			query.executeUpdate();
		}, null);
	}
	
	
	public void findAll() {
		time(personRepository::findAll, "Projections_WholeEntity");
	}
	
	
	public void findProjectedBy() {
		// WHY SLOW???
		time(personRepository::findAllProjectedBy, "Projections_Projection");
	}
	
	
	public void findDtoedBy() {
		time(personRepository::findAllDtoedBy, "Projections_Dto");
	}

}
