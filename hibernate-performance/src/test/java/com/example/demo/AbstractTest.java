package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	private static final Map<String, Stat> STATS = new HashMap<>();

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
		long startMem = title != null ? getUsedMemory() : 0L;
		
		method.run();

		long time = System.currentTimeMillis() - startTime;
		if (title != null) {

			long mem = getUsedMemory() - startMem;
			STATS.put(title, new Stat(time, mem));
		}
		return time;
	}
	
	protected <T> List<T> create(Class<T> clazz, int count) throws Exception {
		List<T> result = new ArrayList<T>(count);
		for (int i = 0; i < count; i++) {
			result.add(newInstance(clazz, i));
		}
		return result;
	}
	
	protected <T> List<T> createAndPersist(Class<T> clazz, int count, int batchSize, String title) throws Exception {
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
		
		return data;
	}
	
	protected void deleteAll(Class<?> clazz) {
		doInJPA(() -> {
			Query query = em.createQuery("Delete from " + clazz.getSimpleName());
			query.executeUpdate();
		}, null);
	}
	
	protected <T> T newInstance(Class<T> clazz, int index) throws Exception {
		return clazz.newInstance();
	}

	protected static void printStatistics() {
		STATS.forEach((k, v) -> {
			System.out.println("TIME [" + k + "]: " + v);
		});
	}

	private static long getUsedMemory() {
		Runtime.getRuntime().gc();
		Runtime.getRuntime().gc();
		Runtime.getRuntime().gc();
		return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}

	private static class Stat {
		long time;
		long mem;

		public Stat(long time, long mem) {
			this.time = time;
			this.mem = mem;
		}

		@Override
		public String toString() {
			return "Stat{" +
					"time=" + time +
					", mem=" + mem +
					'}';
		}
	}
}
