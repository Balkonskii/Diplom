package com.example.babyprogressmap;

import java.util.Date;

public class Notice {
	public Notice() {
		_Id = 0;
		_NoteId = -1;
		_NotifyDateTime = new Date();
	}

	private int _Id;
	private int _NoteId;
	private Date _NotifyDateTime;

	public void setId(int newId) {
		_Id = newId;
	}

	public int getId() {
		return _Id;
	}

	public void setNoteId(int newNoteId) {
		_NoteId = newNoteId;
	}

	public int getNoteId() {
		return _NoteId;
	}

	public void setNotifyDateTime(Date newNotifyDateTime) {
		_NotifyDateTime = newNotifyDateTime;
	}

	public Date getNotifyDateTime() {
		return _NotifyDateTime;
	}

}
