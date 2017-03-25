package com.example.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="groups")
@SuppressWarnings("unused")
public class Group extends BaseEntity  {
	
//	private static final long serialVersionUID = 1L;
//
//
//	@NotNull
//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    private Agency agency;
//    
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
//    private Set<Device> devices = new HashSet<>();
//    
//    
//    private Integer intValue1 = 1;
//	private Integer intValue2 = 2;
//	private Integer intValue3 = 3;
//	
//    private String str1 = "group-str-value-1";
//	private String str2 = "group-str-value-2";
//	private String str3 = "group-str-value-3";
//	
//	
//	protected Group() {}
//	
//	public Group(String name, String desc, Agency agency) {
//		super(name, desc);
//		this.agency = agency;
//		agency.addGroup(this);
//	}
//
//	void addDevice(Device d) {
//		devices.add(d);
//	}
}
