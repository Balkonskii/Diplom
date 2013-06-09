package com.example.babyprogressmap;

import java.util.Date;

public class Account {
	public Account() {
		_Id = -1;
		_Name = "";
		_Surname = "";
		_Middlename = "";
		_Birthdate = new Date();
		_Password = "";
		_Login = "";
	}

	private int _Id;
	private String _Name;
	private String _Surname;
	private String _Middlename;
	private Date _Birthdate;
	private String _Password;
	private String _Login;

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
	
	public void setPassword(String newPassword)
	{
		_Password = newPassword;
	}
	
	public String getPassword()
	{
		return _Password;
	}

	public void setLogin(String newLogin)
	{
		_Login = newLogin;
	}
	
	public String getLogin()
	{
		return _Login;
	}
}
