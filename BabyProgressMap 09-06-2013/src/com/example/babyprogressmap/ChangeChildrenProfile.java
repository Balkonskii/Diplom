package com.example.babyprogressmap;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class ChangeChildrenProfile extends Activity {

	ImageButton imageButton_add;
	// Button button_edit;
	// Button button_delete;
	DataAdapter dataAdapter;
	ListViewChildrenAdapter listViewAdapter;
	ListView listView_children;
	View row;
	Bundle bundle;
	int selectedChildrenId = -1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bundle = savedInstanceState;
		setContentView(R.layout.activity_change_children_profile);

		imageButton_add = (ImageButton) findViewById(R.id.imageButton_add);		
		listView_children = (ListView) findViewById(R.id.listView_children);
		dataAdapter = new DataAdapter(this);
		dataAdapter.open();

		loadAdapterCollection();

		listView_children
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					public boolean onItemLongClick(AdapterView<?> a, View v,
							int position, long id) {
						chooseItem(position);
						return false;
					}
				});

		listView_children.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				chooseItem(position);

			}
		});

		listView_children.setLongClickable(true);
		registerForContextMenu(listView_children);

		imageButton_add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent myIntent = new Intent(getBaseContext(),
						ChildrenProfileActivity.class);
				myIntent.putExtra("isUpdate", false);
				myIntent.putExtra("previousActivity", "ChangeChildrenProfile");
				startActivity(myIntent);
			}
		});

		ActionBar aBar = getActionBar();
		aBar.setDisplayShowTitleEnabled(false);
		aBar.setDisplayShowHomeEnabled(false);
		aBar.show();
		getOverflowMenu();

		Intent intent = getIntent();
		String value = intent.getStringExtra("previousActivity");
		ActivityEnum aEnum = ActivityEnum.valueOf(value);

		switch (aEnum) {
		case ChildrenProfileActivity:
			chooseItem(listViewAdapter.getCount() - 1);
			break;
		}
	}

	private void chooseItem(int position) {
		listViewAdapter.setSelectedPosition(position);
		selectedChildrenId = listViewAdapter.getSelectedChildrenId();
		listView_children.setAdapter(listViewAdapter);

		ArrayList<Children> childrensList = dataAdapter.getChildrens();
		for (Children ch : childrensList) {
			if (ch.getId() == selectedChildrenId) {
				DataManager.setChildren(ch);
			}
		}
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

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu_change_children_profile, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_bar_change_children_profile, menu);
		return true;
	}

	@SuppressLint("ShowToast")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (listViewAdapter.getCount() == 0) {
			Toast.makeText(getApplicationContext(), "Нет ни одного профиля",
					Toast.LENGTH_LONG);
		}
		else if(selectedChildrenId == -1)
			Toast.makeText(getApplicationContext(), "Ничего не выбрано",
					Toast.LENGTH_LONG);
			
		// else if(listViewAdapter.getSelectedPosition() == -1)
		// {
		// Toast.makeText(getApplicationContext(), "Выберите профиль",
		// Toast.LENGTH_LONG);
		// }

		Intent myIntent;
		switch (item.getItemId()) {
		case R.id.children_profile:
			myIntent = new Intent(getBaseContext(), ViewChildrenProfile.class);
			startActivity(myIntent);
			return true;
		case R.id.notes:
			myIntent = new Intent(getBaseContext(), NotesForm.class);
			startActivity(myIntent);
			return true;
		case R.id.logout:

			// TODO

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
			myIntent = new Intent(getBaseContext(),
					ChildrenProfileActivity.class);
			myIntent.putExtra("isUpdate", true);
			startActivity(myIntent);
			return true;
		case R.id.delete:
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setTitle("");
			alert.setMessage("Вы действительно хотите удалить выбранный профиль?");
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

	private void deleteChoosedItem() {
		dataAdapter.deleteChildren(DataManager.getChildren());
		loadAdapterCollection();
	}

	private void loadAdapterCollection() {
		ArrayList<Children> childrens = dataAdapter
				.getChildrensByAccount(DataManager.getAccount());
		listViewAdapter = new ListViewChildrenAdapter(this);
		int selected = -1;
		for (int i = 0; i < childrens.size(); i++) {
			listViewAdapter.addItem(childrens.get(i));
			if (childrens.get(i).getId() == DataManager.getChildren().getId())
				selected = i;
		}

		if (listViewAdapter.getCount() > 0) {
			switch (selected) {
			case -1:
				chooseItem(0);
				break;
			default:
				chooseItem(selected);
			}
		}

		listView_children.setAdapter(listViewAdapter);
		listViewAdapter.notifyDataSetChanged();
	}

	@Override
	public void onBackPressed() {
	}
}
