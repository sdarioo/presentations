package com.example.demo.domain;

import java.time.Instant;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@SuppressWarnings("unused")
public abstract class BaseEntity {

	private Integer status = 1;
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
	
	private String str1 = "device-str-value-1 + some other text a";
	private String str2 = "device-str-value-2 + some other text b";
	private String str3 = "device-str-value-3 + some other text c";
	private String str4 = "device-str-value-4 + some other text d";
	private String str5 = "device-str-value-5 + some other text e";


	protected BaseEntity() {
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	private static final long serialVersionUID = 1L;
}
