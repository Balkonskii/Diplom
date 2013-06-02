package com.example.babyprogressmap;

public class NoteType {
	public NoteType() {
	}

	private int _Id;
	private String _Name;

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
}
