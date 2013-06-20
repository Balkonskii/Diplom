package forms;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;

import com.example.babyprogressmap.ActivityEnum;
import com.example.babyprogressmap.DataAdapter;
import com.example.babyprogressmap.DataManager;
import com.example.babyprogressmap.Note;
import com.example.babyprogressmap.R;
import com.example.babyprogressmap.R.id;
import com.example.babyprogressmap.R.layout;
import com.example.babyprogressmap.R.menu;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewNote extends Activity {

	ImageView imageView_photo;
	TextView textView_tittle;
	TextView textView_description;
	Button button_edit;
	Button button_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_note);

		DataManager.getCurrentState().setActivity(this);
		
		imageView_photo = (ImageView) findViewById(R.id.imageView_photo);
		textView_tittle = (TextView) findViewById(R.id.textView_tittle);
		textView_description = (TextView) findViewById(R.id.textView_description);
		button_edit = (Button) findViewById(R.id.button_edit);
		button_back = (Button) findViewById(R.id.button_back);
		
		Note note = DataManager.getNote();
		// Note note = new Note();
		// note.setTitle("title");
		// note.setDescription("asdasdasdjlshdddddddddddddddddddddddddddddddasdas;ldka;sldkasljdkajshdlksjahfljasdhlkjasdhfad");
		//
		// Bitmap bmp1 = BitmapFactory.decodeResource(getResources(),
		// R.drawable.add);
		//
		// note.setPhoto(DataManager.getImageBytes(bmp1));
		// //note.hasImage = false;

		
		textView_tittle.setText(note.getTitle());
		textView_description.setText(note.getDescription());

		if (note.hasImage) {
			Bitmap bmp = DataManager.getImageFromBytes(note.getPhoto());
			imageView_photo.setImageBitmap(bmp);
		} else
			imageView_photo.setVisibility(View.GONE);

		button_edit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
//				Intent myIntent = new Intent(getBaseContext(), EditNote.class);
//				myIntent.putExtra("previousActivity",
//						ActivityEnum.ViewNote.toString());
//				myIntent.putExtra("isUpdate", true);
//				startActivity(myIntent);
				DataManager.getCurrentState().leftButtonBarButtonClicked(getBaseContext());
			}
		});

		button_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {			
//				String value = getIntent().getStringExtra("previousActivity");
//				ActivityEnum aEnum = ActivityEnum.valueOf(value);
//
//				Intent myIntent;
//
//				switch (aEnum) {
////				case ChangeChildrenProfile:
////					myIntent = new Intent(getBaseContext(),
////							ChangeChildrenProfile.class);
////					myIntent.putExtra("previousActivity",
////							ActivityEnum.ViewNote.toString());
////					startActivity(myIntent);
////					break;
//				case Notes:
//					myIntent = new Intent(getBaseContext(), Notes.class);
//					myIntent.putExtra("previousActivity",
//							ActivityEnum.ViewNote.toString());
//					startActivity(myIntent);
//					break;
//				case EditNote:
//					myIntent = new Intent(getBaseContext(),
//							Notes.class);
//					myIntent.putExtra("previousActivity",
//							ActivityEnum.ViewNote.toString());
//					startActivity(myIntent);
//					break;
//				}
				
				DataManager.getCurrentState().rightButtonBarButtonClicked(getBaseContext());
			}
		});
		
		ActionBar aBar = getActionBar();
		aBar.setDisplayShowTitleEnabled(false);
		aBar.setDisplayShowHomeEnabled(false);
		aBar.show();
		getOverflowMenu();
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_bar_view_note, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent myIntent;

		switch (item.getItemId()) {
		case R.id.notes:
			myIntent = new Intent(getBaseContext(), Notes.class);
			myIntent.putExtra("previousActivity",
					ActivityEnum.ViewNote.toString());
			startActivity(myIntent);
			return true;
		case R.id.change_children_profile:
			myIntent = new Intent(getBaseContext(), ChangeChildrenProfile.class);
			myIntent.putExtra("previousActivity",
					ActivityEnum.ViewNote.toString());
			startActivity(myIntent);
			return true;
		case R.id.logout:

			DataManager.reset();
			myIntent = new Intent(getBaseContext(), Login.class);
			myIntent.putExtra("previousActivity",
					ActivityEnum.Notes.toString());
			startActivity(myIntent);

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onBackPressed() {
	}
}
