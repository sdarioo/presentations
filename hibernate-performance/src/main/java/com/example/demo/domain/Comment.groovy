package com.example.demo.domain

import javax.persistence.*

@Entity
class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id

    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post

//    @OneToOne(mappedBy = "comment")
//    CommentDetails details

    void setDetails(def details) {}

}
