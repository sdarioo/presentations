package com.example.domain;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Persistable;
import org.springframework.util.ClassUtils;

@MappedSuperclass
@SuppressWarnings("unused")
public abstract class BaseEntity {
	
	private static final long serialVersionUID = 1L;

	
	private Integer intValue1 = 1;
	private Integer intValue2 = 2;
	private Integer intValue3 = 3;
	private Integer intValue4 = 4;
	private Integer intValue5 = 5;
	
	private Long longValue1 = 10L;
	private Long longValue2 = 200L;
	private Long longValue3 = 3000L;
	private Long longValue4 = 40000L;
	private Long longValue5 = 500000L;
	
	private Instant time1 = Instant.now();
	private Instant time2 = Instant.now();
	private Instant time3 = Instant.now();
	private Instant time4 = Instant.now();
	private Instant time5 = Instant.now();
	
	private String str1 = "device-str-value-1+someothertext";
	private String str2 = "device-str-value-2+someothertext";
	private String str3 = "device-str-value-3+someothertext";
	private String str4 = "device-str-value-4+someothertext";
	private String str5 = "device-str-value-5+someothertext";
	
	
	protected BaseEntity() {
	}

}
