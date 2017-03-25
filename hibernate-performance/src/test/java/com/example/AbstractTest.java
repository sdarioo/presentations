package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractTest {
	
	@PersistenceContext
	protected EntityManager em;
	
	@Autowired
	protected TransactionTemplate txTemplate;
	
	protected AbstractTest() {
	}
	
	protected void setBatchSize(int count) {
		em.unwrap(Session.class).setJdbcBatchSize(10);
	}
	
	protected long doInJPA(Runnable method, String title) {
		AtomicLong time = new AtomicLong();
		txTemplate.execute(status -> {
			long startTime = System.currentTimeMillis();
			method.run();
			time.set(System.currentTimeMillis() - startTime);
			return null;
		});
		long result = time.get();
		System.out.println("TIME [" + title + "]: " + result);
		return result;
	}
	
	protected static <T> List<T> create(Class<T> clazz, int count) throws Exception {
		List<T> result = new ArrayList<T>(count);
		for (int i = 0; i < count; i++) {
			result.add(clazz.newInstance());
		}
		return result;
	}
	
	protected <T> void createAndPersist(Class<T> clazz, int count, int batchSize, String title) throws Exception {
		List<T> data = create(clazz, count);
		doInJPA(() -> {
			for (int i = 0; i < count; i++) {
				if ((i > 0) && (batchSize > 0) && ((i % batchSize) == 0)) {
					em.flush();
					em.clear();
				}
				em.persist(data.get(i));
			}
		}, title);
	}
}
