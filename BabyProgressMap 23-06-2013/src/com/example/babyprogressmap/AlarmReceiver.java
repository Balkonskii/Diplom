package com.example.babyprogressmap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Bundle bundle = intent.getExtras();
			String message = bundle.getString("alarm_message");
			String title = bundle.getString("title");

			// NotificationUtils.getInstance(context).createInfoNotification(title,
			// message);
			new NotificationAsyncTask(context).doInBackground(title, message);
		} catch (Exception e) {
//			

		}
	}

}