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

	private static BabyProgressDataBaseHelper instance = null;

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

	public static final String ACCOUNT_TABLE_NAME = "parent";
	public static final String ACCOUNT_ID = "parentId";
	public static final String ACCOUNT_PARENT_NAME = "parentName";
	public static final String ACCOUNT_PARENT_SURNAME = "parentSurname";
	public static final String ACCOUNT_PARENT_MIDDLENAME = "parentMiddlename";
	public static final String ACCOUNT_PARENT_BIRTHDATE = "parentBirthdate";
	public static final String ACCOUNT_LOGIN = "login";
	public static final String ACCOUNT_PASSWORD = "password";

//	public static final String NOTETYPE_TABLE_NAME = "notetype";
//	public static final String NOTETYPE_ID = "notetypeId";
//	public static final String NOTETYPE_NAME = "notetypeName";

	public static final String NOTE_TABLE_NAME = "note";
	public static final String NOTE_ID = "noteID";
	public static final String NOTE_DESCRIPTION = "noteDescription";
	public static final String NOTE_POSTDATE = "notePostdate";
	public static final String NOTE_CHILDREN_ID = "childrenId";
	public static final String NOTE_PHOTO = "notePhoto";	
	public static final String NOTE_TITLE = "noteTitle";
	public static final String NOTE_WITH_IMAGE = "noteWithImage";

	public static final String NOTICE_TABLE_NAME = "notice";
	public static final String NOTICE_ID = "noticeId";
	public static final String NOTICE_NOTIFYDATETIME = "notifyDateTime";
	public static final String NOTICE_TITLE = "noteTitle";
	public static final String NOTICE_DESCRIPTION = "noteDescription";
	public static final String NOTICE_CHILDREN_ID = "noteChildrenId";

	public static final String CREATE_TABLE_NOTICE = "create table "
			+ NOTICE_TABLE_NAME + "(" + NOTICE_ID
			+ " integer primary key autoincrement," + NOTICE_NOTIFYDATETIME
			+ " text," + NOTICE_TITLE + " text," + NOTICE_DESCRIPTION
			+ " text," + NOTICE_CHILDREN_ID + " integer," + "foreign key("
			+ NOTICE_CHILDREN_ID + ") references " + CHILDREN_TABLE_NAME + "("
			+ CHILDREN_ID + "));";

	public static final String CREATE_TABLE_CHILDREN = "create table "
			+ CHILDREN_TABLE_NAME + "(" + CHILDREN_ID
			+ " integer primary key autoincrement," + CHILDREN_NAME + " text,"
			+ CHILDREN_SURNAME + " text," + CHILDREN_MIDDLENAME + " text,"
			+ CHILDREN_WEIGHT + " real," + CHILDREN_GROWTH + " real,"
			+ CHILDREN_AWATAR + " blob," + CHILDREN_BIRTHDATE + " text,"
			+ CHILDREN_PARENT_ID + " integer," + "foreign key("
			+ CHILDREN_PARENT_ID + ") references " + ACCOUNT_TABLE_NAME + "("
			+ ACCOUNT_ID + "));";

	public static final String CREATE_TABLE_ACCOUNT = "create table "
			+ ACCOUNT_TABLE_NAME + "(" + ACCOUNT_ID
			+ " integer primary key autoincrement," + ACCOUNT_PARENT_NAME
			+ " text," + ACCOUNT_PARENT_SURNAME + " text,"
			+ ACCOUNT_PARENT_MIDDLENAME + " text," + ACCOUNT_PARENT_BIRTHDATE
			+ " text," + ACCOUNT_LOGIN + " text," + ACCOUNT_PASSWORD
			+ " text);";

//	public static final String CREATE_TABLE_NOTETYPE = "create table "
//			+ NOTETYPE_TABLE_NAME + "(" + NOTETYPE_ID
//			+ " integer primary key autoincrement," + NOTETYPE_NAME + " text);";

	public static final String CREATE_TABLE_NOTE = "create table "
			+ NOTE_TABLE_NAME + "(" + NOTE_ID
			+ " integer primary key autoincrement," + NOTE_DESCRIPTION
			+ " text," + NOTE_TITLE + " text," + NOTE_POSTDATE + " text,"
			+ NOTE_PHOTO + " blob," + NOTE_WITH_IMAGE + " integer,"
			+ NOTE_CHILDREN_ID + " integer,"
			+ "foreign key(" + NOTE_CHILDREN_ID + ") references "
			+ CHILDREN_TABLE_NAME + "(" + CHILDREN_ID + "));";

	private BabyProgressDataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public static BabyProgressDataBaseHelper getInstance(Context context) {
		if (instance == null) {
			instance = new BabyProgressDataBaseHelper(context);
			return instance;
		} else
			return instance;

	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + NOTICE_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + NOTE_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + CHILDREN_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE_NAME);		
		onCreate(db);
	}

	public void onCreate(SQLiteDatabase database) {		
		database.execSQL(CREATE_TABLE_ACCOUNT);
		database.execSQL(CREATE_TABLE_CHILDREN);
		database.execSQL(CREATE_TABLE_NOTE);
		database.execSQL(CREATE_TABLE_NOTICE);
	}
}
