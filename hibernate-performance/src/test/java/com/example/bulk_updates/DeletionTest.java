package com.example.bulk_updates;

import java.util.List;

import org.junit.Test;

import com.example.AbstractTest;
import com.example.domain.id_generation.Identity;

public class DeletionTest extends AbstractTest {

	private static int OBJ_COUNT = 1_000;
	
	@Test
	public void naiveDeletion() throws Exception {
		List<Identity> data = createAndPersist(Identity.class, OBJ_COUNT, 30, null);
		
		time(() -> {
			for (Identity obj : data) {
				doInJPA(() -> {
					Identity toDelete = em.merge(obj);
					em.remove(toDelete);
				}, null);	
			}
		}, "Delete_Naive");
	}
	
	@Test
	public void batchDeletion() throws Exception {
		List<Identity> data = createAndPersist(Identity.class, OBJ_COUNT, 30, null);
		
	}
	
	@Test
	public void bulkDeletion() throws Exception {
		List<Identity> data = createAndPersist(Identity.class, OBJ_COUNT, 30, null);
		
	}
}
