package com.example.babyprogressmap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewNote extends Activity{
	
	ImageView imageView_photo;
	TextView textView_tittle;
	TextView textView_description;
	
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_note);
		
		imageView_photo = (ImageView)findViewById(R.id.imageView_photo);
		textView_tittle = (TextView)findViewById(R.id.textView_tittle);
		textView_description = (TextView)findViewById(R.id.textView_description);
		
		Note note = DataManager.getNote();
//		Note note = new Note();
//		note.setTitle("title");
//		note.setDescription("asdasdasdjlshdddddddddddddddddddddddddddddddasdas;ldka;sldkasljdkajshdlksjahfljasdhlkjasdhfad");
//		
//		Bitmap bmp1 = BitmapFactory.decodeResource(getResources(),
//				R.drawable.add);
//		
//		note.setPhoto(DataManager.getImageBytes(bmp1));
//		//note.hasImage = false;
		
		textView_tittle.setText(note.getTitle());
		textView_description.setText(note.getDescription());
		
		if(note.hasImage)
		{
		Bitmap bmp = DataManager.getImageFromBytes(note.getPhoto());
		imageView_photo.setImageBitmap(bmp);
		}
		else
			imageView_photo.setVisibility(View.GONE);
	}
}
