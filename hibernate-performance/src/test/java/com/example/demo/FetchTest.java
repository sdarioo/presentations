package com.example.demo;

import com.example.demo.domain.Comment;
import com.example.demo.domain.CommentDetails;
import com.example.demo.domain.Post;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;

public class FetchTest extends AbstractTest {

    @Before
    public void setUp() {
        TestUtil.initDb(em, 1, 2);
    }

    @After
    public void tearDown() {
        deleteAll();
    }

    @Test
    @Transactional
    public void fetchStrategies() {

        //Post post = em.find(Post.class, 10);
        //List<Comment> comments = post.getComments();


//        Query query = em.createQuery("SELECT p FROM Post p LEFT JOIN FETCH p.comments WHERE p.id = 10");
//        Post post = (Post)query.getSingleResult();
//        List<Comment> comments = post.getComments();


//        // Eager JOIN
//        Comment comment = em.find(Comment.class, 30);
//        assertNotNull(comment);
//
//        em.clear();
//
//        // Eager SELECT
//        comment = (Comment)em.createQuery("SELECT c from Comment c WHERE c.id = 30").getSingleResult();
//        assertNotNull(comment);


        em.createQuery("SELECT c from Comment c JOIN FETCH c.post");


    }


    private void deleteAll() {
        deleteAll(Comment.class);
        deleteAll(Post.class);
        deleteAll(CommentDetails.class);
    }
}
