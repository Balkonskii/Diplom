package states;

import android.content.Context;
import android.content.Intent;

import com.example.babyprogressmap.ActivityEnum;
import com.example.babyprogressmap.DataManager;

import forms.ChangeChildrenProfile;
import forms.Notifications;

public class EditNoticeState extends State {

	@Override
	public void leftButtonBarButtonClicked(Object... array) {
		super.leftButtonBarButtonClicked(array);

		Context context = (Context) array[0];
		DataManager.setPreviousActivity(ActivityEnum.EditNotice);
		DataManager.setCurrentState(DataManager.getNotificationsState());

		intent = new Intent(context, Notifications.class);
		stateActivity.startActivity(intent);
	}

	@Override
	public void rightButtonBarButtonClicked(Object... array) {
		super.rightButtonBarButtonClicked(array);

		Context context = (Context) array[0];
		DataManager.setPreviousActivity(ActivityEnum.EditNotice);
		DataManager.setCurrentState(DataManager.getNotificationsState());

		intent = new Intent(context, Notifications.class);
		stateActivity.startActivity(intent);
	}

}
