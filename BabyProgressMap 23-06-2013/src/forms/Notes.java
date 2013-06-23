package forms;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.example.babyprogressmap.ActivityEnum;
import com.example.babyprogressmap.DataAdapter;
import com.example.babyprogressmap.DataManager;
import com.example.babyprogressmap.ListViewNoteAdapter;
import com.example.babyprogressmap.MyAsyncTask;
import com.example.babyprogressmap.Note;
import com.example.babyprogressmap.R;
import com.example.babyprogressmap.R.id;
import com.example.babyprogressmap.R.layout;
import com.example.babyprogressmap.R.menu;

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

public class Notes extends Activity {

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

		DataManager.getCurrentState().setActivity(this);

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

		textView_name.setText(DataManager.getChildren().getName());

		imageButton_add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DataManager.getCurrentState().middleButtonBarButtonClicked(
						getBaseContext());
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

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		textView_date.setText(format.format(DataManager.getChoosedDate()));

		showNotes(DataManager.getChoosedDate());

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
		switch (item.getItemId()) {
		case R.id.children_profile:
			DataManager.getCurrentState().childrenProfileClicked(
					getBaseContext());
			return true;
		case R.id.change_children_profile:
			DataManager.getCurrentState()
					.changeProfileClicked(getBaseContext());
			return true;
		case R.id.logout:
			DataManager.getCurrentState().logoutClicked(getBaseContext());
			return true;
		case R.id.notifications:
			DataManager.getCurrentState()
					.notificationsClicked(getBaseContext());
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.itemMaxsimize:
			DataManager.getCurrentState().viewNoteClicked(getBaseContext());
			break;
		case R.id.itemEdit:
			DataManager.getCurrentState().editNoteClicked(getBaseContext());
			break;
		case R.id.itemDelete:
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setTitle("");
			alert.setMessage("�� ������������� ������ ������� ��� �������?");
			alert.setButton("��", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dataAdapter.deleteNote(DataManager.getNote());
					showNotes(DataManager.getNote().getPostdate());
				}
			});
			alert.setButton2("���", new DialogInterface.OnClickListener() {

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
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		}
		return null;
	}

	private void showNotes(Date date) {
		ArrayList<Note> notes = dataAdapter.getNotesByDate(date, DataManager
				.getChildren().getId());
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
				yearRes = year + " ���";
			else if (("" + year).endsWith("2") | ("" + year).endsWith("3")
					| ("" + year).endsWith("4"))
				yearRes = year + " ����";
			else
				yearRes = year + " ���";
		}

		if (month != 0) {
			if (("" + month).endsWith("1") & month != 11)
				monthRes = month + " �����";
			else if (("" + month).endsWith("2") | ("" + month).endsWith("3")
					| ("" + month).endsWith("4"))
				monthRes = month + " ������";
			else
				monthRes = month + " �������";
		}

		if (day != 0) {
			if (("" + day).endsWith("1") & day != 11)
				dayRes = day + " ����";
			else if (("" + day).endsWith("2") | ("" + day).endsWith("3")
					| ("" + day).endsWith("4"))
				dayRes = day + " ���";
			else
				dayRes = day + " ����";
		}

		return yearRes + " " + monthRes + " " + dayRes;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			dlg_year = selectedYear;
			dlg_month = selectedMonth;
			dlg_day = selectedDay;

			int mString = dlg_month + 1;

			String resultDay = (dlg_day + "").length() == 1 ? ("0" + dlg_day)
					: (dlg_day + "");
			String resultMonth = (mString + "").length() == 1 ? ("0" + mString)
					: (mString + "");

			textView_date.setText(resultDay + "." + resultMonth + "."
					+ dlg_year);

			Date dt = new Date(dlg_year - 1900, dlg_month, dlg_day);

			DataManager.setChoosedDate(dt);

			showNotes(dt);
		}
	};

	@Override
	public void onBackPressed() {
	}
}
