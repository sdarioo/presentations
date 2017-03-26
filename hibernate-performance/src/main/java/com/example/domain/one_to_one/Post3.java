package com.example.domain.one_to_one;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Post3 {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * Bidirectional OneToOne with default EAGER fetch type.
	 */
	@OneToOne(mappedBy="post", fetch = FetchType.EAGER)
	private PostDetails3 details;
	
}
