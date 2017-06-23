package com.example.demo.domain

import javax.persistence.*

@Entity
class CommentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id

    @OneToOne
    @JoinColumn(name = "comment_id")
    Comment comment
}
