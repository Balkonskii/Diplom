package forms;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.babyprogressmap.AlarmReceiver;
import com.example.babyprogressmap.DataAdapter;
import com.example.babyprogressmap.DataManager;
import com.example.babyprogressmap.MyAsyncTask;
import com.example.babyprogressmap.Notice;
import com.example.babyprogressmap.R;

public class EditNotice extends Activity {

	EditText editText_title;
	EditText editText_description;
	DatePicker datePicker_postdate;
	TimePicker timePicker_posttime;
	Button button_ok;
	Button button_back;

	int index = -1;

	DataAdapter dataAdapter;
	boolean isUpdate = false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_notice);

		DataManager.getCurrentState().setActivity(this);

		dataAdapter = new DataAdapter(this);
		dataAdapter.open();

		editText_title = (EditText) findViewById(R.id.editText_title);
		editText_description = (EditText) findViewById(R.id.editText_description);
		datePicker_postdate = (DatePicker) findViewById(R.id.datePicker_postdate);

		timePicker_posttime = (TimePicker) findViewById(R.id.timePicker_posttime);
		timePicker_posttime.setIs24HourView(true);

		button_ok = (Button) findViewById(R.id.button_ok);
		button_back = (Button) findViewById(R.id.button_back);

		Intent intent = getIntent();
		isUpdate = intent.getBooleanExtra("isUpdate", false);

		if (isUpdate) {
			Notice notice = DataManager.getNotice();

			index = notice.getId();

			editText_title.setText(notice.getTitle());
			editText_description.setText(notice.getDescription());

			Date postdate = notice.getNotifyDateTime();

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(postdate);

			datePicker_postdate.init(calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH), null);

			timePicker_posttime.setCurrentHour(calendar
					.get(Calendar.HOUR_OF_DAY));
			timePicker_posttime.setCurrentMinute(calendar.get(Calendar.MINUTE));
		}
		else
		{
			Calendar calendar = Calendar.getInstance();
			timePicker_posttime.setCurrentHour(calendar
					.get(Calendar.HOUR_OF_DAY));
			timePicker_posttime.setCurrentMinute(calendar.get(Calendar.MINUTE));
		}

		button_ok.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				Notice notice = new Notice();
				notice.setId(index);

				notice.setDescription(editText_description.getText().toString());
				notice.setTitle(editText_title.getText().toString());

				Calendar cal = Calendar.getInstance();			
				cal.setFirstDayOfWeek(Calendar.MONDAY);
				cal.set(Calendar.HOUR_OF_DAY, timePicker_posttime.getCurrentHour());
				cal.set(Calendar.MINUTE, timePicker_posttime.getCurrentMinute());
				cal.set(Calendar.YEAR, datePicker_postdate.getYear());
				cal.set(Calendar.MONTH, datePicker_postdate.getMonth());
				cal.set(Calendar.DAY_OF_MONTH,
						datePicker_postdate.getDayOfMonth());

				Date date = cal.getTime();

				// = new Date(datePicker_postdate.getYear() - 1900,
				// datePicker_postdate.getMonth(), datePicker_postdate
				// .getDayOfMonth(), timePicker_posttime
				// .getCurrentHour(), timePicker_posttime
				// .getCurrentMinute());
				notice.setNotifyDateTime(date);

				notice.setChildrenId(DataManager.getChildren().getId());

				DataManager.setNotice(notice);

				if (isUpdate) {
					dataAdapter.updateNotice(notice);
					index = index + DataManager.MIN_ALARM_REQUEST_ID;
				} else {
					long addedId = dataAdapter.insertNotice(notice);
					index = DataManager.MIN_ALARM_REQUEST_ID + (int) addedId;
				}

				// добавление уведомления с инициацией сообщения в статус баре
				Intent intent = new Intent(getBaseContext(),
						AlarmReceiver.class);
				intent.putExtra("alarm_message", notice.getDescription());
				intent.putExtra("title", notice.getTitle());

				PendingIntent sender = PendingIntent.getBroadcast(
						getBaseContext(), index, intent,
						PendingIntent.FLAG_UPDATE_CURRENT);

				AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
				am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);

				DataManager.getCurrentState().leftButtonBarButtonClicked(
						getBaseContext());
			}
		});

		button_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				DataManager.getCurrentState().rightButtonBarButtonClicked(
						getBaseContext());
			}
		});
	}

	@Override
	public void onBackPressed() {
	}
}
