package com.example.babyprogressmap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

public class RegistrationActivity extends Activity {

	private EditText edit_name;
	private EditText edit_surname;
	private EditText edit_middlename;
	private EditText edit_login;
	private EditText edit_password;
	private EditText edit_passwordConfirm;
	private DatePicker datepicker_birthdate;
	private Button button_ok;
	private LinearLayout loginLayout;
	BabyProgressDataBaseHelper helper;
	int index = 0;
	DataAdapter adapter;
	private CheckBox checkBox_showPassword;

	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_registration);
		// helper = BabyProgressDataBaseHelper.getInstance(this);
		edit_name = (EditText) findViewById(R.id.edit_name);
		edit_surname = (EditText) findViewById(R.id.edit_surname);
		edit_middlename = (EditText) findViewById(R.id.edit_middlename);
		edit_login = (EditText) findViewById(R.id.edit_login);
		edit_password = (EditText) findViewById(R.id.edit_password);
		edit_passwordConfirm = (EditText) findViewById(R.id.edit_passwordConfirm);
		datepicker_birthdate = (DatePicker) findViewById(R.id.datePicker_birthdate);
		button_ok = (Button) findViewById(R.id.button_ok);
		checkBox_showPassword = (CheckBox) findViewById(R.id.checkBox_showPassword);
		loginLayout = (LinearLayout) findViewById(R.id.loginLayout);
		adapter = new DataAdapter(this);
		adapter.open();

		Intent intent = getIntent();
		edit_login.setText(intent.getStringExtra("login"));
		if (intent.getStringExtra("isUpdate") == "true") {
			loginLayout.setVisibility(1);
			edit_name.setText(intent.getStringExtra("name"));
			edit_surname.setText(intent.getStringExtra("surname"));
			edit_middlename.setText(intent.getStringExtra("middlename"));
			index = intent.getIntExtra("index", 0);
			String birthdate = intent.getStringExtra("birthdate");
			SimpleDateFormat format = new SimpleDateFormat(
					DataAdapter.DATE_FORMAT);
			Date date = new Date();
			try {
				date = format.parse(birthdate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			datepicker_birthdate.init(date.getYear(), date.getMonth(),
					date.getDate(), new DatePicker.OnDateChangedListener() {
						@Override
						public void onDateChanged(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {

						}
					});
		}

		button_ok.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				try {
					Account parent = new Account();
					parent.setId(index);
					parent.setName(edit_name.getText().toString());
					parent.setSurname(edit_surname.getText().toString());
					parent.setMiddlename(edit_middlename.getText().toString());

					Date date = new Date();
					date.setDate(datepicker_birthdate.getDayOfMonth());
					date.setMonth(datepicker_birthdate.getMonth());
					date.setYear(datepicker_birthdate.getYear());

					parent.setBirthdate(date);

					if (getIntent().getStringExtra("isUpdate") == "true")
						adapter.updateParent(parent);
					else
						adapter.insertParent(parent);
					// ������� �� ������� �������
					Intent myIntent = new Intent(getBaseContext(),
							ChildrenProfileActivity.class);
					startActivity(myIntent);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		checkBox_showPassword.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (checkBox_showPassword.isChecked()) {
					edit_password.setInputType(InputType.TYPE_CLASS_TEXT);
					edit_passwordConfirm
							.setInputType(InputType.TYPE_CLASS_TEXT);
				} else {
					edit_password.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
					edit_passwordConfirm.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_VARIATION_PASSWORD);
				}
			}
		});

		TextWatcher watcher = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (edit_password.getText().toString()
						.equals(edit_passwordConfirm.getText().toString()))
					edit_passwordConfirm.setBackgroundColor(Color.GREEN);
				else
					edit_passwordConfirm.setBackgroundColor(Color.RED);
				edit_passwordConfirm.refreshDrawableState();
			}
		};

		edit_passwordConfirm.addTextChangedListener(watcher);
		edit_password.addTextChangedListener(watcher);
	}
}
