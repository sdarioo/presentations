package com.example.demo;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;


public class BulkUpdateTest extends AbstractTest {

    private final int POST_COUNT = 100;
    private final int POST_COMMENT_COUNT = 0;

    @Autowired
    private PostRepository repository;


    @Before
    public void setUp() {
        TestUtil.initDb(em, POST_COUNT, POST_COMMENT_COUNT);
    }

    @After
    public void tearDown() {
        deleteAll(Post.class);
    }

    @Test
    public void buldUpdate() {
        em.createQuery("update Post set status = 2 where status = 1");
    }

    @Test

    @Transactional
    public void naiveUpdate() {
        List<Post> posts = repository.findByStatus(1);
        posts.forEach(post -> post.setStatus(2));
    }

    @Test
    public void bulkDelete() {
        em.createQuery("delete Post where status = 2");

    }

}
