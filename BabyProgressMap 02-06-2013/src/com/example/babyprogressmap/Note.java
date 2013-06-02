package com.example.babyprogressmap;

import java.util.Date;

public class Note {
	public Note() {
	}

	private int _Id;
	private int _ChildrenId;
	private String _Description;
	private byte[] _Photo;
	private Date _Postdate;
	private double _Duration;
	private int _NoteTypeId;

	public void SetId(int newId) {
		_Id = newId;
	}

	public int GetId() {
		return _Id;
	}

	public void SetChildrenId(int newChildrenId) {
		_ChildrenId = newChildrenId;
	}

	public int GetChildrenId() {
		return _ChildrenId;
	}

	public void SetDescription(String newDescription) {
		_Description = newDescription;
	}

	public String GetDescription() {
		return _Description;
	}

	public void SetPhoto(byte[] newPhoto) {
		_Photo = newPhoto;
	}

	public byte[] GetPhoto() {
		return _Photo;
	}

	public void SetPostdate(Date newPostdate) {
		_Postdate = newPostdate;
	}

	public Date GetPostdate() {
		return _Postdate;
	}

	public void SetDuration(double newDuration) {
		_Duration = newDuration;
	}

	public double GetDuration() {
		return _Duration;
	}

	public void SetNoteTypeId(int newNoteTypeId) {
		_NoteTypeId = newNoteTypeId;
	}

	public int GetNoteTypeId() {
		return _NoteTypeId;
	}

}
