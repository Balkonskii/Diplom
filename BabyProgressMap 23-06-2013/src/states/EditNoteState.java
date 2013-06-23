package states;

import com.example.babyprogressmap.ActivityEnum;
import com.example.babyprogressmap.DataManager;

import forms.Notes;
import forms.ViewNote;
import android.content.Context;
import android.content.Intent;

public class EditNoteState extends State {

	/**
	 * param 0 = (Context)context
	 * */
	@Override
	public void leftButtonBarButtonClicked(Object... array) {
		super.leftButtonBarButtonClicked(array);

		Context context = (Context) array[0];
				
		switch (DataManager.getPreviousActivity()) {
		case ViewNote:
			intent = new Intent(context, ViewNote.class);		
			DataManager.setCurrentState(DataManager.getViewNoteState());
			break;
		case Notes:
			intent = new Intent(context, Notes.class);			
			DataManager.setCurrentState(DataManager.getNotesState());
			break;
		}
		
		DataManager.setPreviousActivity(ActivityEnum.EditNote);
		stateActivity.startActivity(intent);
	}
	
	/**
	 * param 0 = (Context)context
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
		case ViewNote:
			intent = new Intent(context, ViewNote.class);
			DataManager.setCurrentState(DataManager.getViewNoteState());
			break;
		}
		
		DataManager.setPreviousActivity(ActivityEnum.EditNote);
		stateActivity.startActivity(intent);
	}

}
