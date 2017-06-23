package com.example.demo;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostRepository;
import net.ttddyy.dsproxy.QueryCountHolder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest(properties = {
		"logging.level.org.hibernate.SQL=ERROR",
		"spring.jpa.properties.hibernate.generate_statistics=false"})
public class InsertTest extends AbstractTest {

	// Doc: https://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/chapters/batch/Batching.html
	
	private static int OBJ_COUNT = 100_000;

	@Autowired
	private PostRepository postRepository;

	@AfterClass
	public static void printStats() {
		printStatistics();
	}

	@After
	public void cleanUp() {
		deleteAll(Post.class);
	}

	// Each operation in single transaction
	@Test
	public void testNaiveAdding1() throws Exception {

		QueryCountHolder.clear();

		time(() -> {
			for (int i = 0; i < OBJ_COUNT; i++) {
				Post post = new Post();
				postRepository.save(post);
			}
		}, "Naive Add 1");

		assertEquals(OBJ_COUNT, QueryCountHolder.getGrandTotal().getInsert());
	}

	// Single long-running transactions
	// all instances cached in the session-level c1ache (OutOfMemoryError)
	// JDBC presentation is not enabled by default, so every insert statement requires a database round trip
	@Test
	public void testNaiveAdding2() {

		QueryCountHolder.clear();

		doInJPA(() -> {
			for (int i = 0; i < OBJ_COUNT; i++) {
				Post post = new Post();
				em.persist(post);
			}
		}, "Naive Add 2");

		assertEquals(OBJ_COUNT, QueryCountHolder.getGrandTotal().getInsert());
	}

	@Test
	public void testBatchAdding() {
		QueryCountHolder.clear();
		final int batchSize = 50;
		doInJPA(() -> {
			setBatchSize(batchSize);
			for (int i = 0; i < OBJ_COUNT; i++) {
				Post post = new Post();
				em.persist(post);
				//flush a batch of inserts and release memory
				if ((i > 0) && (i % batchSize == 0)) {
					em.flush();
					em.clear();
				}
			}
		}, "Batch Add");


		assertEquals((OBJ_COUNT / batchSize) + 1, QueryCountHolder.getGrandTotal().getInsert());
	}



//	@Test
//	public void testHiLoSequence_batch_10() throws Exception {
//		createAndPersist(HiloSequence.class, OBJ_COUNT, 10, "Sequence (batch_size=10)");
//	}
//
//	@Test
//	public void testHiLoSequence_batch_30() throws Exception {
//		createAndPersist(HiloSequence.class, OBJ_COUNT, 30, "Sequence (batch_size=30)");
//	}

//	@Test
//	public void testHiLoSequence_batch_50() throws Exception {
//		createAndPersist(Sequence.class, OBJ_COUNT, 50, "Sequence (batch_size=50)");
//	}
//
//	@Test
//	public void testHiLoSequence_batch_100() throws Exception {
//		createAndPersist(HiloSequence.class, OBJ_COUNT, 100, "Sequence (batch_size=100)");
//	}
//
//	@Test
//	public void testIdentity_batch_100() throws Exception {
//		createAndPersist(Identity.class, OBJ_COUNT, 50, "Identity (batch_size=50)");
//	}
	
}
