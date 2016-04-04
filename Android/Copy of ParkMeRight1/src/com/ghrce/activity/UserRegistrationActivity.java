package com.ghrce.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ghrce.network.NetworkManager;
import com.ghrce.network.NetworkManager.RequestListener;
import com.ghrce.network.ParsingConstants;
import com.loopj.android.http.RequestParams;

public class UserRegistrationActivity extends Activity implements
		RequestListener {

	private String mUserName;
	private String mPassword;
	private String mMobileNo;
	private String mEmail;
	private String mFirstName;
	private String mLastName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_registration);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick11(View view) {
		switch (view.getId()) {
		case R.id.button1:
			mUserName = ((EditText) findViewById(R.id.editText2)).getText()
					.toString();
			mPassword = ((EditText) findViewById(R.id.editText5)).getText()
					.toString();
			mMobileNo = ((EditText) findViewById(R.id.editText4)).getText()
					.toString();
			mEmail = ((EditText) findViewById(R.id.editText3)).getText()
					.toString();
			mFirstName = ((EditText) findViewById(R.id.editText1)).getText()
					.toString();
			mLastName = ((EditText) findViewById(R.id.EditText01)).getText()
					.toString();
			String reenter_password = ((EditText) findViewById(R.id.EditText02))
					.getText().toString();
			// if (username.equals("") || password.equals("") ||
			// mobile.equals("")
			// || email.equals("") || fname.equals("") || lname.equals("")) {
			// Toast.makeText(this, "Please enter all the details",
			// Toast.LENGTH_SHORT).show();
			// } else if (password.equalsIgnoreCase(reenter_password))
			// break;
			// else {
			// Toast.makeText(this, "Passwords do not match", 0).show();
			// }

			new RegistrationPage().execute();

		}

	}

	public void reset(View view) {

		System.out.println(view.getId());
		System.out.println(view.getId() == R.id.button2);
		switch (view.getId()) {
		case R.id.button2:
			Log.d("msg", "Inside click method");
			Intent intent = new Intent(this, UserRegistrationActivity.class);
			startActivity(intent);
			break;
		}
	}

	// get data from the server
	@Override
	public void onSuccess(JSONObject json) {

	}

	@Override
	public void onFailure(int errorCode) {

		Log.d("Error code", "" + errorCode);

	}

	public class RegistrationPage extends AsyncTask<String, String, String> {

		private String register;

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			if (register == null) {
				return;
			}
			if (register.equals("true")) {

				Toast.makeText(getApplicationContext(),
						"User regitered successfully.", Toast.LENGTH_SHORT)
						.show();
				UserRegistrationActivity.this.finish();

			} else {
				Toast.makeText(getApplicationContext(), "Registered user.",
						Toast.LENGTH_SHORT).show();
			}

		}

		@Override
		protected String doInBackground(String... params) {

			HttpClient httpclient;
			HttpParams httpParameters = new BasicHttpParams();
			int timeoutConnection = 3000;
			int timeoutSocket = 5000;
			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
			httpclient = new DefaultHttpClient(httpParameters);
			HttpPost httppost = new HttpPost(
					"http://10.0.2.2:8080/WebService/register/post");
			System.out.println("Inside PostData");
			JSONObject json = new JSONObject();
			try {
				json.put("fname", mFirstName);
				json.put("lname", mLastName);
				json.put("email", mEmail);
				json.put("mobile", mMobileNo);
				json.put("password", mPassword);
				StringEntity se = new StringEntity(json.toString());
				se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,
						"application/json"));
				httppost.setEntity(se);
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			HttpResponse response;

			try {
				response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				Log.d("entity", "" + response.getEntity());
				Log.d("params", "" + response.getParams());
				Log.d("Stutus Line :", response.getStatusLine().toString());
				register = EntityUtils.toString(entity);
				Log.d("Slot Data : ", register);

			} catch (ClientProtocolException e4) {
				e4.printStackTrace();
			} catch (IOException e4) {
				e4.printStackTrace();
			}
			return null;
		}
	}

}
