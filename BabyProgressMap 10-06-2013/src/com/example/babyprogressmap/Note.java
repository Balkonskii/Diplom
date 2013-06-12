package com.example.babyprogressmap;

import java.util.Date;

public class Note {
	public Note() {
		_Id = -1;
		_ChildrenId = -1;
		_Description = "";
		_Photo = null;
		_Postdate = new Date();
		_Duration = 0;
		_NoteTypeId = -1;
		_withImage = true;
	}

	private int _Id;
	private int _ChildrenId;
	private String _Description;
	private byte[] _Photo;
	private Date _Postdate;
	private double _Duration;
	private int _NoteTypeId;
	private String _Title;
	private boolean _withImage;

	public boolean hasImage = true; //TODO

	public void setId(int newId) {
		_Id = newId;
	}

	public int getId() {
		return _Id;
	}

	public void setChildrenId(int newChildrenId) {
		_ChildrenId = newChildrenId;
	}

	public int getChildrenId() {
		return _ChildrenId;
	}

	public void setDescription(String newDescription) {
		_Description = newDescription;
	}

	public String getDescription() {
		return _Description;
	}

	public void setPhoto(byte[] newPhoto) {
		_Photo = newPhoto;
	}

	public byte[] getPhoto() {
		return _Photo;
	}

	public void setPostdate(Date newPostdate) {
		_Postdate = newPostdate;
	}

	public Date getPostdate() {
		return _Postdate;
	}

	public void setDuration(double newDuration) {
		_Duration = newDuration;
	}

	public double getDuration() {
		return _Duration;
	}

	public void setNoteTypeId(int newNoteTypeId) {
		_NoteTypeId = newNoteTypeId;
	}

	public int getNoteTypeId() {
		return _NoteTypeId;
	}

	public String getTitle() {
		return _Title;
	}

	public void setTitle(String newTitle) {
		_Title = newTitle;
	}
}
