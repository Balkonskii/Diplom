package com.example.babyprogressmap;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ChildrenProfileActivity extends Activity {

	ImageButton imgBtn_MakePhoto;
	ImageButton imgBtn_PickFromGallery;
	ImageView imageView_Awatar;

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

	DataAdapter adapter;

	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_children_profile_hints);

		imgBtn_MakePhoto = (ImageButton) findViewById(R.id.imgBtn_MakePhoto);
		imgBtn_PickFromGallery = (ImageButton) findViewById(R.id.imgBtn_PickFromGallery);

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
		if (intent.getStringExtra("isUpdate").equals("true")) {
			// edit_name.setText(intent.getStringExtra("name"));
			// edit_surname.setText(intent.getStringExtra("surname"));
			// edit_middlename.setText(intent.getStringExtra("middlename"));
			// edit_weight.setText(intent.getDoubleExtra("weight", 0) + "");
			// edit_growth.setText(intent.getDoubleExtra("growth", 0) + "");
			// index = intent.getIntExtra("id", 0);
			// SimpleDateFormat format = new SimpleDateFormat(
			// DataAdapter.DATE_FORMAT);
			// Date date = new Date();
			// try {
			// date = format.parse(intent.getStringExtra("birthdate"));
			// } catch (ParseException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			//
			// datepicker_birthdate.init(date.getYear(), date.getMonth(),
			// date.getDate(), new DatePicker.OnDateChangedListener() {
			// @Override
			// public void onDateChanged(DatePicker view, int year,
			// int monthOfYear, int dayOfMonth) {
			//
			// }
			// });
			//
			// parentId = intent.getIntExtra("parentId", 0);

			edit_name.setText(DataManager.getChildren().getName());
			edit_surname.setText(DataManager.getChildren().getSurname());
			edit_middlename.setText(DataManager.getChildren().getName());
			edit_weight.setText(DataManager.getChildren().getWeight() + "");
			edit_growth.setText(DataManager.getChildren().getGrowth() + "");
			parentId = DataManager.getChildren().getParentId();
			index = DataManager.getChildren().getId();

			Date date = DataManager.getChildren().getBirthdate();

			datepicker_birthdate.init(date.getYear(), date.getMonth(),
					date.getDate(), new DatePicker.OnDateChangedListener() {
						@Override
						public void onDateChanged(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {

						}
					});

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

				Date date = new Date();
				date.setDate(datepicker_birthdate.getDayOfMonth());
				date.setMonth(datepicker_birthdate.getMonth());
				date.setYear(datepicker_birthdate.getYear());

				children.setBirthdate(date);
				children.setGrowth(Double.parseDouble(edit_growth.getText()
						.toString()));
				children.setWeight(Double.parseDouble(edit_weight.getText()
						.toString()));
				children.setMiddlename(edit_middlename.getText().toString());
				children.setName(edit_name.getText().toString());
				children.setSurname(edit_surname.getText().toString());

				children.setParentId(parentId);

				if (getIntent().getStringExtra("isUpdate").equals("true"))
					adapter.updateChildren(children);
				else
					adapter.insertChildren(children);

				DataManager.setChildren(children);

				String value = intent.getStringExtra("previousActivity");
				ActivityEnum aEnum = ActivityEnum.valueOf(value);
				switch (aEnum) {
				case ChangeChildrenProfile:
					Intent myIntent = new Intent(getBaseContext(),
							ChangeChildrenProfile.class);				
					startActivity(myIntent);
					break;
				}

			}
		});

		imgBtn_PickFromGallery.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, SELECT_PHOTO);
			}
		});

		imgBtn_MakePhoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent cameraIntent = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(cameraIntent, CAMERA_REQUEST);
			}
		});
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
}