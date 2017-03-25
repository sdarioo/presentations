package com.example.domain.id_generation;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@SuppressWarnings("unused")
public class Base {

	private Integer intValue1 = 1;
	private Integer intValue2 = 2;
	private Integer intValue3 = 3;
	private Integer intValue4 = 4;
	private Integer intValue5 = 5;
	
	private String str1 = "device-str-value-1";
	private String str2 = "device-str-value-2";
	private String str3 = "device-str-value-3";
	private String str4 = "device-str-value-4";
	private String str5 = "device-str-value-5";
	
}
