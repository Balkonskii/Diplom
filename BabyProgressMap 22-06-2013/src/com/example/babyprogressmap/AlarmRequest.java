package com.example.babyprogressmap;

import android.content.Context;

public class AlarmRequest {
	private static int requestId = -1;

	public static void init(Context context) {
		DataAdapter dataAdapter = new DataAdapter(context);
		dataAdapter.open();

		requestId = dataAdapter.getMaxNoticeId() + 1;
	}

	public static int getNextRequest() {
		return requestId++;
	}
}
