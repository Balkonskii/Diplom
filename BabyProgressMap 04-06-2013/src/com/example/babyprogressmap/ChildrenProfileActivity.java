package com.example.babyprogressmap;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
	DatePicker datePicker_birthdate;
	Button button_ok;
	
	Bitmap selectedBitmap = null;
	
	private static final int SELECT_PHOTO = 100;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_children_profile);
		
		imgBtn_MakePhoto = (ImageButton)findViewById(R.id.imgBtn_MakePhoto);
		imgBtn_PickFromGallery = (ImageButton)findViewById(R.id.imgBtn_PickFromGallery);
		
		imageView_Awatar = (ImageView)findViewById(R.id.imageView_Awatar);
		
		edit_name = (EditText)findViewById(R.id.edit_name);
		edit_surname = (EditText)findViewById(R.id.edit_surname);
		edit_middlename = (EditText)findViewById(R.id.edit_middlename);
		edit_weight = (EditText)findViewById(R.id.edit_weight);
		edit_growth = (EditText)findViewById(R.id.edit_growth);
		
		datePicker_birthdate = (DatePicker)findViewById(R.id.datePicker_birthdate);
		
		button_ok = (Button)findViewById(R.id.button_ok);
		
		button_ok.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		imgBtn_PickFromGallery.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, SELECT_PHOTO);    
				
				if(selectedBitmap != null)
				{
					imageView_Awatar.setImageBitmap(selectedBitmap);
					imageView_Awatar.refreshDrawableState();
				}
			}
		});
		
		imgBtn_MakePhoto.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
	    super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

	    switch(requestCode) { 
	    case SELECT_PHOTO:
	        if(resultCode == RESULT_OK){  
	            Uri selectedImage = imageReturnedIntent.getData();
	            InputStream imageStream = null;
				try {
					imageStream = getContentResolver().openInputStream(selectedImage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				selectedBitmap = BitmapFactory.decodeStream(imageStream);
	        }
	    }
	}
}