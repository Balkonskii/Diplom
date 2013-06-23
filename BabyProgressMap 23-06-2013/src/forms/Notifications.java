package forms;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.example.babyprogressmap.ActivityEnum;
import com.example.babyprogressmap.AlarmReceiver;
import com.example.babyprogressmap.DataAdapter;
import com.example.babyprogressmap.DataManager;
import com.example.babyprogressmap.ListViewNoticeAdapter;
import com.example.babyprogressmap.Notice;
import com.example.babyprogressmap.R;
import com.example.babyprogressmap.R.id;
import com.example.babyprogressmap.R.layout;
import com.example.babyprogressmap.R.menu;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
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

public class Notifications extends Activity {

	ImageButton imageButton_add;
	ListView listView_notifications;

	DataAdapter dataAdapter;
	ListViewNoticeAdapter listViewNoticeAdapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notifications_form);

		DataManager.getCurrentState().setActivity(this);
		
		imageButton_add = (ImageButton) findViewById(R.id.imageButton_add);
		listView_notifications = (ListView) findViewById(R.id.listView_notifications);

		dataAdapter = new DataAdapter(this);
		if (dataAdapter.isClosed())
			dataAdapter.open();

		listViewNoticeAdapter = new ListViewNoticeAdapter(this);

		showNotifications();

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

		imageButton_add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DataManager.getCurrentState().middleButtonBarButtonClicked(getBaseContext());
			}
		});

		ActionBar aBar = getActionBar();
		aBar.setDisplayShowTitleEnabled(false);
		aBar.setDisplayShowHomeEnabled(false);
		aBar.show();
		getOverflowMenu();
	}

	private void showNotifications() {
		ArrayList<Notice> notes = dataAdapter.getNotices();
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
		int index = DataManager.getNotice().getId() + DataManager.MIN_ALARM_REQUEST_ID;		
		
		Intent intent = new Intent(getBaseContext(),
				AlarmReceiver.class);
		PendingIntent sender = PendingIntent.getBroadcast(
				getBaseContext(), index, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.cancel(sender);
		
		dataAdapter.deleteNotice(DataManager.getNotice());
		showNotifications();
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
		if (!dataAdapter.isClosed())
			dataAdapter.close();
		switch (item.getItemId()) {
		case R.id.notes:
			DataManager.getCurrentState().notesClicked(getBaseContext());
			return true;
		case R.id.change_children_profile:
			DataManager.getCurrentState()
					.changeProfileClicked(getBaseContext());
			return true;
		case R.id.logout:
			DataManager.getCurrentState().logoutClicked(getBaseContext());
			return true;
		case R.id.children_profile:
			DataManager.getCurrentState().viewChildrenProfileClicked(
					getBaseContext());
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@SuppressWarnings("deprecation")
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.edit:
			DataManager.getCurrentState().editNoticeClicked(getBaseContext());
			return true;
		case R.id.delete:
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setTitle("");
			alert.setMessage("Вы действительно хотите удалить выбранное уведомление?");
			alert.setButton("Да", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					deleteChoosedItem();
				}
			});
			alert.setButton2("Нет", new DialogInterface.OnClickListener() {

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
