package com.example.babyprogressmap;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.PeriodicSync;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class NotesForm extends Activity {

	ImageView imageView_awatar;
	TextView textView_name;
	TextView textView_age;
	TextView textView_date;
	Button button_chooseDate;
	ListView listView_notes;
	ImageButton imageButton_add;

	private int dlg_year;
	private int dlg_month;
	private int dlg_day;

	DataAdapter dataAdapter;
	ListViewNoteAdapter listViewNoteAdapter;

	static final int DATE_DIALOG_ID = 999;

	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notes_form);

		dataAdapter = new DataAdapter(this);
		dataAdapter.open();
		listViewNoteAdapter = new ListViewNoteAdapter(this);

		imageView_awatar = (ImageView) findViewById(R.id.imageView_awatar);
		textView_name = (TextView) findViewById(R.id.textView_name);
		textView_age = (TextView) findViewById(R.id.textView_age);
		textView_date = (TextView) findViewById(R.id.textView_date);
		button_chooseDate = (Button) findViewById(R.id.button_chooseDate);
		listView_notes = (ListView) findViewById(R.id.listView_notes);
		imageButton_add = (ImageButton) findViewById(R.id.imageButton_add);

		// Date date = DataManager.getChildren().getBirthdate();
		textView_name.setText(DataManager.getChildren().getName());
		// /////////////////////////////////
		// String dateS = "2012-06-05";
		// SimpleDateFormat format = new
		// SimpleDateFormat(DataAdapter.DATE_FORMAT);
		// try {
		// DataManager.getChildren().setBirthdate(format.parse(dateS));
		// } catch (ParseException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// //////////////////////////////////

		imageButton_add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(getBaseContext(), EditNote.class);
				myIntent.putExtra("previousActivity",
						ActivityEnum.NotesForm.toString());
				startActivity(myIntent);

			}
		});

		Date dtChild = DataManager.getChildren().getBirthdate();
		String age = getAge(dtChild);
		textView_age.setText(age);

		Bitmap bmp = DataManager.getImageFromBytes(DataManager.getChildren()
				.getAwatar());

		imageView_awatar.setImageBitmap(bmp);
		button_chooseDate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		textView_date.setText(format.format(now) + " ");

		showNotes(now);

		listView_notes
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long id) {
						DataManager.setNote(listViewNoteAdapter.getCollection()
								.get(position));
					}
				});

		listView_notes.setLongClickable(true);
		listView_notes
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> arg0,
							View arg1, int position, long id) {
						DataManager.setNote(listViewNoteAdapter.getCollection()
								.get(position));
						return false;
					}
				});

		registerForContextMenu(listView_notes);

		ActionBar aBar = getActionBar();
		aBar.setDisplayShowTitleEnabled(false);
		aBar.setDisplayShowHomeEnabled(false);
		aBar.show();
		getOverflowMenu();

	}

	private void getOverflowMenu() {

		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu_notes_form_, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_bar_notes_form, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent myIntent;

		switch (item.getItemId()) {
		case R.id.children_profile:
			myIntent = new Intent(getBaseContext(), ViewChildrenProfile.class);
			myIntent.putExtra("previousActivity",
					ActivityEnum.NotesForm.toString());
			startActivity(myIntent);
			return true;
		case R.id.change_children_profile:
			myIntent = new Intent(getBaseContext(), ChangeChildrenProfile.class);
			myIntent.putExtra("previousActivity",
					ActivityEnum.NotesForm.toString());
			startActivity(myIntent);
			return true;
		case R.id.logout:
			DataManager.reset();
			myIntent = new Intent(getBaseContext(), MyLoginActivity.class);
			myIntent.putExtra("previousActivity",
					ActivityEnum.NotesForm.toString());
			startActivity(myIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Intent myIntent;

		switch (item.getItemId()) {
		case R.id.itemMaxsimize:
			myIntent = new Intent(getBaseContext(), ViewNote.class);
			startActivity(myIntent);
			break;
		case R.id.itemEdit:
			myIntent = new Intent(getBaseContext(), EditNote.class);
			startActivity(myIntent);
			break;
		case R.id.itemDelete:
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setTitle("");
			alert.setMessage("Вы действительно хотите удалить эту заметку?");
			alert.setButton("Да", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dataAdapter.deleteNote(DataManager.getNote());
					showNotes(DataManager.getNote().getPostdate());
				}
			});
			alert.setButton2("Нет", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// ...
				}
			});
			alert.show();
			break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, dlg_year,
					dlg_month, dlg_day);
		}
		return null;
	}

	private void showNotes(Date date) {
		// ArrayList<Note> notes = dataAdapter.getNotes();
		ArrayList<Note> notes = dataAdapter.getNotesByDate(date);
		listViewNoteAdapter.getCollection().clear();

		for (Note note : notes) {
			listViewNoteAdapter.addItem(note);
		}

		if (listViewNoteAdapter.getCollection().size() > 0) {
			DataManager.setNote(listViewNoteAdapter.getCollection().get(0));
		} else
			DataManager.setNote(new Note());

		listView_notes.setAdapter(listViewNoteAdapter);
		listViewNoteAdapter.notifyDataSetChanged();
	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	public static int[] getDateDiffParts(Date date1, Date date2) {
		long days = getDateDiff(date1, date2, TimeUnit.DAYS);

		int year = (int) (days / 365);
		int month = (int) ((days % 365) / 30);
		int day = (int) ((days % 365) % 30);

		return new int[] { year, month, day };
	}

	public String getAge(Date birthdate) {
		long days = Math.abs(getDateDiff(new Date(), birthdate, TimeUnit.DAYS));

		int year = (int) (days / 365);
		int month = (int) ((days % 365) / 30);
		int day = (int) ((days % 365) % 30);

		String yearRes = "", monthRes = "", dayRes = "";

		if (year != 0) {
			if (("" + year).endsWith("1") & year != 11)
				yearRes = year + " год";
			else if (("" + year).endsWith("2") | ("" + year).endsWith("3")
					| ("" + year).endsWith("4"))
				yearRes = year + " года";
			else
				yearRes = year + " лет";
		}

		if (month != 0) {
			if (("" + month).endsWith("1") & month != 11)
				monthRes = month + " месяц";
			else if (("" + month).endsWith("2") | ("" + month).endsWith("3")
					| ("" + month).endsWith("4"))
				monthRes = month + " месяца";
			else
				monthRes = month + " месяцев";
		}

		if (day != 0) {
			if (("" + day).endsWith("1") & day != 11)
				dayRes = day + " день";
			else if (("" + day).endsWith("2") | ("" + day).endsWith("3")
					| ("" + day).endsWith("4"))
				dayRes = day + " дня";
			else
				dayRes = day + " дней";
		}

		return yearRes + " " + monthRes + " " + dayRes;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			dlg_year = selectedYear;
			dlg_month = selectedMonth;
			dlg_day = selectedDay;

			textView_date.setText(dlg_day + "." + (dlg_month + 1) + "."
					+ dlg_year);

			Date dt = new Date(dlg_year - 1900, dlg_month+1, dlg_day);

			showNotes(dt);
		}
	};

	@Override
	public void onBackPressed() {
	}
}
