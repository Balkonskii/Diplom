package com.example.babyprogressmap;

import java.util.Date;

public class Parent {
	public Parent() {
	}

	private int _Id;
	private String _Name;
	private String _Surname;
	private String _Middlename;
	private Date _Birthdate;

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

}
