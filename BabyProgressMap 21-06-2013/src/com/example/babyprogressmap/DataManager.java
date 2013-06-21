package com.example.babyprogressmap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;

import states.ChangeChildrenProfileState;
import states.ChildrenProfileState;
import states.EditNoteState;
import states.LoginState;
import states.NotesState;
import states.RegistrationState;
import states.State;
import states.ViewChildrenProfileState;
import states.ViewNoteState;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import forms.ChangeChildrenProfile;
import forms.ChildrenProfile;
import forms.EditNote;
import forms.Login;
import forms.Notes;
import forms.Notifications;
import forms.Registration;
import forms.ViewChildrenProfile;
import forms.ViewNote;

public class DataManager {
	private static Account _account;
	private static Children _children;
	private static Note _note;
	private static Notice _notice;
	private static ActivityEnum _PreviousActivity;

	public static String Extra_PreviousActivity = "previousActivity";
	public static String Extra_isUpdate = "isUpdate";

	private static Date _date = new Date();

	private static State _currentState;
	
	private static ChangeChildrenProfileState _ChangeChildrenProfileState = new ChangeChildrenProfileState();
	private static ChildrenProfileState _ChildrenProfileState = new ChildrenProfileState();
	private static EditNoteState _EditNoteState = new EditNoteState();
	private static LoginState _LoginState = new LoginState();
	private static NotesState _NotesState= new NotesState();
	private static RegistrationState _RegistrationState= new RegistrationState();
	private static ViewChildrenProfileState _ViewChildrenProfileState= new ViewChildrenProfileState();
	private static ViewNoteState _ViewNoteState= new ViewNoteState();
	
	
	public static void reset() {
		_account = new Account();
		_children = new Children();
		_note = new Note();
		_notice = new Notice();
		_date = new Date();
		setPreviousActivity(ActivityEnum.Null);
		_currentState = _LoginState;
	}

	public static Date getChoosedDate() {
		return _date;
	}

	public static void setChoosedDate(Date date) {
		_date = date;
	}

	public static void setNotice(Notice notice) {
		_notice = notice;
	}

	public static Notice getNotice() {
		return _notice;
	}

	public static void setAccount(Account newAccount) {
		_account = newAccount;
	}

	// current account
	public static Account getAccount() {
		return _account;
	}

	public static void setChildren(Children children) {
		_children = children;
	}

	// current children
	public static Children getChildren() {
		return _children;
	}

	// current note
	public static Note getNote() {
		return _note;
	}

	public static void setNote(Note newNote) {
		_note = newNote;
	}

	public static byte[] getImageBytes(Bitmap bitmap) {
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
			return stream.toByteArray();
		} catch (Exception e) {
			return new byte[0];
		}
	}

	public static Bitmap getImageFromBytes(byte[] array) {
		try {
			InputStream is = new ByteArrayInputStream(array);
			return BitmapFactory.decodeStream(is);
		} catch (Exception e) {
			Bitmap.Config conf = Bitmap.Config.ARGB_8888;
			return Bitmap.createBitmap(200, 200, conf);
		}
	}

	public static Class<?> getClassByEnumValue(ActivityEnum value) {
		switch (value) {
		case ChangeChildrenProfile:
			return ChangeChildrenProfile.class;
		case EditNote:
			return EditNote.class;
		case ChildrenProfile:
			return ChildrenProfile.class;
		case Login:
			return Login.class;
		case Notes:
			return Notes.class;
		case Notifications:
			return Notifications.class;
		case Registration:
			return Registration.class;
		case ViewChildrenProfile:
			return ViewChildrenProfile.class;
		case ViewNote:
			return ViewNote.class;
		default:
			return null;
		}

	}

	public Intent getNextFormIntent(Context context, ActivityEnum current,
			ActivityEnum next) {

		Intent intent = new Intent(context, getClassByEnumValue(next));
		intent.putExtra(DataManager.Extra_PreviousActivity, current.toString());
		return intent;
	}

	public static ActivityEnum getPreviousActivity() {
		return _PreviousActivity;
	}

	public static void setPreviousActivity(ActivityEnum _PreviousActivity) {
		DataManager._PreviousActivity = _PreviousActivity;
	}

	public static ChangeChildrenProfileState getChangeChildrenProfileState() {
		return _ChangeChildrenProfileState;
	}

	public static void setChangeChildrenProfileState(
			ChangeChildrenProfileState _ChangeChildrenProfileState) {
		DataManager._ChangeChildrenProfileState = _ChangeChildrenProfileState;
	}

	public static ChildrenProfileState getChildrenProfileState() {
		return _ChildrenProfileState;
	}

	public static void setChildrenProfileState(ChildrenProfileState _ChildrenProfileState) {
		DataManager._ChildrenProfileState = _ChildrenProfileState;
	}

	public static EditNoteState getEditNoteState() {
		return _EditNoteState;
	}

	public static void setEditNoteState(EditNoteState _EditNoteState) {
		DataManager._EditNoteState = _EditNoteState;
	}

	public static LoginState getLoginState() {
		return _LoginState;
	}

	public static void setLoginState(LoginState _LoginState) {
		DataManager._LoginState = _LoginState;
	}

	public static NotesState getNotesState() {
		return _NotesState;
	}

	public static void setNotesState(NotesState _NotesState) {
		DataManager._NotesState = _NotesState;
	}

	public static RegistrationState getRegistrationState() {
		return _RegistrationState;
	}

	public static void setRegistrationState(RegistrationState _RegistrationState) {
		DataManager._RegistrationState = _RegistrationState;
	}

	public static ViewChildrenProfileState getViewChildrenProfileState() {
		return _ViewChildrenProfileState;
	}

	public static void setViewChildrenProfileState(
			ViewChildrenProfileState _ViewChildrenProfileState) {
		DataManager._ViewChildrenProfileState = _ViewChildrenProfileState;
	}

	public static ViewNoteState getViewNoteState() {
		return _ViewNoteState;
	}

	public static void setViewNoteState(ViewNoteState _ViewNoteState) {
		DataManager._ViewNoteState = _ViewNoteState;
	}

	public static State getCurrentState() {
		return _currentState;
	}

	public static void setCurrentState(State _currentState) {
		DataManager._currentState = _currentState;
	}
}
