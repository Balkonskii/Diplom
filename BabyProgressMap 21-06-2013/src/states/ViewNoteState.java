package states;

import android.content.Context;
import android.content.Intent;

import com.example.babyprogressmap.ActivityEnum;
import com.example.babyprogressmap.DataManager;

import forms.ChangeChildrenProfile;
import forms.EditNote;
import forms.Login;
import forms.Notes;

public class ViewNoteState extends State {

	/**
	 * @param 0 = (Context)context
	 * */
	@Override
	public void leftButtonBarButtonClicked(Object... array) {
		super.leftButtonBarButtonClicked(array);

		Context context = (Context) array[0];
		DataManager.setPreviousActivity(ActivityEnum.ViewNote);
		DataManager.setCurrentState(DataManager.getEditNoteState());
		
		intent = new Intent(context, EditNote.class);
		intent.putExtra("isUpdate", true);
		stateActivity.startActivity(intent);
	}

	/**
	 * @param 0 = (Context)context
	 * */
	@Override
	public void rightButtonBarButtonClicked(Object... array) {
		super.rightButtonBarButtonClicked(array);

		Context context = (Context) array[0];
		
		switch (DataManager.getPreviousActivity()) {
		case Notes:
			intent = new Intent(context, Notes.class);
			DataManager.setCurrentState(DataManager.getNotesState());
			break;
		case EditNote:
			intent = new Intent(context, EditNote.class);
			intent.putExtra(DataManager.Extra_isUpdate, true);
			DataManager.setCurrentState(DataManager.getEditNoteState());
			break;
		}
		
		DataManager.setPreviousActivity(ActivityEnum.ViewNote);
		stateActivity.startActivity(intent);
	}

	/**
	 * @param 0 = (Context)context
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
	 * @param 0 = (Context)context
	 * */
	@Override
	public void notesClicked(Object... array) {		
		super.notesClicked(array);
		
		Context context = (Context) array[0];
		DataManager.setPreviousActivity(ActivityEnum.ViewNote);
		DataManager.setCurrentState(DataManager.getNotesState());
		
		intent = new Intent(context, Notes.class);	
		stateActivity.startActivity(intent);
	}

	/**
	 * @param 0 = (Context)context
	 * */
	@Override
	public void changeProfileClicked(Object... array) {		
		super.changeProfileClicked(array);
		
		Context context = (Context) array[0];
		DataManager.setPreviousActivity(ActivityEnum.ViewNote);
		DataManager.setCurrentState(DataManager.getChangeChildrenProfileState());
		
		intent = new Intent(context, ChangeChildrenProfile.class);		
		stateActivity.startActivity(intent);		
	}

}
