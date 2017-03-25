package com.example.id_generation;

import org.junit.Test;

import com.example.AbstractTest;
import com.example.domain.id_generation.HiloSequence;
import com.example.domain.id_generation.Identity;
import com.example.domain.id_generation.Sequence;
import com.example.domain.id_generation.Table;

public class IdGenerationTypeTest extends AbstractTest {
	
	private static int OBJ_COUNT = 10_000;
	
	@Test
	public void testSequence() throws Exception {
		createAndPersist(Sequence.class, OBJ_COUNT, -1, "Sequence");
	}
	
	@Test
	public void testHiLoSequence() throws Exception {
		createAndPersist(HiloSequence.class, OBJ_COUNT, -1, "HiLo Sequence (inc. size = 10)");
	}
	
	@Test
	public void testIdentity() throws Exception {
		createAndPersist(Identity.class, OBJ_COUNT, -1, "Identity");
	}
	
	@Test
	public void testTable() throws Exception {
		createAndPersist(Table.class, OBJ_COUNT, -1, "Table");
	}
		
}
