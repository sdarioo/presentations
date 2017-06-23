package com.example.demo;


import com.example.demo.domain.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"logging.level.org.hibernate.SQL=ERROR",
                              "spring.jpa.properties.hibernate.generate_statistics=false"},
        webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerTest {

    private static int OBJ_COUNT = 100_000;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testObjectPerRequest() throws Exception {
        for (int i = 0; i < OBJ_COUNT; i++) {
            ResponseEntity<Void> response = restTemplate.postForEntity("/post", new Post(), Void.class);
            assertEquals(200, response.getStatusCode().value());
        }
    }

    @Test
    public void test100ObjectsPerRequest() throws Exception {
        for (int i = 0; i < OBJ_COUNT / 100; i++) {
            List<Post> posts = new ArrayList<>();
            for (int j = 0; j < 100; j++) {
                posts.add(new Post());
            }
            ResponseEntity<Void> response = restTemplate.postForEntity("/posts", posts, Void.class);
            assertEquals(200, response.getStatusCode().value());
        }


        ResponseEntity<Void> response = restTemplate.postForEntity("/post", new Post(), Void.class);
    }
}
