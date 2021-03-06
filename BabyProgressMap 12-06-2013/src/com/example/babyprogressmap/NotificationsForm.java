package com.example.babyprogressmap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;

public class NotificationsForm extends Activity {

	TextView textView_date;
	Button button_chooseDate;
	ImageButton imageButton_add;
	ListView listView_notifications;

	DataAdapter dataAdapter;
	ListViewNoticeAdapter listViewNoticeAdapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notifications_form);

		textView_date = (TextView) findViewById(R.id.textView_date);
		button_chooseDate = (Button) findViewById(R.id.button_chooseDate);
		imageButton_add = (ImageButton) findViewById(R.id.imageButton_add);
		listView_notifications = (ListView) findViewById(R.id.listView_notifications);

		dataAdapter = new DataAdapter(this);
		if (dataAdapter.isClosed())
			dataAdapter.open();

		listViewNoticeAdapter = new ListViewNoticeAdapter(this);

		Calendar cal = Calendar.getInstance();

		Date now2 = new Date(cal.get(Calendar.YEAR) - 1900,
				cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));

		showNotifications(now2);

		registerForContextMenu(listView_notifications);
		listView_notifications
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long id) {
						DataManager.setNotice(listViewNoticeAdapter
								.getCollection().get(position));
					}
				});

		listView_notifications.setLongClickable(true);
		listView_notifications
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> arg0,
							View arg1, int position, long id) {
						DataManager.setNotice(listViewNoticeAdapter
								.getCollection().get(position));
						return false;
					}
				});

		button_chooseDate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//TODO startActivityForResult
			}
		});

		imageButton_add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(getBaseContext(), EditNotice.class);
				myIntent.putExtra("isUpdate", true);
				myIntent.putExtra("previousActivity",
						ActivityEnum.NotificationsForm.toString());
				startActivity(myIntent);
			}
		});

		ActionBar aBar = getActionBar();
		aBar.setDisplayShowTitleEnabled(false);
		aBar.setDisplayShowHomeEnabled(false);
		aBar.show();
		getOverflowMenu();
	}

	private void showNotifications(Date date) {
		ArrayList<Notice> notes = new ArrayList<Notice>();
		listViewNoticeAdapter.getCollection().clear();

		for (Notice notice : notes) {
			listViewNoticeAdapter.addItem(notice);
		}

		listView_notifications.setAdapter(listViewNoticeAdapter);
		listViewNoticeAdapter.notifyDataSetChanged();
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

	private void deleteChoosedItem() {
		dataAdapter.deleteNotice(DataManager.getNotice());
		showNotifications(DataManager.getNotice().getNotifyDateTime());
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu_change_notice, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_bar_notifications_form, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent myIntent;
		if (!dataAdapter.isClosed())
			dataAdapter.close();
		switch (item.getItemId()) {
		case R.id.notes:
			myIntent = new Intent(getBaseContext(), NotesForm.class);
			myIntent.putExtra("previousActivity",
					ActivityEnum.NotificationsForm.toString());
			startActivity(myIntent);
			return true;
		case R.id.change_children_profile:
			myIntent = new Intent(getBaseContext(), ChangeChildrenProfile.class);
			myIntent.putExtra("previousActivity",
					ActivityEnum.NotificationsForm.toString());
			startActivity(myIntent);
			return true;
		case R.id.logout:
			DataManager.reset();
			myIntent = new Intent(getBaseContext(), MyLoginActivity.class);
			myIntent.putExtra("previousActivity",
					ActivityEnum.NotificationsForm.toString());
			startActivity(myIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@SuppressWarnings("deprecation")
	public boolean onContextItemSelected(MenuItem item) {
		Intent myIntent;

		switch (item.getItemId()) {

		case R.id.edit:
			myIntent = new Intent(getBaseContext(), EditNotice.class);
			myIntent.putExtra("isUpdate", true);
			myIntent.putExtra("previousActivity",
					ActivityEnum.NotificationsForm.toString());
			startActivity(myIntent);
			return true;
		case R.id.delete:
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setTitle("");
			alert.setMessage("�� ������������� ������ ������� ��������� �����������?");
			alert.setButton("��", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					deleteChoosedItem();
				}
			});
			alert.setButton2("���", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// ...
				}
			});
			alert.show();

			return true;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onBackPressed() {
	}
}
