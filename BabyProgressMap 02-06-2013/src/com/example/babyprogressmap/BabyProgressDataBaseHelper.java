package com.example.babyprogressmap;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BabyProgressDataBaseHelper extends SQLiteOpenHelper {
	private SQLiteDatabase db;

	public static final String DATABASE_NAME = "BabyProgressDataBase";
	private static final int DATABASE_VERSION = 1;

	public static final String CHILDREN_TABLE_NAME = "children";
	public static final String CHILDREN_ID = "childrenId";
	public static final String CHILDREN_NAME = "childrenName";
	public static final String CHILDREN_SURNAME = "childrenSurname";
	public static final String CHILDREN_MIDDLENAME = "childrenMiddlename";
	public static final String CHILDREN_WEIGHT = "childrenWeight";
	public static final String CHILDREN_GROWTH = "childrenGrowth";
	public static final String CHILDREN_AWATAR = "childrenAwatar";
	public static final String CHILDREN_BIRTHDATE = "childrenBirthdate";
	public static final String CHILDREN_PARENT_ID = "parentId";

	public static final String PARENT_TABLE_NAME = "parent";
	public static final String PARENT_ID = "parentId";
	public static final String PARENT_NAME = "parentName";
	public static final String PARENT_SURNAME = "parentSurname";
	public static final String PARENT_MIDDLENAME = "parentMiddlename";
	public static final String PARENT_BIRTHDATE = "parentBirthdate";

	public static final String NOTETYPE_TABLE_NAME = "notetype";
	public static final String NOTETYPE_ID = "notetypeId";
	public static final String NOTETYPE_NAME = "notetypeName";

	public static final String NOTE_TABLE_NAME = "note";
	public static final String NOTE_ID = "noteID";
	public static final String NOTE_DESCRIPTION = "noteDescription";
	public static final String NOTE_POSTDATE = "notePostdate";
	public static final String NOTE_CHILDREN_ID = "childrenId";
	public static final String NOTE_PHOTO = "notePhoto";
	public static final String NOTE_DURATION = "noteDuration";

	public static final String NOTICE_TABLE_NAME = "notice";
	public static final String NOTICE_ID = "noticeId";
	public static final String NOTICE_NOTIFYDATETIME = "notifyDateTime";
	public static final String NOTICE_NOTE_ID = "noteId";

	public static final String CREATE_TABLE_NOTICE = "create table "
			+ NOTICE_TABLE_NAME + "(" + NOTICE_ID
			+ " integer primary key autoincrement," + NOTICE_NOTIFYDATETIME
			+ " text" + NOTICE_NOTE_ID + "foreign key(" + NOTICE_NOTE_ID
			+ ") references " + NOTE_TABLE_NAME + "(" + NOTE_ID + "));";

	public static final String CREATE_TABLE_CHILDREN = "create table "
			+ CHILDREN_TABLE_NAME + "(" + CHILDREN_ID
			+ " integer primary key autoincrement," + CHILDREN_NAME + " text,"
			+ CHILDREN_SURNAME + " text," + CHILDREN_MIDDLENAME + " text,"
			+ CHILDREN_WEIGHT + " real," + CHILDREN_GROWTH + " real,"
			+ CHILDREN_AWATAR + " blob," + CHILDREN_BIRTHDATE + " text,"
			+ "foreign key(" + CHILDREN_PARENT_ID + ") references "
			+ PARENT_TABLE_NAME + "(" + PARENT_ID + "));";

	public static final String CREATE_TABLE_PARENT = "create table "
			+ PARENT_TABLE_NAME + "(" + PARENT_ID
			+ " integer primary key autoincrement," + PARENT_NAME + " text,"
			+ PARENT_SURNAME + " text," + PARENT_MIDDLENAME + " text,"
			+ PARENT_BIRTHDATE + " text);";

	public static final String CREATE_TABLE_NOTETYPE = "create table "
			+ NOTETYPE_TABLE_NAME + "(" + NOTETYPE_ID
			+ " integer primary key autoincrement," + NOTETYPE_NAME + " text);";

	public static final String CREATE_TABLE_NOTE = "create table "
			+ NOTE_TABLE_NAME + "(" + NOTE_ID
			+ " integer primary key autoincrement," + NOTE_DESCRIPTION
			+ " text," + NOTE_POSTDATE + " text," + NOTE_PHOTO + " blob,"
			+ NOTE_DURATION + " real," + "foreign key(" + NOTE_CHILDREN_ID
			+ ") references " + CHILDREN_TABLE_NAME + "(" + CHILDREN_ID + "));";

	public BabyProgressDataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public void onCreate(SQLiteDatabase database) {
		database.execSQL(getDataBaseCreatingScript());
	}

	public static String getDataBaseCreatingScript() {
		return CREATE_TABLE_NOTETYPE + " " + CREATE_TABLE_PARENT + " "
				+ CREATE_TABLE_CHILDREN + " " + CREATE_TABLE_NOTE + " "
				+ CREATE_TABLE_NOTICE;
	}

	public void openDB() {
		db = this.getWritableDatabase();
	}

	public void closeDB() {
		db.close();
	}

	public long insertNoteType(NoteType nt) {
		ContentValues cv = new ContentValues();
		cv.put(NOTETYPE_NAME, nt.GetName());
		return db.insert(NOTETYPE_TABLE_NAME, null, cv);
	}

	public long insertNote(Note note) {
		ContentValues cv = new ContentValues();
		cv.put(NOTE_CHILDREN_ID, note.GetChildrenId());
		cv.put(NOTE_POSTDATE, note.GetPostdate().toString());
		cv.put(NOTE_DESCRIPTION, note.GetDescription());
		cv.put(NOTE_DURATION, note.GetDuration());
		cv.put(NOTE_PHOTO, note.GetPhoto());
		return db.insert(NOTE_TABLE_NAME, null, cv);
	}

	public long insertParent(Parent parent) {
		ContentValues cv = new ContentValues();
		cv.put(PARENT_BIRTHDATE, parent.GetBirthdate().toString());
		cv.put(PARENT_MIDDLENAME, parent.GetMiddlename());
		cv.put(PARENT_NAME, parent.GetName());
		cv.put(PARENT_SURNAME, parent.GetSurname());
		return db.insert(PARENT_TABLE_NAME, null, cv);
	}

	public long insertChildren(Children children) {
		ContentValues cv = new ContentValues();
		cv.put(CHILDREN_AWATAR, children.GetAwatar());
		cv.put(CHILDREN_BIRTHDATE, children.GetBirthdate().toString());
		cv.put(CHILDREN_GROWTH, children.GetGrowth());
		cv.put(CHILDREN_MIDDLENAME, children.GetMiddlename());
		cv.put(CHILDREN_NAME, children.GetName());
		cv.put(CHILDREN_PARENT_ID, children.GetParentId());
		cv.put(CHILDREN_SURNAME, children.GetSurname());
		cv.put(CHILDREN_WEIGHT, children.GetWeight());
		return db.insert(CHILDREN_TABLE_NAME, null, cv);
	}

	public long insertNotice(Notice notice) {
		ContentValues cv = new ContentValues();
		cv.put(NOTICE_NOTE_ID, notice.GetNoteId());
		cv.put(NOTICE_NOTIFYDATETIME, notice.GetNotifyDateTime().toString());
		return db.insert(NOTICE_TABLE_NAME, null, cv);
	}

	public Cursor getCursor(String tableName) {
		String sql = "select * from " + tableName;
		return db.rawQuery(sql, new String[] {});
	}

	public ArrayList<Children> getChildrens() {
		Cursor cur = getCursor(CHILDREN_TABLE_NAME);
		ArrayList<Children> list = new ArrayList<Children>();
		while (cur.moveToNext()) {
			Children child = new Children();
			child.SetId(cur.getInt(0));
			child.SetName(cur.getString(1));
			child.SetSurname(cur.getString(2));
			child.SetMiddlename(cur.getString(3));
			child.SetWeight(cur.getDouble(4));
			child.SetGrowth(cur.getDouble(5));
			child.SetAwatar(cur.getBlob(6));

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				child.SetBirthdate(format.parse(cur.getString(7)));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			child.SetParentId(cur.getInt(8));
			list.add(child);
		}
		return list;
	}

	public ArrayList<Parent> getParents() {
		Cursor cur = getCursor(PARENT_TABLE_NAME);
		ArrayList<Parent> list = new ArrayList<Parent>();
		while (cur.moveToNext()) {
			Parent parent = new Parent();
			parent.SetId(cur.getInt(0));
			parent.SetName(cur.getString(1));
			parent.SetSurname(cur.getString(2));
			parent.SetMiddlename(cur.getString(3));

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				parent.SetBirthdate(format.parse(cur.getString(4)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			list.add(parent);
		}
		return list;
	}

	public ArrayList<Notice> getNotices() {
		Cursor cur = getCursor(NOTICE_TABLE_NAME);
		ArrayList<Notice> list = new ArrayList<Notice>();
		while (cur.moveToNext()) {
			Notice notice = new Notice();
			notice.SetId(cur.getInt(0));

			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss'Z'");
			try {
				notice.SetNotifyDateTime(format.parse(cur.getString(1)));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			notice.SetNoteId(cur.getInt(2));
			list.add(notice);
		}

		return list;
	}

	public ArrayList<NoteType> getNoteTypes() {
		Cursor cur = getCursor(NOTETYPE_TABLE_NAME);
		ArrayList<NoteType> list = new ArrayList<NoteType>();
		while (cur.moveToNext()) {
			NoteType notetype = new NoteType();
			notetype.SetId(cur.getInt(0));
			notetype.SetName(cur.getString(1));
			list.add(notetype);
		}
		return list;
	}

	public ArrayList<Note> getNotes() {
		Cursor cur = getCursor(NOTE_TABLE_NAME);
		ArrayList<Note> list = new ArrayList<Note>();
		while (cur.moveToNext()) {
			Note note = new Note();
			note.SetId(cur.getInt(0));
			note.SetDescription(cur.getString(1));
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss'Z'");
			try {
				note.SetPostdate(format.parse(cur.getString(2)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			note.SetPhoto(cur.getBlob(3));
			note.SetDuration(cur.getDouble(4));
			note.SetChildrenId(cur.getInt(5));
			list.add(note);
		}
		return list;
	}

	public int updateChildren(Children children) {
		ContentValues cv = new ContentValues();
		cv.put(CHILDREN_AWATAR, children.GetAwatar());
		cv.put(CHILDREN_BIRTHDATE, children.GetBirthdate().toString());
		cv.put(CHILDREN_GROWTH, children.GetGrowth());
		cv.put(CHILDREN_MIDDLENAME, children.GetMiddlename());
		cv.put(CHILDREN_NAME, children.GetName());
		cv.put(CHILDREN_PARENT_ID, children.GetParentId());
		cv.put(CHILDREN_SURNAME, children.GetSurname());
		cv.put(CHILDREN_WEIGHT, children.GetWeight());

		return db.update(CHILDREN_TABLE_NAME, cv, CHILDREN_ID + " = "
				+ children.GetId(), null);
	}

	public int updateNoteType(NoteType notetype) {
		ContentValues cv = new ContentValues();
		cv.put(NOTETYPE_NAME, notetype.GetName());

		return db.update(NOTETYPE_TABLE_NAME, cv, NOTETYPE_ID + " = "
				+ notetype.GetId(), null);
	}
	
	public int updateNote(Note note)
	{
		ContentValues cv = new ContentValues();
		cv.put(NOTE_CHILDREN_ID, note.GetChildrenId());
		cv.put(NOTE_POSTDATE, note.GetPostdate().toString());
		cv.put(NOTE_DESCRIPTION, note.GetDescription());
		cv.put(NOTE_DURATION, note.GetDuration());
		cv.put(NOTE_PHOTO, note.GetPhoto());
		
		return db.update(NOTE_TABLE_NAME, cv, NOTE_ID + " = "
				+ note.GetId(), null);
	}
	
	public int updateParent(Parent parent)
	{
		ContentValues cv = new ContentValues();
		cv.put(PARENT_BIRTHDATE, parent.GetBirthdate().toString());
		cv.put(PARENT_MIDDLENAME, parent.GetMiddlename());
		cv.put(PARENT_NAME, parent.GetName());
		cv.put(PARENT_SURNAME, parent.GetSurname());
		
		return db.update(PARENT_TABLE_NAME, cv, PARENT_ID + " = "
				+ parent.GetId(), null);
	}
	
	public int updateNotice(Notice notice)
	{
		ContentValues cv = new ContentValues();
		cv.put(NOTICE_NOTE_ID, notice.GetNoteId());
		cv.put(NOTICE_NOTIFYDATETIME, notice.GetNotifyDateTime().toString());
		
		return db.update(NOTICE_TABLE_NAME, cv, NOTICE_ID + " = "
				+ notice.GetId(), null);
	}
}
