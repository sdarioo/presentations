package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<IdTitle> findAllProjectedBy();

    List<IdTitle> findAllDtoedBy();

    Post findByTitle(String title);

    List<Post> findByStatus(Integer status);

}
