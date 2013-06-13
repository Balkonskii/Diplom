package com.example.babyprogressmap;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<String, Void, String> {
    private Context context;
    public MyAsyncTask(Context context) {
        this.context = context;
    }
    
    private String message = "";
    
    
    @Override
    protected String doInBackground(String... params) {
    	this.message = params[0];
      return "";
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
