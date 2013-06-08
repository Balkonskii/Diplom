package com.example.babyprogressmap;

import java.util.ArrayList;

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
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class ChangeChildrenProfile extends Activity {

	ImageButton imageButton_add;
//	Button button_edit;
//	Button button_delete;
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
//		button_edit = (Button) findViewById(R.id.button_edit);
//		button_delete = (Button) findViewById(R.id.button_delete);
		listView_children = (ListView) findViewById(R.id.listView_children);
		dataAdapter = new DataAdapter(this);
		dataAdapter.open();

		ArrayList<Children> childrens = dataAdapter.getChildrens();
		// ////////////////////////////////////////// debug
//		Bitmap.Config conf = Bitmap.Config.ARGB_8888;
//		Bitmap bmp = Bitmap.createBitmap(200, 200, conf);
//
//		Bitmap.Config conf1 = Bitmap.Config.ARGB_8888;
//		Bitmap bmp1 = Bitmap.createBitmap(200, 200, conf);
//
//		Children child1 = new Children();
//		child1.setName("Name1");
//		child1.setId(5);
//		child1.setAwatar(DataManager.getImageBytes(bmp));
//		Children child2 = new Children();
//		child2.setName("Name2");
//		child2.setId(10);
//		child2.setAwatar(DataManager.getImageBytes(bmp1));
//
//		Children[] childrens = { child1, child2 };
		// //////////////////////////////////////////
		listViewAdapter = new ListViewChildrenAdapter(this);
		//DataManager.getChildren().setId(5);

		int selected = 0;
		for (int i = 0; i < childrens.size(); i++) {
			listViewAdapter.addItem(childrens.get(i));
			if (childrens.get(i).getId() == DataManager.getChildren().getId())
				selected = i;
		}
		listViewAdapter.setSelectedPosition(selected);

		listView_children.setAdapter(listViewAdapter);

		listView_children.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {

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

//		button_edit.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				Intent myIntent = new Intent(getBaseContext(),
//						ChildrenProfileActivity.class);
//				myIntent.putExtra("isUpdate", "true");
//				myIntent.putExtra("previousActivity", "ChangeChildrenProfile");
//				startActivity(myIntent);
//			}
//		});
//
//		button_delete.setOnClickListener(new View.OnClickListener() {
//
//			@SuppressWarnings("deprecation")
//			@Override
//			public void onClick(View arg0) {
//
//				AlertDialog alert = new AlertDialog.Builder(
//						ChangeChildrenProfile.this.getApplicationContext())
//						.create();
//				alert.setTitle("");
//				alert.setMessage("Удалить выделенный профиль?");
//				alert.setButton("Да", new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						dataAdapter.deleteChildren(DataManager.getChildren());
//						onCreate(bundle);
//					}
//				});
//				alert.setButton2("Нет", new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// ...
//					}
//				});
//				alert.show();
//
//			}
//		});
		
		ActionBar aBar = getActionBar();
		aBar.setDisplayShowTitleEnabled(false);
		aBar.setDisplayShowHomeEnabled(false);
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
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent myIntent;
	    switch (item.getItemId()) {
	        case R.id.children_profile:
	        	myIntent = new Intent(getBaseContext(),
						ViewChildrenProfile.class);					
				startActivity(myIntent);	
	            return true;	            
	        case R.id.notes:
	        	myIntent = new Intent(getBaseContext(),
						NotesForm.class);					
				startActivity(myIntent);	
	        	return true;
	        case R.id.logout:
	        	
	        	//TODO
	        	
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
			alert.setButton("Да", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {               														
				dataAdapter.deleteChildren(DataManager.getChildren());				
			}
			});
			alert.setButton2("Нет", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
			//...
			}
			});
			alert.show();
		
			return true;
		}
		return super.onContextItemSelected(item);
	}
}
