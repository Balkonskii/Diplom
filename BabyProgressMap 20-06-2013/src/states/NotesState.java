package states;

import com.example.babyprogressmap.ActivityEnum;
import com.example.babyprogressmap.DataManager;
import com.example.babyprogressmap.R;

import forms.ChangeChildrenProfile;
import forms.EditNote;
import forms.Login;
import forms.ViewChildrenProfile;
import forms.ViewNote;

import android.content.Context;
import android.content.Intent;

public class NotesState extends State {

	/**
	 * @param 0 = (Context)context
	 * */
	@Override
	public void middleButtonBarButtonClicked(Object... array) {
		super.middleButtonBarButtonClicked(array);

		Context context = (Context) array[0];
		
		DataManager.setPreviousActivity(ActivityEnum.Notes);
		DataManager.setCurrentState(DataManager.getEditNoteState());
		
		intent = new Intent(context, EditNote.class);
		intent.putExtra(DataManager.Extra_isUpdate, false);
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
	public void changeProfileClicked(Object... array) {
		super.changeProfileClicked(array);

		Context context = (Context) array[0];
		DataManager.setPreviousActivity(ActivityEnum.Notes);
		DataManager.setCurrentState(DataManager.getChangeChildrenProfileState());
		
		intent = new Intent(context, ChangeChildrenProfile.class);
		stateActivity.startActivity(intent);
	}

	/**
	 * @param 0 = (Context)context
	 */
	@Override
	public void viewChildrenProfileClicked(Object... array) {	
		super.viewChildrenProfileClicked(array);
		
		Context context = (Context) array[0];
		DataManager.setPreviousActivity(ActivityEnum.Notes);
		DataManager.setCurrentState(DataManager.getViewChildrenProfileState());

		intent = new Intent(context, ViewChildrenProfile.class);
		stateActivity.startActivity(intent);
	}

	/**
	 * @param 0 = (Context)context
	 */
	@Override
	public void editNoteClicked(Object... array) {		
		super.editNoteClicked(array);
		
		Context context = (Context) array[0];
		DataManager.setPreviousActivity(ActivityEnum.Notes);
		DataManager.setCurrentState(DataManager.getEditNoteState());
		
		intent = new Intent(context, EditNote.class);		
		intent.putExtra("isUpdate", true);
		stateActivity.startActivity(intent);
		
	}

	/**
	 * @param 0 = (Context)context
	 */
	@Override
	public void viewNoteClicked(Object... array) {	
		super.viewNoteClicked(array);
		
		Context context = (Context) array[0];
		DataManager.setPreviousActivity(ActivityEnum.Notes);
		DataManager.setCurrentState(DataManager.getViewNoteState());
		
		intent = new Intent(context, ViewNote.class);
		stateActivity.startActivity(intent);
		
	}

}
