package com.ghrce.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClick(View view){
		System.out.println(view.getId());
		System.out.println(view.getId() == R.id.signup_button);
		switch (view.getId()) {
		case R.id.signup_button:
			Log.d("msg", "Inside click method");
			Intent intent = new Intent(this, UserRegistrationActivity.class);
			startActivity(intent);
		break;
		}
	}
    public void onClick1(View view1){
				System.out.println(view1.getId());
				System.out.println(view1.getId() == R.id.forgotpassword_button);
				switch (view1.getId()) {
				case R.id.forgotpassword_button:
					Log.d("msg", "Inside click method");
					Intent intent = new Intent(this, ForgotPasswordActivity.class);
					startActivity(intent);
					break;
				}	
		
		}
	
}
