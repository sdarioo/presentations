package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="device")
@SuppressWarnings("unused")
public class Device extends BaseEntity {
	
//	private static final long serialVersionUID = 1L;
//	
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name="groupId")
//    private Group group;
//	
//	@OneToOne(fetch = FetchType.LAZY, mappedBy="device", optional = false)
//	private DeviceDetails details;
//	
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name="networkId")
//    private Network network;
//	
//
//	private String str1 = "device-str-value-1";
//	private String str2 = "device-str-value-2";
//	private String str3 = "device-str-value-3";
//	private String str4 = "device-str-value-4";
//	private String str5 = "device-str-value-5";
//	
//	private Integer intValue1 = 1;
//	private Integer intValue2 = 2;
//	private Integer intValue3 = 3;
//	private Integer intValue4 = 4;
//	private Integer intValue5 = 5;
//	
//	private Long longValue1 = 1L;
//	private Long longValue2 = 2L;
//	private Long longValue3 = 3L;
//	private Long longValue4 = 4L;
//	private Long longValue5 = 5L;
//
//	
//	protected Device() {}
//	
//	public Device(String name, String desc, Group group, Network network) {
//		super(name, desc);
//		this.network = network;
//		this.group = group;
//		group.addDevice(this);
//	}
//	
	
}
