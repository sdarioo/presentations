package com.example.projections.transactions;



import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.AbstractTest;
import com.example.domain.ImmutablePerson;
import com.example.domain.ImmutablePersonRepository;
import com.example.domain.Person;
import com.example.domain.PersonRepository;

public class TransactionsTest extends AbstractTest {
	
	private static int OBJ_COUNT = 100_000;
	
	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private ImmutablePersonRepository immutableRepository;
	
	@Test
	public void testTransactions() throws Exception {
		
		createAndPersist(Person.class, OBJ_COUNT, 50, null);
		try {
			selectInDefaultTransaction();
			
			selectInReadOnlyTransaction();
		} finally {
			deleteAll(Person.class);
		}
	}
	
	@Test
	public void testImmutableSelect() throws Exception {
		createAndPersist(ImmutablePerson.class, OBJ_COUNT, 50, null);
		
		time(immutableRepository::findAll, "Select_Immutable");
		
		deleteAll(ImmutablePerson.class);
	}
	
	@Transactional
	private void selectInDefaultTransaction() {
		time(repository::findAll, "Select_DefaultTransaction");
	}
	
	@Transactional(readOnly=true)
	private void selectInReadOnlyTransaction() {
		time(repository::findAll, "Select_ReadOnlyTransaction");
	}
}
