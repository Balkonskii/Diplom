package com.example.babyprogressmap;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class EditNote extends Activity {
	private static final int SELECT_PHOTO = 100;
	private static final int CAMERA_REQUEST = 1888;
	Bitmap selectedBitmap = null;
	DataAdapter adapter;

	boolean hasImage = false;
	ImageView imageView_photo;
	ImageButton imageButton_options;
	EditText editText_title;
	EditText editText_description;
	Button button_ok;

	Intent intent;
	boolean isUpdate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_note);

		adapter = new DataAdapter(this);
		adapter.open();

		imageView_photo = (ImageView) findViewById(R.id.imageView_photo);
		imageButton_options = (ImageButton) findViewById(R.id.imageButton_options);
		editText_title = (EditText) findViewById(R.id.editText_title);
		editText_description = (EditText) findViewById(R.id.editText_description);
		button_ok = (Button) findViewById(R.id.button_ok);

		imageButton_options.setLongClickable(false);
		registerForContextMenu(imageButton_options);

		imageButton_options.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				openContextMenu(v);
			}
		});

		intent = getIntent();
		intent.putExtra("isUpdate", false);
		isUpdate = intent.getBooleanExtra("isUpdate", false);

		if (isUpdate) {
			Note currentNote = DataManager.getNote();
			hasImage = currentNote.hasImage;
			if (currentNote.hasImage)
				imageView_photo.setImageBitmap(DataManager
						.getImageFromBytes(currentNote.getPhoto()));
			else
				imageView_photo.setVisibility(View.GONE);

			editText_description.setText(currentNote.getDescription());
			editText_title.setText(currentNote.getTitle());
		}

		button_ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Note note = DataManager.getNote();

				note.setChildrenId(DataManager.getChildren().getId());
				note.setDescription(editText_description.getText().toString());
				note.setTitle(editText_title.getText().toString());

				note.hasImage = hasImage;
				if (hasImage) {
					Bitmap bitmap = ((BitmapDrawable) imageView_photo
							.getDrawable()).getBitmap();
					note.setPhoto(DataManager.getImageBytes(bitmap));
				}

				if (isUpdate) {
					adapter.updateNote(note);
				} else
					adapter.insertNote(note);
				
				//return back
			}
		});

	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu_edit_image_options, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.from_camera:
			Intent cameraIntent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(cameraIntent, CAMERA_REQUEST);
			imageView_photo.setVisibility(View.VISIBLE);
			return true;
		case R.id.from_gallery:
			Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			photoPickerIntent.setType("image/*");
			startActivityForResult(photoPickerIntent, SELECT_PHOTO);
			imageView_photo.setVisibility(View.VISIBLE);
			return true;
		case R.id.without_image:
			
			if(imageView_photo.getVisibility() == View.GONE)
				imageView_photo.setVisibility(View.VISIBLE);
			else
				imageView_photo.setVisibility(View.GONE);
			item.setChecked(!item.isChecked());
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
			imageView_photo.setImageBitmap(selectedBitmap);
			imageView_photo.refreshDrawableState();
		}
	}

}
