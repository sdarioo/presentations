package com.example.demo;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;

import javax.persistence.EntityManager;

public final class TestUtil {
    private TestUtil() {}

    static void initDb(EntityManager em, int postCount, int postCommentCount) {
        for (int i = 0; i < postCount; i++) {
            Post post = new Post();
            em.persist(post);
            for (int j = 0; j < postCommentCount; j++) {
                Comment comment = new Comment();
                comment.setPost(post);
                em.persist(comment);
            }
        }
        em.flush();
        em.clear();
    }

}
