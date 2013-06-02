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

	public void SetId(int newId) {
		_Id = newId;
	}

	public int GetId() {
		return _Id;
	}

	public void SetName(String newName) {
		_Name = newName;
	}

	public String GetName() {
		return _Name;
	}

	public void SetSurname(String newSurname) {
		_Surname = newSurname;
	}

	public String GetSurname() {
		return _Surname;
	}

	public void SetMiddlename(String newMiddlename) {
		_Middlename = newMiddlename;
	}

	public String GetMiddlename() {
		return _Middlename;
	}

	public void SetBirthdate(Date newBirthdate) {
		_Birthdate = newBirthdate;
	}

	public Date GetBirthdate() {
		return _Birthdate;
	}

	public void SetAwatar(byte[] newAwatar) {
		_Awatar = newAwatar;
	}

	public byte[] GetAwatar() {
		return _Awatar;
	}

	public void SetWeight(double newWeight) {
		_Weight = newWeight;
	}

	public double GetWeight() {
		return _Weight;
	}

	public void SetGrowth(double newGrowth) {
		_Growth = newGrowth;
	}

	public double GetGrowth() {
		return _Growth;
	}

	public void SetParentId(int newParentId) {
		_ParentId = newParentId;
	}

	public int GetParentId() {
		return _ParentId;
	}
}
