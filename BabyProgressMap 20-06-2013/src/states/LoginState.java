package states;

import com.example.babyprogressmap.ActivityEnum;
import com.example.babyprogressmap.DataManager;

import forms.ChangeChildrenProfile;
import forms.Registration;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class LoginState extends State {

	/**
	 * @param 0 = context
	 * @param 1 = registered	
	 * @param 2 = login
	 * */
	@Override
	public void centerButtonClicked(Object... array) {		
			Context context = (Context) array[0];
			boolean registered = (Boolean) array[1];			

			if (registered) // registered == true - незарегистрирован
			{
				intent = new Intent(context, ChangeChildrenProfile.class);
				DataManager.setCurrentState(DataManager.getChangeChildrenProfileState());

			} else {
				intent = new Intent(context, Registration.class);
				DataManager.setCurrentState(DataManager.getRegistrationState());
			}
			DataManager.setPreviousActivity(ActivityEnum.Login);
			intent.putExtra(DataManager.Extra_isUpdate, false);
			intent.putExtra("login", array[2].toString());
			stateActivity.startActivity(intent);		
	}
}
