package com.example.babyprogressmap;

import java.util.HashMap;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import forms.Notifications;

public class NotificationUtils {

	private static final String TAG = NotificationUtils.class.getSimpleName();

	private static NotificationUtils instance;

	private static Context context;
	private NotificationManager manager; // ��������� �������, ����������
											// �������������
	private int lastId = 0; // ��������� ��������������� ����, ���������� �����
							// ������� �����������
	private HashMap<Integer, Notification> notifications; // ������
															// ����-�������� ��
															// ��� ������������
															// ������������
															// �����������

	// ��������� ������������ ��� Singleton
	private NotificationUtils(Context context) {
		this.context = context;
		manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		notifications = new HashMap<Integer, Notification>();
	}

	/**
	 * ��������� ������ �� ��������
	 */
	public static NotificationUtils getInstance(Context context) {
		if (instance == null) {
			instance = new NotificationUtils(context);
		} else {
			instance.context = context;
		}
		return instance;
	}

	public int createInfoNotification(String title, String message) {
		Intent notificationIntent = new Intent(context, Notifications.class); // ��
																				// �����
																				// ��
																				// �����������
																				// ���������
																				// HomeActivity
		NotificationCompat.Builder nb = new NotificationCompat.Builder(context)
				// NotificationCompat.Builder nb = new
				// NotificationBuilder(context) //��� ������ Android > 3.0
				.setSmallIcon(R.drawable.launcher_icon)
				// ������ �����������
				.setAutoCancel(true)
				// ����������� ��������� �� ����� �� ����
				.setTicker(title)
				// �����, ������� ����������� ������ ������-���� ��� ��������
				// �����������
				.setContentText(message)
				// �������� ����� �����������
				.setContentIntent(
						PendingIntent.getActivity(context, 0,
								notificationIntent,
								PendingIntent.FLAG_CANCEL_CURRENT))
				.setWhen(System.currentTimeMillis()) // ������������ �����
														// �����������
				.setContentTitle(title) // ��������� �����������
				.setDefaults(Notification.DEFAULT_ALL); // ����, ����� � �������
														// ���������
														// ������������ ��
														// ���������

		Notification notification = nb.getNotification(); // ����������
															// �����������
		manager.notify(lastId, notification); // ���������� ��� ������������.
		notifications.put(lastId, notification); // ������ �� ����� ���������� �
													// ���� �� id
		return lastId++;
	}
}
