package states;

import android.app.Activity;
import android.content.Intent;

//первым параметром всегда должен идти Context

public abstract class State {

	protected Activity stateActivity;
	protected Intent intent;
	
	public void centerButtonClicked(Object... array) {
	};

	public void leftButtonBarButtonClicked(Object... array) {
	};

	public void middleButtonBarButtonClicked(Object... array) {
	};

	public void rightButtonBarButtonClicked(Object... array) {
	};

	public void logoutClicked(Object... array) {
	};

	public void notesClicked(Object... array) {
	};

	public void changeProfileClicked(Object... array) {
	};

	public void childrenProfileClicked(Object... array) {
	};

	public void editNoteClicked(Object... array) {
	};
	
	public void noticesClicked(Object... array) {
	};

	public void viewChildrenProfileClicked(Object... array) {
	};

	public void viewNoteClicked(Object... array) {
	};
	
	public void setActivity(Activity _this) {
		stateActivity = _this;
	};
}
