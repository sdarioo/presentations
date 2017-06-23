package com.example.demo;

import com.example.demo.domain.PostRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class LoggingTest extends AbstractTest {

    @Autowired
    private PostRepository repository;

    @Test
    @Transactional
    public void logSQL() {
        repository.findByTitle("title");
    }

    @Test
    @Transactional
    public void hibernateStats() {
        repository.findAll();
        repository.findOne(1);
    }

}
