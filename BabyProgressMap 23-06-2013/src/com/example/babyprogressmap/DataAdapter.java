package com.example.babyprogressmap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

@SuppressLint("SimpleDateFormat")
public class DataAdapter {
	private Context context;
	private BabyProgressDataBaseHelper helper;
	private SQLiteDatabase db;

	private boolean _isClosed = true;

	public static final String DATE_FORMAT_RUS = "dd.MM.yyyy";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String DATE_TIME_FORMAT_REVERSED = "HH:mm:ss dd-MM-yyyy";

	public DataAdapter(Context context) {
		this.context = context;
	}

	public void open() {
		helper = BabyProgressDataBaseHelper.getInstance(context);
		db = helper.getWritableDatabase();
		_isClosed = false;
	}

	public void close() {
		helper.close();
		_isClosed = true;
	}

	public boolean isClosed() {
		return _isClosed;
	}

	// public long insertNoteType(NoteType nt) {
	// ContentValues cv = new ContentValues();
	// cv.put(BabyProgressDataBaseHelper.NOTETYPE_NAME, nt.getName());
	// return db.insert(BabyProgressDataBaseHelper.NOTETYPE_TABLE_NAME, null,
	// cv);
	// }

	public long insertNote(Note note) {
		ContentValues cv = new ContentValues();
		cv.put(BabyProgressDataBaseHelper.NOTE_CHILDREN_ID,
				note.getChildrenId());

		Date dt = note.getPostdate();
		SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
		String resultDate = format.format(dt);

		cv.put(BabyProgressDataBaseHelper.NOTE_POSTDATE, resultDate);
		cv.put(BabyProgressDataBaseHelper.NOTE_DESCRIPTION,
				note.getDescription());

		if (note.hasImage)
			cv.put(BabyProgressDataBaseHelper.NOTE_PHOTO, note.getPhoto());
		else
			cv.put(BabyProgressDataBaseHelper.NOTE_PHOTO, new byte[0]);

		cv.put(BabyProgressDataBaseHelper.NOTE_TITLE, note.getTitle());

		int i = 0;
		if (note.hasImage)
			i = 1;

		cv.put(BabyProgressDataBaseHelper.NOTE_WITH_IMAGE, i);
		return db.insert(BabyProgressDataBaseHelper.NOTE_TABLE_NAME, null, cv);
	}

	public long insertParent(Account parent) {
		ContentValues cv = new ContentValues();

		Date bd = parent.getBirthdate();
		SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
		String result = format.format(bd);

		cv.put(BabyProgressDataBaseHelper.ACCOUNT_PARENT_BIRTHDATE, result);
		cv.put(BabyProgressDataBaseHelper.ACCOUNT_PARENT_MIDDLENAME,
				parent.getMiddlename());
		cv.put(BabyProgressDataBaseHelper.ACCOUNT_PARENT_NAME, parent.getName());
		cv.put(BabyProgressDataBaseHelper.ACCOUNT_PARENT_SURNAME,
				parent.getSurname());
		cv.put(BabyProgressDataBaseHelper.ACCOUNT_LOGIN, parent.getLogin());
		cv.put(BabyProgressDataBaseHelper.ACCOUNT_PASSWORD,
				parent.getPassword());
		return db.insert(BabyProgressDataBaseHelper.ACCOUNT_TABLE_NAME, null,
				cv);
	}

	public long insertChildren(Children children) {
		ContentValues cv = new ContentValues();
		cv.put(BabyProgressDataBaseHelper.CHILDREN_AWATAR, children.getAwatar());

		Date dt = children.getBirthdate();
		SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
		String resultDate = format.format(dt);

		cv.put(BabyProgressDataBaseHelper.CHILDREN_BIRTHDATE, resultDate);
		cv.put(BabyProgressDataBaseHelper.CHILDREN_GROWTH, children.getGrowth());
		cv.put(BabyProgressDataBaseHelper.CHILDREN_MIDDLENAME,
				children.getMiddlename());
		cv.put(BabyProgressDataBaseHelper.CHILDREN_NAME, children.getName());
		cv.put(BabyProgressDataBaseHelper.CHILDREN_PARENT_ID,
				children.getParentId());
		cv.put(BabyProgressDataBaseHelper.CHILDREN_SURNAME,
				children.getSurname());
		cv.put(BabyProgressDataBaseHelper.CHILDREN_WEIGHT, children.getWeight());
		return db.insert(BabyProgressDataBaseHelper.CHILDREN_TABLE_NAME, null,
				cv);
	}

