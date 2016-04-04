package com.ghrce.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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

import com.ghrce.network.NetworkManager.RequestListener;

public class LoginActivity extends Activity implements RequestListener {

	private static final String USER_NAME = "vivek";
	private static final String PASSWORD = "parking";
	static JSONObject jObj = null;
	static String json = null;
	String username;
	String password;
	private String mLoginValid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick11(View view) {
		Intent intent = null;
		switch (view.getId()) {
		case R.id.button1:
			username = ((EditText) findViewById(R.id.editText2)).getText()
					.toString();
			password = ((EditText) findViewById(R.id.editText1)).getText()
					.toString();
			if (username.length() != 0 && password.length() != 0) {
				new LoginCheck().execute();
			} else {
				Toast.makeText(getApplicationContext(),
						"Username and Password should not be empty.",
						Toast.LENGTH_LONG).show();
			}

			break;

		}
	}

	public void onClick(View view) {
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

	public void onClick1(View view1) {
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

	// get response from server and data in json format.
	@Override
	public void onSuccess(JSONObject json) {

	}

	@Override
	public void onFailure(int errorCode) {

		Log.d("error code :", "" + errorCode);

	}

	public class LoginCheck extends AsyncTask<String, String, String> {

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (mLoginValid == null) {

				return;
			}

			if (mLoginValid.equals("true")) {

				Intent intent = new Intent(LoginActivity.this,
						MainActivity.class);
				intent.putExtra("userId", username);
				startActivity(intent);

			} else {

				Toast.makeText(getApplicationContext(), "Invalid user.",
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
					"http://10.0.2.2:8080/WebService/login/post");
			System.out.println("Inside PostData");
			JSONObject json = new JSONObject();
			try {
				json.put("username", username);
				json.put("password", password);
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
				mLoginValid = EntityUtils.toString(entity);
				Log.d("responseText", mLoginValid);

			} catch (ClientProtocolException e4) {
				e4.printStackTrace();
			} catch (IOException e4) {
				e4.printStackTrace();
			}

			return null;
		}
	}

}
