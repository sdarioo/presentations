package com.example.demo;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest(properties = {
        "logging.level.org.hibernate.SQL=ERROR",
        "spring.jpa.properties.hibernate.generate_statistics=false"})
public class BulkUpdateTest extends AbstractTest {

    private final int POST_COUNT = 100_000;
    private final int POST_COMMENT_COUNT = 0;

    @Autowired
    private PostRepository repository;

    @AfterClass
    public static void printStats() {
        printStatistics();
    }

    @Test
    public void bulkTest() {

        doInJPA(() -> {
            TestUtil.initDb(em, POST_COUNT, POST_COMMENT_COUNT);
        }, null);

        doInJPA(() -> {
            setBatchSize(50);

            List<Post> posts = repository.findByStatus(1);
            assertEquals(POST_COUNT, posts.size());
            posts.forEach(post -> post.setStatus(2));
        }, "NaiveUpdate");

        doInJPA(() -> {
            int count = em.createQuery("update Post set status = 1 where status = 2").executeUpdate();
            assertEquals(POST_COUNT, count);
        }, "BulkUpdate");

        doInJPA(() -> {
            setBatchSize(50);

            List<Post> posts = repository.findByStatus(1);
            assertEquals(POST_COUNT, posts.size());
            posts.forEach(repository::delete);
        }, "NaiveDelete");
    }


    @Test
    public void bulkDelete() {

        doInJPA(() -> {
            TestUtil.initDb(em, POST_COUNT, POST_COMMENT_COUNT);
        }, null);

        doInJPA(() -> {
            int count = em.createQuery("delete Post where status = 1").executeUpdate();
            assertEquals(POST_COUNT, count);
        }, "BulkDelete");
    }

}
