package com.example.demo;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.TypedQuery;

@SpringBootTest(properties = {
        "logging.level.org.hibernate.SQL=ERROR",
        "spring.jpa.properties.hibernate.generate_statistics=false"})
public class FetchTest extends AbstractTest {

    @AfterClass
    public static void printStats() {
        AbstractTest.printStatistics();
    }

    @Test
    public void testAll() {

        doInJPA(() -> {
            TestUtil.initDb(em, 100_000, 1);
        }, "Init");

        doInJPA(() -> {
            TypedQuery<Comment> q = em.createQuery("select c from Comment c join fetch c.post", Comment.class);
            q.getResultList();
        }, "Select with JOIN FETCH");

        doInJPA(() -> {
            TypedQuery<Comment> q = em.createQuery("select c from Comment c", Comment.class);
            q.getResultList();
        }, "Select with N+1 Problem");

        doInJPA(() -> {
            TypedQuery<Post> q = em.createQuery("select p from Post p", Post.class);
            q.getResultList();
        }, "Select LAZY");

        deleteAll();
    }

    private void deleteAll() {
        deleteAll(Comment.class);
        deleteAll(Post.class);
    }
}
