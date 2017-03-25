package com.example.one_to_one;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.AbstractTest;
import com.example.domain.one_to_one.Child;
import com.example.domain.one_to_one.Parent;
import com.example.domain.one_to_one.ParentRepository;

public class TestOneToOne extends AbstractTest {

	@Autowired
	private ParentRepository repository;
	
	@Test
	public void showNPlus1Problem() throws Exception {
		
		doInJPA(() -> {
			for (int i = 0; i < 1000; i++) {
				Parent parent = new Parent();
				Child child = new Child();
				child.setParent(parent);
				
				em.persist(parent);
				em.persist(child);
			}
		}, null);
		
		Iterable<Parent> all = repository.findAll();
		
	}
}
