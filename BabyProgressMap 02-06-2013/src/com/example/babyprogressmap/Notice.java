package com.example.babyprogressmap;

import java.util.Date;

public class Notice {
	public Notice() {
	}

	private int _Id;
	private int _NoteId;
	private Date _NotifyDateTime;

	public void SetId(int newId) {
		_Id = newId;
	}

	public int GetId() {
		return _Id;
	}

	public void SetNoteId(int newNoteId) {
		_NoteId = newNoteId;
	}

	public int GetNoteId() {
		return _NoteId;
	}

	public void SetNotifyDateTime(Date newNotifyDateTime) {
		_NotifyDateTime = newNotifyDateTime;
	}

	public Date GetNotifyDateTime() {
		return _NotifyDateTime;
	}

}
