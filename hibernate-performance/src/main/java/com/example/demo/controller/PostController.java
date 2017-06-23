package com.example.demo.controller;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostRepository repository;

    @PersistenceContext
    private EntityManager em;

    @PostMapping(path="/post", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(Post post) {
        repository.save(post);
    }

    @PostMapping(path="/posts", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public void addAll(@RequestBody List<Post> posts) {
        em.unwrap(Session.class).setJdbcBatchSize(posts.size());
        repository.save(posts);
    }
}
