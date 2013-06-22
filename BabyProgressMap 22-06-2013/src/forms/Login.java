package forms;

import java.util.ArrayList;

import states.LoginState;

import com.example.babyprogressmap.Account;
import com.example.babyprogressmap.ActivityEnum;
import com.example.babyprogressmap.AlarmRequest;
import com.example.babyprogressmap.Children;
import com.example.babyprogressmap.DataAdapter;
import com.example.babyprogressmap.DataManager;
import com.example.babyprogressmap.R;
import com.example.babyprogressmap.R.id;
import com.example.babyprogressmap.R.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {

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
		DataManager.reset();
		setContentView(R.layout.activity_login);

		AlarmRequest.init(getBaseContext());
		DataManager.getCurrentState().setActivity(this);
		
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
			DataManager.getCurrentState().centerButtonClicked(getBaseContext(),
					true, login);			
		} else if (!registered) {
			alert = new AlertDialog.Builder(Login.this);
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
		DataManager.getCurrentState().centerButtonClicked(getBaseContext(),
				false, login);
	}

	private boolean isPasswordValid(String pass) {
		return pass.length() >= _passwordLength;
	}
}
