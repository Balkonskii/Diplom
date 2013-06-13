package com.example.babyprogressmap;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MyLoginActivity extends Activity {

	EditText email;
	EditText password;
	Button sign_in_button;

	String login;
	String pass;

	DataAdapter adapter;
	AlertDialog.Builder alert;

	private final int _passwordLength = 6;
	boolean registered = false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		email = (EditText) findViewById(R.id.email);
		password = (EditText) findViewById(R.id.password);

		adapter = new DataAdapter(this);
		if (adapter.isClosed())
			adapter.open();

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						if (adapter.isClosed())
							adapter.open();

						attemptLogin();

					}

				});
	}

	private void attemptLogin() {
		boolean registered = false;
		boolean verifiedPass = false;

		login = email.getText().toString();
		pass = password.getText().toString();

		if (!isPasswordValid(pass)) {
			password.setError("Пароль должен быть не меньше " + _passwordLength
					+ " символов");
			return;
		}

		Account acc = adapter.getAccountByLogin(login);
		if (acc.getId() != -1) {
			registered = true;
		}

		if (acc.getPassword().equals(pass)) {
			verifiedPass = true;
		}

		if (registered & verifiedPass) {
			ArrayList<Children> list = adapter.getChildrensByAccount(acc);
			Intent myIntent;
			DataManager.setAccount(acc);
			if (!adapter.isClosed())
				adapter.close();
			myIntent = new Intent(getBaseContext(), ChangeChildrenProfile.class);
			myIntent.putExtra("isUpdate", false);
			myIntent.putExtra("previousActivity",
					ActivityEnum.LoginActivity.toString());
			startActivity(myIntent);
			// ////////////////////////////////
			// String debug = "login id = " + acc.getId() + "			";
			//
			// ArrayList<Children> child = adapter.getChildrens();
			// for(Children ch : child)
			// {
			// debug += "(Id = "+ ch.getId()+" Pid = " + ch.getParentId() + ")";
			// }
			//
			// email.setText(debug);
			// //////////////////////////////
			// switch (list.size()) {
			// case 0:
			// myIntent = new Intent(getBaseContext(),
			// ChildrenProfileActivity.class);
			// myIntent.putExtra("isUpdate", false);
			// myIntent.putExtra("previousActivity",
			// ActivityEnum.LoginActivity.toString());
			// startActivity(myIntent);
			// break;
			// case 1:
			// DataManager.setChildren(list.get(0));
			// myIntent = new Intent(getBaseContext(),
			// ViewChildrenProfile.class);
			// myIntent.putExtra("previousActivity",
			// ActivityEnum.LoginActivity.toString());
			// startActivity(myIntent);
			// break;
			// default:
			// myIntent = new Intent(getBaseContext(),
			// ChangeChildrenProfile.class);
			// myIntent.putExtra("previousActivity",
			// ActivityEnum.LoginActivity.toString());
			// startActivity(myIntent);
			// break;
			// }
		} else if (!registered) {
			alert = new AlertDialog.Builder(MyLoginActivity.this);
			alert.setTitle("");
			alert.setMessage("Введенной учетной записи не существует. Зарегистрировать её?");
			alert.setPositiveButton("Да",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							startRegistration();

						}

					});
			alert.setNegativeButton("Нет",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			alert.create().show();
		} else if (!verifiedPass) {
			password.setError("Неверный пароль");
		}

	}

	private void startRegistration() {
		adapter.close();
		Intent myIntent = new Intent(getBaseContext(),
				RegistrationActivity.class);
		myIntent.putExtra("login", login);
		myIntent.putExtra("previousActivity",
				ActivityEnum.LoginActivity.toString());
		startActivity(myIntent);
	}

	private boolean isPasswordValid(String pass) {
		return pass.length() >= _passwordLength;
	}
}
