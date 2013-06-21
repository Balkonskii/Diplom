package states;

import android.content.Context;
import android.content.Intent;

import com.example.babyprogressmap.ActivityEnum;
import com.example.babyprogressmap.DataManager;

import forms.ChangeChildrenProfile;
import forms.Notes;
import forms.Registration;
import forms.ViewChildrenProfile;

public class ChildrenProfileState extends State {

	/**
	 * @param 0 = context
	 * */
	@Override
	public void leftButtonBarButtonClicked(Object... array) {
		super.leftButtonBarButtonClicked(array);

		Context context = (Context) array[0];

		switch (DataManager.getPreviousActivity()) {
		case ChangeChildrenProfile:
			intent = new Intent(context, ChangeChildrenProfile.class);
			DataManager.setCurrentState(DataManager.getChangeChildrenProfileState());
			break;
		case Registration:
			intent = new Intent(context, Notes.class);
			DataManager.setCurrentState(DataManager.getNotesState());
			break;
		case ViewChildrenProfile:
			intent = new Intent(context, ViewChildrenProfile.class);
			DataManager.setCurrentState(DataManager.getViewChildrenProfileState());
			break;
		}
		
		DataManager.setPreviousActivity(ActivityEnum.ChildrenProfile);		
		stateActivity.startActivity(intent);
	}

	/**
	 * @param 0 = context
	 * */
	@Override
	public void rightButtonBarButtonClicked(Object... array) {
		super.rightButtonBarButtonClicked(array);

		Context context = (Context) array[0];
		
		switch (DataManager.getPreviousActivity()) {
		case Registration:
			intent = new Intent(context, Registration.class);
			DataManager.setCurrentState(DataManager.getRegistrationState());
			break;
		case ViewChildrenProfile:
			intent = new Intent(context, ViewChildrenProfile.class);
			DataManager.setCurrentState(DataManager.getViewChildrenProfileState());
			break;
		case ChangeChildrenProfile:
			intent = new Intent(context, ChangeChildrenProfile.class);
			DataManager.setCurrentState(DataManager.getChangeChildrenProfileState());
			break;
		}
		
		DataManager.setPreviousActivity(ActivityEnum.ChildrenProfile);
		stateActivity.startActivity(intent);
	}

}
