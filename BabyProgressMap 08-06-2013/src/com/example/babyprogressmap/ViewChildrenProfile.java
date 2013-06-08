package com.example.babyprogressmap;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewChildrenProfile extends Activity{

	TextView textView_nameValue;
	TextView textView_surnameValue;
	TextView textView_middlenameValue;
	TextView textView_weightValue;
	TextView textView_growthValue;
	TextView textView_birthdateValue;
	ImageView imageView_Awatar;
	
	Button button_edit;
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_children_profile_view);
				
		textView_nameValue = (TextView)findViewById(R.id.textView_nameValue);		
		textView_surnameValue = (TextView)findViewById(R.id.textView_surnameValue);
		textView_middlenameValue = (TextView)findViewById(R.id.textView_middlenameValue);
		textView_weightValue = (TextView)findViewById(R.id.textView_weightValue);
		textView_growthValue = (TextView)findViewById(R.id.textView_growthValue);
		textView_birthdateValue = (TextView)findViewById(R.id.textView_birthdateValue);
		imageView_Awatar = (ImageView)findViewById(R.id.imageView_Awatar);		
		button_edit = (Button)findViewById(R.id.button_edit); 
		
		textView_nameValue.setText(DataManager.getChildren().getName());
		textView_surnameValue.setText(DataManager.getChildren().getSurname());
		textView_middlenameValue.setText(DataManager.getChildren().getMiddlename());
		textView_weightValue.setText(DataManager.getChildren().getWeight()+"");
		textView_growthValue.setText(DataManager.getChildren().getGrowth()+"");
		
		Date date = DataManager.getChildren().getBirthdate();	
		SimpleDateFormat format = new SimpleDateFormat(DataAdapter.DATE_FORMAT_RUS); 		
		textView_birthdateValue.setText(format.format(date));
		
		Bitmap bmp = DataManager.getImageFromBytes(DataManager.getChildren().getAwatar());
		
		imageView_Awatar.setImageBitmap(bmp);
		
		button_edit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(getBaseContext(),
						ChildrenProfileActivity.class);				
				myIntent.putExtra("isUpdate", true);
				
				startActivity(myIntent);	
			}
		});
	}
	
}
