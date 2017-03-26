package com.example.domain.one_to_one;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.example.domain.BaseEntity;

@Entity
public class PostDetails3 extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="postId")
	private Post3 post;
	
	PostDetails3() {}
	
	public PostDetails3(Post3 post) {
		this.post = post;
	}
}
