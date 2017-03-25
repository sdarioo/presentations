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

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	private Integer intValue1 = 1;
	private Integer intValue2 = 2;
	private Integer intValue3 = 3;
	private Integer intValue4 = 4;
	private Integer intValue5 = 5;
	
	private Long longValue1 = 1L;
	private Long longValue2 = 2L;
	private Long longValue3 = 3L;
	private Long longValue4 = 4L;
	private Long longValue5 = 5L;
	
	private String str1 = "device-str-value-1";
	private String str2 = "device-str-value-2";
	private String str3 = "device-str-value-3";
	private String str4 = "device-str-value-4";
	private String str5 = "device-str-value-5";
	
	
	protected BaseEntity() {
	}
	
	public Integer getId() {
		return id;
	}

	@Transient
	public boolean isNew() {
		return null == getId();
	}

}
