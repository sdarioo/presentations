package com.example.demo;

import com.example.demo.domain.IdTitle;
import com.example.demo.domain.Post;
import com.example.demo.domain.PostRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest(properties = {
		"logging.level.org.hibernate.SQL=ERROR",
		"spring.jpa.properties.hibernate.generate_statistics=false"})
public class ProjectionsTest extends AbstractTest {

	@Autowired
	private PostRepository postRepository;
	
	private static int OBJ_COUNT = 100_000;
	
	
	@Test
	public void projectionsTest() throws Exception {
		setUp();
		
		try {
			findAll();
			
			findProjectedBy();
			
			findDtoedBy();

			findProjectionWithQuery();
		} finally {
			tearDown();
			printStatistics();
		}
	}
	
	public void setUp() throws Exception {
		createAndPersist(Post.class, OBJ_COUNT, 50, "SetUp");
	}
	public void tearDown() {
		deleteAll(Post.class);
	}

	public void findAll() {
		time(postRepository::findAll, "Projections_WholeEntity");
	}

	public void findProjectedBy() {
		// WHY SLOW???
		time(postRepository::findAllProjectedBy, "Projections_Projection");
	}

	public void findDtoedBy() {
		time(postRepository::findAllDtoedBy, "Projections_Dto");
	}

	public void findProjectionWithQuery() {
		Runnable r = () -> {
			TypedQuery<IdTitle> query = em.createQuery("select new com.example.demo.domain.IdTitle(p.id, p.title) from Post p", IdTitle.class);
			List<IdTitle> result = query.getResultList();
			assertEquals(OBJ_COUNT, result.size());
		};
		time(r, "ProjectionsWithQuery");

	}

}
