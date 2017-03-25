package com.example.domain.id_generation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class HiloSequence extends Base {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hilo_sequence_generator")
	@GenericGenerator(
          name = "hilo_sequence_generator",
          strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
          parameters = {
                  @Parameter(name = "sequence_name", value = "hilo_seqeunce"),
                  @Parameter(name = "initial_value", value = "1"),
                  @Parameter(name = "increment_size", value = "10"),
                  @Parameter(name = "optimizer", value = "hilo")
          })
	private Integer id;

}
