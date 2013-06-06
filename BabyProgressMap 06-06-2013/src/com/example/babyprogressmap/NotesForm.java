package com.example.babyprogressmap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.PeriodicSync;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class NotesForm extends Activity {

	ListViewNoteAdapter listViewNoteAdapter;
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

	static final int DATE_DIALOG_ID = 999;

	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notes_form);

		imageView_awatar = (ImageView) findViewById(R.id.imageView_awatar);
		textView_name = (TextView) findViewById(R.id.textView_name);
		textView_age = (TextView) findViewById(R.id.textView_age);
		textView_date = (TextView) findViewById(R.id.textView_date);
		button_chooseDate = (Button) findViewById(R.id.button_chooseDate);
		listView_notes = (ListView) findViewById(R.id.listView_notes);
		imageButton_add = (ImageButton) findViewById(R.id.imageButton_add);

		textView_name.setText(DataManager.getChildren().getName());

		// Date date = DataManager.getChildren().getBirthdate();
		textView_name.setText(DataManager.getChildren().getName());

		String dateS = "2012-06-05";
		SimpleDateFormat format = new SimpleDateFormat(DataAdapter.DATE_FORMAT);
		try {
			DataManager.getChildren().setBirthdate(format.parse(dateS));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		textView_age.setText(getAge(DataManager.getChildren().getBirthdate()));

		button_chooseDate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});
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
				monthRes = month + " мес€ц";
			else if (("" + month).endsWith("2") | ("" + month).endsWith("3")
					| ("" + month).endsWith("4"))
				monthRes = month + " мес€ца";
			else
				monthRes = month + " мес€цев";
		}

		if (day != 0) {
			if (("" + day).endsWith("1") & day != 11)
				dayRes = day + " день";
			else if (("" + day).endsWith("2") | ("" + day).endsWith("3")
					| ("" + day).endsWith("4"))
				dayRes = day + " дн€";
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

			textView_date.setText(dlg_day + "." + dlg_month + "." + dlg_year);
		}
	};
}
