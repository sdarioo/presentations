package com.example.projections;

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
	public void testProjections() throws Exception {
	
		createAndPersist(Person.class, OBJ_COUNT, 50, null);
		
		time(personRepository::findAll, "Projections_WholeEntity");
		
		// WHY SLOW???
		time(personRepository::findAllProjectedBy, "Projections_Projection");
		
		time(personRepository::findAllDtoedBy, "Projections_Dto");
	}
	
	
	@Override
	protected <T> T newInstance(Class<T> clazz, int index) throws Exception {
		if (Person.class.equals(clazz)) {
			return (T)new Person("Name: " + index);
		}
		return super.newInstance(clazz, index);
	}
}
