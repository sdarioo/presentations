package com.example.domain.one_to_one;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.example.domain.BaseEntity;

@Entity
public class PostDetails2 extends BaseEntity {

	@Id
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id")
	@MapsId
	private Post2 post;
	
	
	PostDetails2() {}
	
	public PostDetails2(Post2 post) {
		this.post = post;
	}
}
