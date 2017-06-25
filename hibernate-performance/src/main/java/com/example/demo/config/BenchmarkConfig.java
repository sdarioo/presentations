package com.example.demo.config;

import com.example.demo.domain.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;


@Configuration
public class BenchmarkConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Bean
    @Order(1)
    public ApplicationRunner benchmarkRunner() {
        return new BenchmarkRunner(new RestTemplate());
    }

    @Bean
    public ApplicationRunner exitManager(ApplicationContext context) {
        return args -> {
            if (isBenchmarkMode(args)) {
                LOGGER.info("Exiting application.");
                SpringApplication.exit(context, () -> 0);
            }
        };
    }

    private static boolean isBenchmarkMode(ApplicationArguments args) {
        return args.getOptionNames().contains("--benchmark");
    }

    private static class BenchmarkRunner implements ApplicationRunner {

        private final RestTemplate template;

        private BenchmarkRunner(RestTemplate template) {
            this.template = template;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {
            if (!isBenchmarkMode(args)) {
                LOGGER.info("Benchmark mode not enabled.");
                return;
            }
            String host = "localhost";
            int port = 8080;
            int count = 100;

            if (args.containsOption("--host")) {
                host = args.getOptionValues("--host").get(0);
            }
            if (args.containsOption("--port")) {
                port = Integer.parseInt(args.getOptionValues("--port").get(0));
            }
            if (args.containsOption("--count")) {
                count = Integer.parseInt(args.getOptionValues("--count").get(0));
            }

            URI url1 = new URI("http", null, host, port, "post", null, null);
            URI url2 = new URI("http", null, host, port, "posts", null, null);

            benchmark(this::addOneByOne, url1, count);
            benchmark(this::addInBatches, url2, count);
        }

        private long benchmark(BiConsumer<URI, Integer> runnable, URI url, int count) {
            LOGGER.info("Starting benchmark...");
            long start = System.currentTimeMillis();
            runnable.accept(url, count);
            return System.currentTimeMillis() - start;
        }

        private void addOneByOne(URI url, int count) {
            for (int i = 0; i < count; i++) {
                ResponseEntity<Void> response = template.postForEntity(url, new Post(), Void.class);
                verityResponse(response);
            }
        }

        private void addInBatches(URI url, int count) {
            List<Post> posts = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                posts.add(new Post());
                if ((posts.size() >= 1000) || (i == count - 1)) {
                    ResponseEntity<Void> response = template.postForEntity(url, posts, Void.class);
                    verityResponse(response);
                    posts.clear();
                }
            }
        }

        private void verityResponse(ResponseEntity<Void> responseEntity) {
            if (responseEntity.getStatusCode().value() != 200) {
                throw new RuntimeException("Unexpected response: " + responseEntity.toString());
            }
        }
    }

}
