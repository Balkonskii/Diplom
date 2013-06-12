package com.example.babyprogressmap;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ChildrenProfileActivity extends Activity {

	// ImageButton imgBtn_MakePhoto;
	// ImageButton imgBtn_PickFromGallery;
	ImageView imageView_Awatar;
	ImageButton imageButton_options;

	EditText edit_name;
	EditText edit_surname;
	EditText edit_middlename;
	EditText edit_weight;
	EditText edit_growth;
	DatePicker datepicker_birthdate;
	Button button_ok;
	int index = 0;
	int parentId = 0;
	Bitmap selectedBitmap = null;
	Intent intent;
	private static final int SELECT_PHOTO = 100;
	private static final int CAMERA_REQUEST = 1888;

	boolean isUpdate;

	DataAdapter adapter;

	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_children_profile_hints);

		// imgBtn_MakePhoto = (ImageButton) findViewById(R.id.imgBtn_MakePhoto);
		// imgBtn_PickFromGallery = (ImageButton)
		// findViewById(R.id.imgBtn_PickFromGallery);

		imageButton_options = (ImageButton) findViewById(R.id.imageButton_options);

		imageView_Awatar = (ImageView) findViewById(R.id.imageView_Awatar);

		edit_name = (EditText) findViewById(R.id.edit_name);
		edit_surname = (EditText) findViewById(R.id.edit_surname);
		edit_middlename = (EditText) findViewById(R.id.edit_middlename);
		edit_weight = (EditText) findViewById(R.id.edit_weight);
		edit_growth = (EditText) findViewById(R.id.edit_growth);

		datepicker_birthdate = (DatePicker) findViewById(R.id.datePicker_birthdate);

		button_ok = (Button) findViewById(R.id.button_ok);

		adapter = new DataAdapter(this);
		adapter.open();

		intent = getIntent();

		isUpdate = intent.getBooleanExtra("isUpdate", false);

		if (isUpdate) {
			edit_name.setText(DataManager.getChildren().getName());
			edit_surname.setText(DataManager.getChildren().getSurname());
			edit_middlename.setText(DataManager.getChildren().getMiddlename());
			edit_weight.setText(DataManager.getChildren().getWeight() + "");
			edit_growth.setText(DataManager.getChildren().getGrowth() + "");
			parentId = DataManager.getAccount().getId();
			index = DataManager.getChildren().getId();

			Date date = DataManager.getChildren().getBirthdate();
			
			Calendar calendar = Calendar.getInstance();			
			calendar.setTime(date);
			
			datepicker_birthdate.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);
			
//			datepicker_birthdate.updateDate(date.getYear() - 1900,
//					date.getMonth()-1, date.getDay());

			imageView_Awatar.setImageBitmap(DataManager
					.getImageFromBytes(DataManager.getChildren().getAwatar()));
		}

		button_ok.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				Children children = new Children();
				children.setId(index);

				Bitmap bitmap = ((BitmapDrawable) imageView_Awatar
						.getDrawable()).getBitmap();
				children.setAwatar(DataManager.getImageBytes(bitmap)); // TODO

				Date date = new Date(datepicker_birthdate.getYear() - 1900,
						datepicker_birthdate.getMonth() + 1,
						datepicker_birthdate.getDayOfMonth());

				children.setBirthdate(date);

				try {
					children.setGrowth(Double.parseDouble(edit_growth.getText()
							.toString()));
				} catch (NumberFormatException e) {
					edit_growth.setError(getString(R.string.wrong_value));
					return;
				}

				try {
					children.setWeight(Double.parseDouble(edit_weight.getText()
							.toString()));
				} catch (NumberFormatException e) {
					edit_weight.setError(getString(R.string.wrong_value));
					return;
				}
				children.setMiddlename(edit_middlename.getText().toString());
				children.setName(edit_name.getText().toString());
				children.setSurname(edit_surname.getText().toString());

				children.setParentId(parentId);

				if (isUpdate)
					adapter.updateChildren(children);
				else
					adapter.insertChildren(children);

				DataManager.setChildren(children);

				String value = intent.getStringExtra("previousActivity");
				ActivityEnum aEnum = ActivityEnum.valueOf(value);

				Intent myIntent;
				switch (aEnum) {
				case ChangeChildrenProfile:
					myIntent = new Intent(getBaseContext(),
							ChangeChildrenProfile.class);
					myIntent.putExtra("previousActivity",
							ActivityEnum.ChildrenProfileActivity.toString());
					startActivity(myIntent);
					break;
				case RegistrationActivity:
					myIntent = new Intent(getBaseContext(), NotesForm.class);
					myIntent.putExtra("previousActivity",
							ActivityEnum.ChildrenProfileActivity.toString());
					startActivity(myIntent);
					break;
				case ViewChildrenProfile:
					myIntent = new Intent(getBaseContext(),
							ViewChildrenProfile.class);
					myIntent.putExtra("previousActivity",
							ActivityEnum.ChildrenProfileActivity.toString());
					startActivity(myIntent);
					break;
				}

			}
		});
		
		imageButton_options.setLongClickable(false);
		registerForContextMenu(imageButton_options);

		imageButton_options.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				openContextMenu(v);
			}
		});
	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu_children_profile_edit_image, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.from_camera:
			Intent cameraIntent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(cameraIntent, CAMERA_REQUEST);
			imageView_Awatar.setVisibility(View.VISIBLE);
			return true;
		case R.id.from_gallery:
			Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			photoPickerIntent.setType("image/*");
			startActivityForResult(photoPickerIntent, SELECT_PHOTO);
			imageView_Awatar.setVisibility(View.VISIBLE);
			return true;

		default:
			return true;

		}
		// return super.onContextItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent imageReturnedIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
		switch (requestCode) {

		case CAMERA_REQUEST:
			if (resultCode == RESULT_OK) {
				selectedBitmap = (Bitmap) imageReturnedIntent.getExtras().get(
						"data");
			}
			break;
		case SELECT_PHOTO:
			if (resultCode == RESULT_OK) {
				Uri selectedImage = imageReturnedIntent.getData();
				InputStream imageStream = null;
				try {
					imageStream = getContentResolver().openInputStream(
							selectedImage);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				selectedBitmap = BitmapFactory.decodeStream(imageStream);
			}
			break;
		}

		if (resultCode == RESULT_OK) {
			imageView_Awatar.setImageBitmap(selectedBitmap);
			imageView_Awatar.refreshDrawableState();
		}
	}

	@Override
    public void onConfigurationChanged(Configuration newConfig) {
      super.onConfigurationChanged(newConfig);

    }
	
	@Override
	public void onBackPressed() {
	}
}