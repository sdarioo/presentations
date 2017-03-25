package com.example.domain.one_to_one;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.example.domain.BaseEntity;

@Entity
public class Child extends BaseEntity {

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Parent parent;
	
	
	public void setParent(Parent parent) {
		this.parent = parent;
	}
}
