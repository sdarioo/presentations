package com.example.batching;

import org.junit.Test;

import com.example.AbstractTest;
import com.example.domain.id_generation.HiloSequence;
import com.example.domain.id_generation.Identity;

public class CreationTest extends AbstractTest {

	// Doc: https://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/chapters/batch/Batching.html
	
	private static int OBJ_COUNT = 10_000;
	
	@Test
	public void testNaiveAdding() throws Exception {
		time(() -> {
			for (int i = 0; i < OBJ_COUNT; i++) {
				doInJPA(() -> {
					em.persist(new Identity());
				}, null);
				
			}
		}, "Naive Way");
	}
	
	@Test
	public void testHiLoSequence_batch_10() throws Exception {
		createAndPersist(HiloSequence.class, OBJ_COUNT, 10, "Sequence (batch_size=10)");
	}
	
	@Test
	public void testHiLoSequence_batch_30() throws Exception {
		createAndPersist(HiloSequence.class, OBJ_COUNT, 30, "Sequence (batch_size=30)");
	}
	
	@Test
	public void testHiLoSequence_batch_50() throws Exception {
		createAndPersist(HiloSequence.class, OBJ_COUNT, 50, "Sequence (batch_size=50)");
	}
	
	@Test
	public void testHiLoSequence_batch_100() throws Exception {
		createAndPersist(HiloSequence.class, OBJ_COUNT, 100, "Sequence (batch_size=100)");
	}
	
	@Test
	public void testIdentity_batch_100() throws Exception {
		createAndPersist(Identity.class, OBJ_COUNT, 50, "Identity (batch_size=50)");
	}
	
}
