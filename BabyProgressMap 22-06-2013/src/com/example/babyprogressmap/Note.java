package com.example.babyprogressmap;

import java.util.Date;

public class Note {
	public Note() {
		_Id = -1;
		_ChildrenId = -1;
		_Description = "";
		_Photo = null;
		_Postdate = new Date();
		_withImage = true;
	}

	private int _Id;
	private int _ChildrenId;
	private String _Description;
	private byte[] _Photo;
	private Date _Postdate;
	private String _Title;
	private boolean _withImage;

	public boolean hasImage = true; // TODO

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

	public String getTitle() {
		return _Title;
	}

	public void setTitle(String newTitle) {
		_Title = newTitle;
	}

	public String toString() {
		String result = "_Postdate = " + _Postdate.toLocaleString()
				+ "\n_ChildrenId = " + _ChildrenId + "\n_Description = "
				+ _Description + "\n_Id = " + _Id + "\n_Title = " + _Title
				+ "\n_withImage = " + _withImage;

		return result;

	}
}
