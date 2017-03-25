package com.example.domain.one_to_one;

import org.springframework.data.repository.CrudRepository;

public interface ParentRepository extends CrudRepository<Parent, Integer> {
}
