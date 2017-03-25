package com.example.domain.one_to_one;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.example.domain.BaseEntity;

@Entity
public class Parent extends BaseEntity {

	@OneToOne(mappedBy="parent", fetch = FetchType.LAZY, optional = false)
	private Child child;

}
