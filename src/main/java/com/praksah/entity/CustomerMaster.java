package com.praksah.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customer_master")
@SequenceGenerator(initialValue = 1,allocationSize = 1, name = "customerSeq", sequenceName = "customer_seq")
public class CustomerMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "customerSeq")
	private Integer id;
	private String name;
	private String location;
	
	public CustomerMaster() { }

	public CustomerMaster(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}

	public CustomerMaster(Integer id, String name, String location) {
		this.id = id;
		this.name = name;
		this.location = location;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "CustomerMaster [id=" + id + ", name=" + name + ", location=" + location + "]";
	}
	
}
