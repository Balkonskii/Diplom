package states;

import android.content.Context;
import android.content.Intent;

import com.example.babyprogressmap.ActivityEnum;
import com.example.babyprogressmap.DataManager;

import forms.ChangeChildrenProfile;
import forms.ChildrenProfile;
import forms.Login;
import forms.Notes;
import forms.Notifications;

public class ViewChildrenProfileState extends State {

	/**
	 * @param 0 = (Context)context
	 */
	@Override
	public void leftButtonBarButtonClicked(Object... array) {
		super.leftButtonBarButtonClicked(array);

		Context context = (Context) array[0];
		DataManager.setPreviousActivity(ActivityEnum.ViewChildrenProfile);
		DataManager.setCurrentState(DataManager.getChildrenProfileState());
		
		intent = new Intent(context, ChildrenProfile.class);
		intent.putExtra(DataManager.Extra_isUpdate, true);
		stateActivity.startActivity(intent);
	}

	/**
	 * @param 0 = (Context)context
	 */
	@Override
	public void rightButtonBarButtonClicked(Object... array) {
		super.rightButtonBarButtonClicked(array);

		Context context = (Context) array[0];
		
		switch (DataManager.getPreviousActivity()) {
		case ChangeChildrenProfile:
			intent = new Intent(context, ChangeChildrenProfile.class);
			DataManager.setCurrentState(DataManager.getChangeChildrenProfileState());
			break;
		case Notes:
			intent = new Intent(context, Notes.class);
			DataManager.setCurrentState(DataManager.getNotesState());
			break;
		case ChildrenProfile:
			intent = new Intent(context, ChildrenProfile.class);
			intent.putExtra(DataManager.Extra_isUpdate, true);
			DataManager.setCurrentState(DataManager.getChildrenProfileState());			
			break;
		case Notifications:
			intent = new Intent(context, Notifications.class);
			DataManager.setCurrentState(DataManager.getNotificationsState());
			break;
		}
		
		DataManager.setPreviousActivity(ActivityEnum.ViewChildrenProfile);
		stateActivity.startActivity(intent);
	}

	/**
	 * @param 0 = (Context)context
	 */
	@Override
	public void logoutClicked(Object... array) {
		super.logoutClicked(array);

		Context context = (Context) array[0];

		DataManager.reset();

		intent = new Intent(context, Login.class);
		stateActivity.startActivity(intent);
	}

	/**
	 * @param 0 = (Context)context
	 */
	@Override
	public void notesClicked(Object... array) {		
		super.notesClicked(array);			

		Context context = (Context) array[0];
		
		DataManager.setPreviousActivity(ActivityEnum.ViewChildrenProfile);
		DataManager.setCurrentState(DataManager.getNotesState());
		
		intent = new Intent(context, Notes.class);
		stateActivity.startActivity(intent);
	}


	/**
	 * @param 0 = context
	 * */
	@Override
	public void changeProfileClicked(Object... array) {	
		super.changeProfileClicked(array);
		
		Context context = (Context) array[0];
		DataManager.setPreviousActivity(ActivityEnum.ViewChildrenProfile);
		DataManager.setCurrentState(DataManager.getChangeChildrenProfileState());
		
		intent = new Intent(context, ChangeChildrenProfile.class);
		stateActivity.startActivity(intent);
	}

	/**
	 * @param 0 = context
	 * */
	@Override
	public void notificationsClicked(Object... array) {
		super.notificationsClicked(array);

		DataManager.setPreviousActivity(ActivityEnum.ViewChildrenProfile);
		DataManager.setCurrentState(DataManager.getNotificationsState());
		
		Context context = (Context) array[0];
		intent = new Intent(context, Notifications.class);		
		stateActivity.startActivity(intent);
	}
}
