package states;

import com.example.babyprogressmap.DataManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import forms.Login;

//������ ���������� ������ ������ ���� Context

public abstract class State {

	protected Activity stateActivity;
	protected Intent intent;

	public void centerButtonClicked(Object... array) {
		Context context = (Context) array[0];
		stateChanged(context);
	};

	public void leftButtonBarButtonClicked(Object... array) {
		Context context = (Context) array[0];
		stateChanged(context);
	};

	public void middleButtonBarButtonClicked(Object... array) {
		Context context = (Context) array[0];
		stateChanged(context);
	};

	public void rightButtonBarButtonClicked(Object... array) {
		Context context = (Context) array[0];
		stateChanged(context);
	};

	public void logoutClicked(Object... array) {
		Context context = (Context) array[0];
		stateChanged(context);
	};

	public void notesClicked(Object... array) {
		Context context = (Context) array[0];
		stateChanged(context);
	};

	public void changeProfileClicked(Object... array) {
		Context context = (Context) array[0];
		stateChanged(context);
	};

	public void childrenProfileClicked(Object... array) {
		Context context = (Context) array[0];
		stateChanged(context);
	};

	public void editNoteClicked(Object... array) {
		Context context = (Context) array[0];
		stateChanged(context);
	};

	public void editNoticeClicked(Object... array) {
		Context context = (Context) array[0];
		stateChanged(context);
	};

	public void notificationsClicked(Object... array) {
		Context context = (Context) array[0];
		stateChanged(context);
	};

	public void viewChildrenProfileClicked(Object... array) {
		Context context = (Context) array[0];
		stateChanged(context);
	};

	public void viewNoteClicked(Object... array) {
		Context context = (Context) array[0];
		stateChanged(context);
	};

	public void setActivity(Activity _this) {
		stateActivity = _this;
	};

	public void stateChanged(Context context) {
		if (!DataManager.hasLogged()) {					
			DataManager.reset();

			intent = new Intent(context, Login.class);
			stateActivity.startActivity(intent);
		}
	}
}
