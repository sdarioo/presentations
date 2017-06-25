package com.example.demo;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import net.ttddyy.dsproxy.QueryCountHolder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Query;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

public class ProxyTest extends AbstractTest {

    private final int POST_COUNT = 10;
    private final int POST_COMMENT_COUNT = 10;

    @Before
    public void setUp() {
        TestUtil.initDb(em, POST_COUNT, POST_COMMENT_COUNT);
    }

    @After
    public void tearDown() {
        deleteAll();
    }

    @Transactional
    @Test
    public void validateQueryCount() {
        QueryCountHolder.clear();

        Query query = em.createQuery("SELECT c FROM Comment c");
        //Query query = em.createQuery("SELECT c FROM Comment c LEFT JOIN FETCH c.post LEFT JOIN FETCH c.details");
        query.getResultList();

        assertEquals(1, QueryCountHolder.getGrandTotal().getSelect());
    }

    private void deleteAll() {
        deleteAll(Comment.class);
        deleteAll(Post.class);
    }
}
