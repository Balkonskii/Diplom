package states;

import android.content.Context;
import android.content.Intent;

import com.example.babyprogressmap.ActivityEnum;
import com.example.babyprogressmap.DataManager;

import forms.ChangeChildrenProfile;
import forms.EditNotice;
import forms.Login;
import forms.Notes;
import forms.ViewChildrenProfile;

public class NotificationsState extends State{

	@Override
	public void logoutClicked(Object... array) {
		super.logoutClicked(array);

		Context context = (Context) array[0];

		DataManager.reset();

		intent = new Intent(context, Login.class);
		stateActivity.startActivity(intent);
	}

	@Override
	public void notesClicked(Object... array) {
		super.notesClicked(array);

		DataManager.setPreviousActivity(ActivityEnum.Notifications);
		DataManager.setCurrentState(DataManager.getNotesState());
		
		Context context = (Context) array[0];
		intent = new Intent(context, Notes.class);
		stateActivity.startActivity(intent);
	}

	@Override
	public void changeProfileClicked(Object... array) {
		super.changeProfileClicked(array);

		Context context = (Context) array[0];
		DataManager.setPreviousActivity(ActivityEnum.Notifications);
		DataManager.setCurrentState(DataManager.getChangeChildrenProfileState());
		
		intent = new Intent(context, ChangeChildrenProfile.class);
		stateActivity.startActivity(intent);
	}

	@Override
	public void viewChildrenProfileClicked(Object... array) {
		super.viewChildrenProfileClicked(array);

		DataManager.setPreviousActivity(ActivityEnum.Notifications);
		DataManager.setCurrentState(DataManager.getViewChildrenProfileState());
		
		Context context = (Context) array[0];
		intent = new Intent(context, ViewChildrenProfile.class);
		stateActivity.startActivity(intent);
	}

	@Override
	public void editNoticeClicked(Object... array) {		
		super.editNoticeClicked(array);
		
		DataManager.setPreviousActivity(ActivityEnum.Notifications);
		DataManager.setCurrentState(DataManager.getEditNoticeState());
		
		Context context = (Context) array[0];
		intent = new Intent(context, EditNotice.class);
		intent.putExtra(DataManager.Extra_isUpdate, true);
		stateActivity.startActivity(intent);
	}

	@Override
	public void middleButtonBarButtonClicked(Object... array) {		
		super.middleButtonBarButtonClicked(array);
		
		DataManager.setPreviousActivity(ActivityEnum.Notifications);
		DataManager.setCurrentState(DataManager.getEditNoticeState());
		
		Context context = (Context) array[0];
		intent = new Intent(context, EditNotice.class);		
		stateActivity.startActivity(intent);
	}
}
