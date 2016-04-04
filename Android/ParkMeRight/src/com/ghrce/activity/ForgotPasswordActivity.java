package com.ghrce.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgot_password);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.forgot_submit_button:
			String emailAddress = ((EditText)findViewById(R.id.email_address)).getText().toString();
			
			if(emailAddress.equals("")){
				Toast.makeText(this,"Please enter email address", Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}
	}
}
