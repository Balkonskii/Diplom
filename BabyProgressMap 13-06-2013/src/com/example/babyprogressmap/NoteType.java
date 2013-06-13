package com.example.babyprogressmap;

public class NoteType {
	public NoteType() {
		_Id = 0;
		_Name = "";
	}

	private int _Id;
	private String _Name;

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
}
