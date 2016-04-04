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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ghrce.network.NetworkManager.RequestListener;
import com.ghrce.network.ParsingConstants;

public class ForgotPasswordActivity extends Activity implements RequestListener {

	private String mEmailAddress;
	static JSONObject jObj = null;
	static String json = null;

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

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button1:
			mEmailAddress = ((EditText) findViewById(R.id.email_address))
					.getText().toString();

			if (mEmailAddress.equals("")) {
				Toast.makeText(this, "Please enter email address",
						Toast.LENGTH_SHORT).show();
			} else {

				new ForgetPassword().execute();

			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onSuccess(JSONObject json) {
		try {
			mEmailAddress = json.getString("emailid");
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onFailure(int errorCode) {

	}

	private class ForgetPassword extends AsyncTask<String, String, String> {

		private String forgetPassword;

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			if (forgetPassword == null) {
				return;
			}

			if (forgetPassword.equals("true")) {
				Toast.makeText(getApplicationContext(),
						"password send successfully.", Toast.LENGTH_SHORT)
						.show();
				ForgotPasswordActivity.this.finish();
			} else {
				Toast.makeText(getApplicationContext(),
						"user is not registered.", Toast.LENGTH_SHORT).show();
			}

		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			HttpClient httpclient;
			HttpParams httpParameters = new BasicHttpParams();
			int timeoutConnection = 3000;
			int timeoutSocket = 5000;
			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
			httpclient = new DefaultHttpClient(httpParameters);
			HttpPost httppost = new HttpPost(
					"http://10.0.2.2:8080/WebService/forgotpass/post");
			System.out.println("Inside ForgetPassword");
			JSONObject json = new JSONObject();
			try {
				json.put("emailid", mEmailAddress);
				StringEntity se = new StringEntity(json.toString());
				se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,
						"application/json"));
				httppost.setEntity(se);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			HttpResponse response;

			try {
				// httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				if (entity == null) {
					return "entity null";
				} else {
					Log.d("entity", "" + response.getEntity());
					// Log.d("params", "" + EntityUtils.toString(entity));

					forgetPassword = EntityUtils.toString(entity);
					Log.d("Test", forgetPassword);
				}

			} catch (ClientProtocolException e4) {
				e4.printStackTrace();
			} catch (IOException e4) {
				e4.printStackTrace();
			}

			return null;
		}
	}
}
