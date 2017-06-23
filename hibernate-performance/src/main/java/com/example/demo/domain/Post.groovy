package com.example.demo.domain

import javax.persistence.*

@Entity
class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id

    @Column
    String title = "This is some random title";

    @OneToMany(mappedBy = "post")
    List<Comment> comments = new ArrayList<>();
}
