package com.example.babyprogressmap;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Children {
	private int _Id;
	private String _Name;
	private String _Surname;
	private String _Middlename;
	private Date _Birthdate;
	private byte[] _Awatar;
	private double _Weight;
	private double _Growth;
	private int _ParentId;

	public Children(){
		_Id = 0;
		_Name = "";
		_Surname = "";
		_Middlename = "";
		_Birthdate = new Date();
		_Awatar = null;
		_Weight = 0;
		_Growth = 0;
		_ParentId = -1;
	}
	
	public void setId(int newId) {
		_Id = newId;
	}

	public int getId() {
		return _Id;
	}

	public void setName(String newName) {
		_Name = newName;
	}

	public String getName() {
		return _Name;
	}

	public void setSurname(String newSurname) {
		_Surname = newSurname;
	}

	public String getSurname() {
		return _Surname;
	}

	public void setMiddlename(String newMiddlename) {
		_Middlename = newMiddlename;
	}

	public String getMiddlename() {
		return _Middlename;
	}

	public void setBirthdate(Date newBirthdate) {
		_Birthdate = newBirthdate;
	}

	public Date getBirthdate() {
		return _Birthdate;
	}

	public void setAwatar(byte[] newAwatar) {
		_Awatar = newAwatar;
	}

	public byte[] getAwatar() {
		return _Awatar;
	}

	public void setWeight(double newWeight) {
		_Weight = newWeight;
	}

	public double getWeight() {
		return _Weight;
	}

	public void setGrowth(double newGrowth) {
		_Growth = newGrowth;
	}

	public double getGrowth() {
		return _Growth;
	}

	public void setParentId(int newParentId) {
		_ParentId = newParentId;
	}

	public int getParentId() {
		return _ParentId;
	}
}
