package com.example.babyprogressmap;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class NotificationAsyncTask extends AsyncTask<String, Void, String> {
	private Context context;

	public NotificationAsyncTask(Context context) {
		this.context = context;
	}

	/**
	 * @param 0 = title
	 * @param 1 = description
	 * */

	@Override
	protected String doInBackground(String... params) {
		NotificationUtils.getInstance(context).createInfoNotification(
				params[0], params[1]);
		return "";
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		// Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}
}
