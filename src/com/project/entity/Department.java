package com.project.entity;

import java.io.Serializable;

public class Department implements Serializable{

	private int did;
	
	private String dname;
	
	private String directorID;
	
	private String description;
	
	private int iseffective;

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDirectorID() {
		return directorID;
	}

	public void setDirectorID(String directorID) {
		this.directorID = directorID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIseffective() {
		return iseffective;
	}

	public void setIseffective(int iseffective) {
		this.iseffective = iseffective;
	}

	public Department(int did, String dname, String directorID,
			String description, int iseffective) {
		this.did = did;
		this.dname = dname;
		this.directorID = directorID;
		this.description = description;
		this.iseffective = iseffective;
	}

	public Department() {}
	
	
	
	
	
}
