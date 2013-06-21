package states;

import android.content.Context;
import android.content.Intent;

import com.example.babyprogressmap.ActivityEnum;
import com.example.babyprogressmap.DataManager;

import forms.ChildrenProfile;
import forms.Login;

public class RegistrationState extends State {

	/**
	 * @param 0 = (Context)context
	 * */
	@Override
	public void leftButtonBarButtonClicked(Object... array) {
		super.leftButtonBarButtonClicked(array);

		Context context = (Context) array[0];
		DataManager.setPreviousActivity(ActivityEnum.Registration);
		DataManager.setCurrentState(DataManager.getChildrenProfileState());
		
		intent = new Intent(context, ChildrenProfile.class);
		stateActivity.startActivity(intent);
	}

	/**
	 * @param 0 = (Context)context
	 * */
	@Override
	public void rightButtonBarButtonClicked(Object... array) {
		super.rightButtonBarButtonClicked(array);

		Context context = (Context) array[0];
		DataManager.setPreviousActivity(ActivityEnum.Registration);
		DataManager.setCurrentState(DataManager.getLoginState());

		intent = new Intent(context, Login.class);
		stateActivity.startActivity(intent);
	}

}
