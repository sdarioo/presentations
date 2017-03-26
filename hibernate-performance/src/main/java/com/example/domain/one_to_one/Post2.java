package com.example.domain.one_to_one;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Post2 {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * Bidirectional OneToOne with LAZY fetch type and sharing primary key between
	 * parent and child (MapsId). Optional=false needed - otherwise details will be fetched eagerly.
	 */
	@OneToOne(mappedBy="post", fetch=FetchType.LAZY, optional=false)
	private PostDetails2 details;
	
}
