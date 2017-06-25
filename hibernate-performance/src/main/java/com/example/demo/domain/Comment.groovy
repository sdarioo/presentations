package com.example.demo.domain

import javax.persistence.*

@Entity
class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id

    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post

}
