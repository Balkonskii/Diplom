package forms;

import java.util.Calendar;

import com.example.babyprogressmap.MyAsyncTask;
import com.example.babyprogressmap.R;
import com.example.babyprogressmap.R.id;
import com.example.babyprogressmap.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;

public class ChoosingDateDialog extends Activity {

//	ImageButton imageButton_ok;
//	ImageButton imageButton_cancel;
	
	Button button_ok;
	Button button_cancel;
	
	CalendarView calendarView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choosing_date);

		button_ok = (Button) findViewById(R.id.button_ok);
		button_cancel = (Button) findViewById(R.id.button_cancel);
		calendarView = (CalendarView) findViewById(R.id.calendarView);

		calendarView.setFirstDayOfWeek(Calendar.MONDAY);
		
		button_ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(calendarView.getDate());
				
				String tr = calendar.get(Calendar.DAY_OF_MONTH) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.YEAR);
				
				new MyAsyncTask(getApplicationContext())
						.execute(tr);
			}
		});
		button_cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}

}
