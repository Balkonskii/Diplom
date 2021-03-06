package com.example.babyprogressmap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DataManager {
	private static Account _account = new Account();
	private static Children _children = new Children();
	private static Note _note = new Note();
	
	public static void setAccount(Account newAccount) {
		_account = newAccount;
	}

	//current account
	public static Account getAccount() {
		return _account;
	}

	public static void setChildren(Children children) {
		_children = children;
	}

	//current children
	public static Children getChildren() {
		return _children;
	}
	
	//current note
	public static Note getNote()
	{
		return _note;
	}
	
	public static void setNote(Note newNote)
	{
		_note = newNote;
	}
	
	public static byte[] getImageBytes(Bitmap bitmap)
	{
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
			return stream.toByteArray();
		} catch (Exception e) {
			return new byte[0];
		}
	}
	
	public static Bitmap getImageFromBytes(byte[] array)
	{		
		try {
			InputStream is = new ByteArrayInputStream(array);
			return BitmapFactory.decodeStream(is);
		} catch (Exception e) {
			Bitmap.Config conf = Bitmap.Config.ARGB_8888; 			
			return Bitmap.createBitmap(200,200, conf); 
		}
	}
}
