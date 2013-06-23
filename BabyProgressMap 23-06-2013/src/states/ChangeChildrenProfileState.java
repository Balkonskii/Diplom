package states;

import com.example.babyprogressmap.ActivityEnum;
import com.example.babyprogressmap.DataManager;

import forms.ChangeChildrenProfile;
import forms.ChildrenProfile;
import forms.Login;
import forms.Notes;
import forms.Notifications;
import forms.ViewChildrenProfile;
import android.content.Context;
import android.content.Intent;

public class ChangeChildrenProfileState extends State {

	/**
	 * @param 0 = context
	 * */
	@Override
	public void middleButtonBarButtonClicked(Object... array) {
		super.middleButtonBarButtonClicked(array);

		Context context = (Context) array[0];

		DataManager.setPreviousActivity(ActivityEnum.ChangeChildrenProfile);
		DataManager.setCurrentState(DataManager.getChildrenProfileState());
		
		intent = new Intent(context, ChildrenProfile.class);
		intent.putExtra(DataManager.Extra_isUpdate, false);
		stateActivity.startActivity(intent);
	}

	/**
	 * @param 0 = context
	 * */
	@Override
	public void logoutClicked(Object... array) {
		super.logoutClicked(array);

		Context context = (Context) array[0];

		DataManager.reset();

		intent = new Intent(context, Login.class);
		stateActivity.startActivity(intent);
	}

	/**
	 * @param 0 = context
	 * */
	@Override
	public void notesClicked(Object... array) {
		super.notesClicked(array);

		DataManager.setPreviousActivity(ActivityEnum.ChangeChildrenProfile);
		DataManager.setCurrentState(DataManager.getNotesState());
		
		Context context = (Context) array[0];
		intent = new Intent(context, Notes.class);
		stateActivity.startActivity(intent);
	}

	/**
	 * @param 0 = context
	 * */
	@Override
	public void viewChildrenProfileClicked(Object... array) {
		super.viewChildrenProfileClicked(array);

		DataManager.setPreviousActivity(ActivityEnum.ChangeChildrenProfile);
		DataManager.setCurrentState(DataManager.getViewChildrenProfileState());
		
		Context context = (Context) array[0];
		intent = new Intent(context, ViewChildrenProfile.class);
		stateActivity.startActivity(intent);
	}


	/**
	 * @param 0 = context
	 * */
	@Override
	public void childrenProfileClicked(Object... array) {		
		super.childrenProfileClicked(array);
		
		DataManager.setPreviousActivity(ActivityEnum.ChangeChildrenProfile);
		DataManager.setCurrentState(DataManager.getChildrenProfileState());
		
		Context context = (Context) array[0];
		intent = new Intent(context, ChildrenProfile.class);
		intent.putExtra(DataManager.Extra_isUpdate, true);
		stateActivity.startActivity(intent);
	}

	/**
	 * @param 0 = context
	 * */
	@Override
	public void notificationsClicked(Object... array) {
		super.notificationsClicked(array);

		DataManager.setPreviousActivity(ActivityEnum.ChangeChildrenProfile);
		DataManager.setCurrentState(DataManager.getNotificationsState());
		
		Context context = (Context) array[0];
		intent = new Intent(context, Notifications.class);		
		stateActivity.startActivity(intent);
	}

}
