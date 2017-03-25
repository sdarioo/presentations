package com.example.domain.id_generation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name="ttable")
public class Table extends Base {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Integer id;
}
