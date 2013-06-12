package com.example.babyprogressmap;

import java.util.Date;

public class Notice {
	public Notice() {
		_Id = -1;
		_Title = "";
		_Description = "";
		_NotifyDateTime = new Date();
		_ChildrenId = -1;
	}

	private int _Id;
	private String _Title;
	private String _Description;
	private Date _NotifyDateTime;
	private int _ChildrenId;

	public void setId(int newId) {
		_Id = newId;
	}

	public int getId() {
		return _Id;
	}

	public int getChildrenId() {
		return _ChildrenId;
	}

	public void setChildrenId(int newChildrenId) {
		_ChildrenId = newChildrenId;
	}

	public String getTitle() {
		return _Title;
	}

	public void setTitle(String newTitle) {
		_Title = newTitle;
	}

	public String getDescription() {
		return _Description;
	}

	public void setDescription(String newDescription) {
		_Description = newDescription;
	}

	public void setNotifyDateTime(Date newNotifyDateTime) {
		_NotifyDateTime = newNotifyDateTime;
	}

	public Date getNotifyDateTime() {
		return _NotifyDateTime;
	}

}
