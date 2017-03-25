package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="devicedetails")
@SuppressWarnings("unused")
public class DeviceDetails  {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Device device;
	
	private Integer intValue1 = 1;
	private Integer intValue2 = 2;
	private Integer intValue3 = 3;
	private Integer intValue4 = 4;
	private Integer intValue5 = 5;
	private Integer intValue6 = 6;
	private Integer intValue7 = 7;
	private Integer intValue8 = 8;
	private Integer intValue9 = 9;
	private Integer intValue10 = 10;
	
	private Long longValue1 = 1L;
	private Long longValue2 = 2L;
	private Long longValue3 = 3L;
	private Long longValue4 = 4L;
	private Long longValue5 = 5L;
	private Long longValue6 = 6L;
	private Long longValue7 = 7L;
	private Long longValue8 = 8L;
	private Long longValue9 = 9L;
	private Long longValue10 = 10L;
	
	
	protected DeviceDetails() {}
	
	public DeviceDetails(String name, String desc, Device device) {
		this.device = device;
	}
}
