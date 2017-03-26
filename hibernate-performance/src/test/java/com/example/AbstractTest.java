package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class AbstractTest {
	
	@PersistenceContext
	protected EntityManager em;
	
	@Autowired
	protected TransactionTemplate txTemplate;
	
	protected AbstractTest() {
	}
	
	protected void setBatchSize(int count) {
		em.unwrap(Session.class).setJdbcBatchSize(count);
	}
	
	protected void doInJPA(Runnable method, String title) {
		time(() -> {
			txTemplate.execute(status -> {
				method.run();
				return null;
			});
		}, title);
	}
	
	protected long time(Runnable method, String title) {
		long startTime = System.currentTimeMillis();
		
		method.run();
		
		long result = System.currentTimeMillis() - startTime;
		System.out.println("TIME [" + title + "]: " + result);
		return result;
	}
	
	protected <T> List<T> create(Class<T> clazz, int count) throws Exception {
		List<T> result = new ArrayList<T>(count);
		for (int i = 0; i < count; i++) {
			result.add(newInstance(clazz, i));
		}
		return result;
	}
	
	protected <T> void createAndPersist(Class<T> clazz, int count, int batchSize, String title) throws Exception {
		List<T> data = create(clazz, count);
		doInJPA(() -> {
			setBatchSize(batchSize);
			
			for (int i = 0; i < count; i++) {
				em.persist(data.get(i));
				if ((i > 0) && (batchSize > 0) && ((i % batchSize) == 0)) {
					em.flush();
					em.clear();
				}
			}
		}, title);
	}
	
	protected <T> T newInstance(Class<T> clazz, int index) throws Exception {
		return clazz.newInstance();
	}
}
