package com.example.demo;


import com.example.demo.domain.Post;
import com.example.demo.domain.PostRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(properties = {
		"logging.level.org.hibernate.SQL=ERROR",
		"spring.jpa.properties.hibernate.generate_statistics=false"})
public class TransactionsTest extends AbstractTest {
	
	private static int OBJ_COUNT = 100_000;
	
	@Autowired
	private PostRepository repository;


	@Before
	public void setUp() throws Exception {
		createAndPersist(Post.class, OBJ_COUNT, 50, "Create_Post");
	}

	@After
	public void tearDown() {
		deleteAll(Post.class);
	}

	@Test
	public void testTransactions() throws Exception {
		try {
			selectInDefaultTransaction();
			
			selectInReadOnlyTransaction();

		} finally {
			printStatistics();
		}
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