	public long insertNotice(Notice notice) {
		ContentValues cv = new ContentValues();
		cv.put(BabyProgressDataBaseHelper.NOTICE_CHILDREN_ID,
				notice.getChildrenId());

		SimpleDateFormat format = new SimpleDateFormat(
				DataAdapter.DATE_TIME_FORMAT);
		String dateRes = format.format(notice.getNotifyDateTime());

		cv.put(BabyProgressDataBaseHelper.NOTICE_NOTIFYDATETIME, dateRes);

		cv.put(BabyProgressDataBaseHelper.NOTICE_TITLE, notice.getTitle());
		cv.put(BabyProgressDataBaseHelper.NOTICE_DESCRIPTION,
				notice.getDescription());

		return db
				.insert(BabyProgressDataBaseHelper.NOTICE_TABLE_NAME, null, cv);
	}

	public Cursor getCursor(String tableName) {
		String sql = "select * from " + tableName;
		return db.rawQuery(sql, new String[] {});
	}

	public ArrayList<Children> getChildrens() {
		Cursor cur = getCursor(BabyProgressDataBaseHelper.CHILDREN_TABLE_NAME);
		ArrayList<Children> list = new ArrayList<Children>();
		while (cur.moveToNext()) {
			Children child = new Children();
			child.setId(cur.getInt(0));
			child.setName(cur.getString(1));
			child.setSurname(cur.getString(2));
			child.setMiddlename(cur.getString(3));
			child.setWeight(cur.getDouble(4));
			child.setGrowth(cur.getDouble(5));
			child.setAwatar(cur.getBlob(6));

			SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
			try {
				Date dt = format.parse(cur.getString(7));
				child.setBirthdate(dt);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			child.setParentId(cur.getInt(8));
			list.add(child);
		}
		return list;
	}

	public Children getChildrenById(int id) {
		String sql = "select * from "
				+ BabyProgressDataBaseHelper.CHILDREN_TABLE_NAME + " where "
				+ BabyProgressDataBaseHelper.CHILDREN_ID + " = " + id;
		Cursor cur = db.rawQuery(sql, new String[] {});
		Children child = new Children();
		if (cur.moveToFirst()) {

			child.setId(cur.getInt(0));
			child.setName(cur.getString(1));
			child.setSurname(cur.getString(2));
			child.setMiddlename(cur.getString(3));
			child.setWeight(cur.getDouble(4));
			child.setGrowth(cur.getDouble(5));
			child.setAwatar(cur.getBlob(6));

			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
			try {
				Date dt = format.parse(cur.getString(7));
				child.setBirthdate(dt);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			child.setParentId(cur.getInt(8));
		}
		return child;
	}

	public int getAccountId(String login) {
		String sql = "select * from "
				+ BabyProgressDataBaseHelper.ACCOUNT_TABLE_NAME + " where "
				+ BabyProgressDataBaseHelper.ACCOUNT_LOGIN + " = '" + login
				+ "'";
		Cursor cur = db.rawQuery(sql, new String[] {});
		int id = -1;
		if (cur.moveToFirst()) {
			id = cur.getInt(0);
		}
		return id;
	}

	public ArrayList<Account> getAccounts() {
		Cursor cur = getCursor(BabyProgressDataBaseHelper.ACCOUNT_TABLE_NAME);
		ArrayList<Account> list = new ArrayList<Account>();
		while (cur.moveToNext()) {
			Account parent = new Account();
			parent.setId(cur.getInt(0));
			parent.setName(cur.getString(1));
			parent.setSurname(cur.getString(2));
			parent.setMiddlename(cur.getString(3));

			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
			try {
				parent.setBirthdate(format.parse(cur.getString(4)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			parent.setLogin(cur.getString(5));
			parent.setPassword(cur.getString(6));
			list.add(parent);
		}
		return list;
	}

	public ArrayList<Notice> getNotices() {
		Cursor cur = getCursor(BabyProgressDataBaseHelper.NOTICE_TABLE_NAME);
		ArrayList<Notice> list = new ArrayList<Notice>();
		while (cur.moveToNext()) {
			Notice notice = new Notice();
			notice.setId(cur.getInt(0));

			SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
			try {
				notice.setNotifyDateTime(format.parse(cur.getString(1)));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			notice.setTitle(cur.getString(2));
			notice.setDescription(cur.getString(3));
			
			list.add(notice);
		}

		return list;
	}

	// public ArrayList<NoteType> getNoteTypes() {
	// Cursor cur = getCursor(BabyProgressDataBaseHelper.NOTETYPE_TABLE_NAME);
	// ArrayList<NoteType> list = new ArrayList<NoteType>();
	// while (cur.moveToNext()) {
	// NoteType notetype = new NoteType();
	// notetype.setId(cur.getInt(0));
	// notetype.setName(cur.getString(1));
	// list.add(notetype);
	// }
	// return list;
	// }

	public ArrayList<Note> getNotes() {
		Cursor cur = getCursor(BabyProgressDataBaseHelper.NOTE_TABLE_NAME);
		ArrayList<Note> list = new ArrayList<Note>();
		while (cur.moveToNext()) {
			Note note = new Note();
			note.setId(cur.getInt(0));
			note.setDescription(cur.getString(1));
			note.setTitle(cur.getString(2));
			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
			try {
				note.setPostdate(format.parse(cur.getString(3)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			note.setPhoto(cur.getBlob(4));

			int i = cur.getInt(5);
			note.hasImage = i != 0;

			note.setChildrenId(cur.getInt(6));
			list.add(note);
		}
		return list;
	}

	public ArrayList<Note> getNotesByDate(Date date, int childrenId) {

		SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
		String dt = format.format(date);

		String sql = "select * from "
				+ BabyProgressDataBaseHelper.NOTE_TABLE_NAME + " where "
				+ BabyProgressDataBaseHelper.NOTE_POSTDATE + " = '" + dt
				+ "' and " + BabyProgressDataBaseHelper.NOTE_CHILDREN_ID
				+ " = " + childrenId;

		Cursor cur = db.rawQuery(sql, new String[] {});
		ArrayList<Note> list = new ArrayList<Note>();
		while (cur.moveToNext()) {
			Note note = new Note();
			note.setId(cur.getInt(0));
			note.setDescription(cur.getString(1));
			note.setTitle(cur.getString(2));
			try {
				note.setPostdate(format.parse(cur.getString(3)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			note.setPhoto(cur.getBlob(4));
			int i = cur.getInt(5);
			note.hasImage = i != 0;
			note.setChildrenId(cur.getInt(6));
			list.add(note);
		}
		return list;
	}

	public int updateChildren(Children children) {
		ContentValues cv = new ContentValues();
		cv.put(BabyProgressDataBaseHelper.CHILDREN_AWATAR, children.getAwatar());

		SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
		String result = format.format(children.getBirthdate());

		cv.put(BabyProgressDataBaseHelper.CHILDREN_BIRTHDATE, result);
		cv.put(BabyProgressDataBaseHelper.CHILDREN_GROWTH, children.getGrowth());
		cv.put(BabyProgressDataBaseHelper.CHILDREN_MIDDLENAME,
				children.getMiddlename());
		cv.put(BabyProgressDataBaseHelper.CHILDREN_NAME, children.getName());
		cv.put(BabyProgressDataBaseHelper.CHILDREN_PARENT_ID,
				children.getParentId());
		cv.put(BabyProgressDataBaseHelper.CHILDREN_SURNAME,
				children.getSurname());
		cv.put(BabyProgressDataBaseHelper.CHILDREN_WEIGHT, children.getWeight());

		return db.update(
				BabyProgressDataBaseHelper.CHILDREN_TABLE_NAME,
				cv,
				BabyProgressDataBaseHelper.CHILDREN_ID + " = "
						+ children.getId(), null);
	}

	// public int updateNoteType(NoteType notetype) {
	// ContentValues cv = new ContentValues();
	// cv.put(BabyProgressDataBaseHelper.NOTETYPE_NAME, notetype.getName());
	//
	// return db.update(BabyProgressDataBaseHelper.NOTETYPE_TABLE_NAME, cv,
	// helper.NOTETYPE_ID + " = " + notetype.getId(), null);
	// }

	public int updateNote(Note note) {
		ContentValues cv = new ContentValues();
		cv.put(BabyProgressDataBaseHelper.NOTE_CHILDREN_ID,
				note.getChildrenId());

		SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
		String dRes = format.format(note.getPostdate());

		cv.put(BabyProgressDataBaseHelper.NOTE_POSTDATE, dRes);

		cv.put(BabyProgressDataBaseHelper.NOTE_TITLE, note.getTitle());

		cv.put(BabyProgressDataBaseHelper.NOTE_DESCRIPTION,
				note.getDescription());

		cv.put(BabyProgressDataBaseHelper.NOTE_PHOTO, note.getPhoto());

		int i = 0;
		if (note.hasImage)
			i = 1;

		cv.put(BabyProgressDataBaseHelper.NOTE_WITH_IMAGE, i);

		return db
				.update(BabyProgressDataBaseHelper.NOTE_TABLE_NAME,
						cv,
						BabyProgressDataBaseHelper.NOTE_ID + " = "
								+ note.getId(), null);
	}

	public int updateParent(Account parent) {
		ContentValues cv = new ContentValues();
		cv.put(BabyProgressDataBaseHelper.ACCOUNT_PARENT_BIRTHDATE, parent
				.getBirthdate().toString());
		cv.put(BabyProgressDataBaseHelper.ACCOUNT_PARENT_MIDDLENAME,
				parent.getMiddlename());
		cv.put(BabyProgressDataBaseHelper.ACCOUNT_PARENT_NAME, parent.getName());
		cv.put(BabyProgressDataBaseHelper.ACCOUNT_PARENT_SURNAME,
				parent.getSurname());

		return db.update(BabyProgressDataBaseHelper.ACCOUNT_TABLE_NAME, cv,
				BabyProgressDataBaseHelper.ACCOUNT_ID + " = " + parent.getId(),
				null);
	}

	public int updateNotice(Notice notice) {
		ContentValues cv = new ContentValues();
		cv.put(BabyProgressDataBaseHelper.NOTICE_CHILDREN_ID,
				notice.getChildrenId());

		SimpleDateFormat format = new SimpleDateFormat(
				DataAdapter.DATE_TIME_FORMAT);
		String dateRes = format.format(notice.getNotifyDateTime());

		cv.put(BabyProgressDataBaseHelper.NOTICE_NOTIFYDATETIME, dateRes);

		cv.put(BabyProgressDataBaseHelper.NOTICE_TITLE, notice.getTitle());
		cv.put(BabyProgressDataBaseHelper.NOTICE_DESCRIPTION,
				notice.getDescription());

		return db.update(BabyProgressDataBaseHelper.NOTICE_TABLE_NAME, cv,
				BabyProgressDataBaseHelper.NOTICE_ID + " = " + notice.getId(),
				null);
	}

	// public int deleteNoteType(NoteType notetype) {
	// return db.delete(
	// BabyProgressDataBaseHelper.NOTETYPE_TABLE_NAME,
	// BabyProgressDataBaseHelper.NOTETYPE_ID + " = "
	// + notetype.getId(), null);
	// }

	public int deleteNote(Note note) {
		return db
				.delete(BabyProgressDataBaseHelper.NOTE_TABLE_NAME,
						BabyProgressDataBaseHelper.NOTE_ID + " = "
								+ note.getId(), null);
	}

	public int deleteChildren(Children children) {
		return db.delete(
				BabyProgressDataBaseHelper.CHILDREN_TABLE_NAME,
				BabyProgressDataBaseHelper.CHILDREN_ID + " = "
						+ children.getId(), null);
	}

	public int deleteParent(Account account) {
		return db
				.delete(BabyProgressDataBaseHelper.ACCOUNT_TABLE_NAME,
						BabyProgressDataBaseHelper.ACCOUNT_ID + " = "
								+ account.getId(), null);
	}

	public int deleteNotice(Notice notice) {
		return db.delete(BabyProgressDataBaseHelper.NOTICE_TABLE_NAME,
				BabyProgressDataBaseHelper.NOTICE_ID + " = " + notice.getId(),
				null);
	}

	public ArrayList<Children> getChildrensByAccount(Account account) {

		String sql = "select * from "
				+ BabyProgressDataBaseHelper.CHILDREN_TABLE_NAME + " where "
				+ BabyProgressDataBaseHelper.CHILDREN_PARENT_ID + " = "
				+ account.getId();

		Cursor cur = db.rawQuery(sql, new String[] {});
		ArrayList<Children> list = new ArrayList<Children>();
		while (cur.moveToNext()) {
			Children child = new Children();
			child.setId(cur.getInt(0));
			child.setName(cur.getString(1));
			child.setSurname(cur.getString(2));
			child.setMiddlename(cur.getString(3));
			child.setWeight(cur.getDouble(4));
			child.setGrowth(cur.getDouble(5));
			child.setAwatar(cur.getBlob(6));

			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
			try {
				child.setBirthdate(format.parse(cur.getString(7)));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			child.setParentId(cur.getInt(8));
			list.add(child);
		}
		return list;
	}

	public Account getAccountByLogin(String login) {
		String sql = " select * from "
				+ BabyProgressDataBaseHelper.ACCOUNT_TABLE_NAME + " where "
				+ BabyProgressDataBaseHelper.ACCOUNT_LOGIN + " = '" + login
				+ "'";
		Cursor cur = db.rawQuery(sql, new String[] {});
		ArrayList<Account> list = new ArrayList<Account>();
		while (cur.moveToNext()) {
			Account parent = new Account();
			parent.setId(cur.getInt(0));
			parent.setName(cur.getString(1));
			parent.setSurname(cur.getString(2));
			parent.setMiddlename(cur.getString(3));

			SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
			try {
				parent.setBirthdate(format.parse(cur.getString(4)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			parent.setLogin(cur.getString(5));
			parent.setPassword(cur.getString(6));
			list.add(parent);
		}
		if (list.size() > 0)
			return list.get(0);
		else
			return new Account();
	}

	public int getMaxNoticeId() {
		String sql = " select max(" + BabyProgressDataBaseHelper.NOTICE_ID
				+ ") from " + BabyProgressDataBaseHelper.NOTICE_TABLE_NAME;

		Cursor cur = db.rawQuery(sql, new String[] {});

		try {
			if (cur.moveToFirst()) {
				return cur.getInt(0);
			}
		} catch (Exception e) {
			return 0;
		}

		return 0;
	}
}
