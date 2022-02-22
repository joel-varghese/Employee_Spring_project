package com.example.demo.controller;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Employdetails {

	private int eid;
	private int empid;
	private String projectname;
	
	@Id
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	
	public Employdetails(int eid, int empid, String projectname) {
		this.eid = eid;
		this.empid = empid;
		this.projectname = projectname;
	}
	public Employdetails() {
		// TODO Auto-generated constructor stub
	}
	
}
