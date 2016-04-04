package com.ghrce.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ghrce.network.JsonReader;
import com.ghrce.network.NetworkManager.RequestListener;

public class MainActivity extends Activity implements RequestListener,
		OnClickListener {
	private Spinner spinner1, spinner2;
	private Button button;
	private String mFloorNo;
	private String mSlotNo;
	private String mBookingId;
	private String mVehicleNo;
	private String mEntryTime;
	private String mDurationTime;
	private String mVehType;
	String selectedSlot;

	private int mFlag;

	List<String> slotAvailable = new ArrayList<String>();

	// widgets instance

	private EditText mBookingIdText;
	private EditText mVehicleNoText;
	private EditText mEntryTimeText;
	private EditText mDurationTimeText;
	private EditText mVehicleType;
	private String userId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		button = (Button) findViewById(R.id.button);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			userId = extras.getString("userId");
		}
		mBookingIdText = (EditText) findViewById(R.id.editText1);
		mVehicleNoText = (EditText) findViewById(R.id.editText2);
		mEntryTimeText = (EditText) findViewById(R.id.editText3);
		mDurationTimeText = (EditText) findViewById(R.id.editText4);
		mVehicleType = (EditText) findViewById(R.id.type_text);

		spinner2 = (Spinner) findViewById(R.id.spinner2);

		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(spinner1Listener);
		button.setOnClickListener(this);

	}

	OnItemSelectedListener spinner1Listener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			if (spinner1.getSelectedItem().equals("Select Floor")) {
			} else {
				new SelectFloor().execute();
			}

		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {

		}
	};

	// add items into spinner dynamically

	@Override
	public void onSuccess(JSONObject json) {

	}

	@Override
	public void onFailure(int errorCode) {

		Log.d("test", "" + errorCode);

	}

	public class SelectFloor extends AsyncTask<String, String, String> {

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			ArrayAdapter dataAdapter = new ArrayAdapter(MainActivity.this,
					android.R.layout.simple_spinner_item, slotAvailable);

			dataAdapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner2.setAdapter(dataAdapter);
			spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					selectedSlot = (String) spinner2.getSelectedItem();
					new SlotNo().execute();
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {

				}
			});

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
			HttpResponse response;
			try {
				HttpGet httpget = new HttpGet(
						"http://10.0.2.2:8080/WebService/floor/get?floorNo=1");
				response = httpclient.execute(httpget);
				System.out.println("Inside PostData");

				HttpEntity entity = response.getEntity();
				Log.d("entity", "" + response.getEntity());
				Log.d("params", "" + response.getParams());
				Log.d("Stutus Line", "" + response.getStatusLine());
				String data = EntityUtils.toString(entity);
				Log.d("DATA : ", data);
				JSONArray array = new JSONArray(data);
				Log.d("Lenght", "" + array.length());
				String[] available = new String[array.length()];

				for (int i = 0; i < array.length(); i++) {

					JSONObject json = array.getJSONObject(i);
					Log.d("floorNo", json.getString("floorNo"));
					Log.d("available", json.getString("available"));
					Log.d("slotNo", json.getString("slotNo"));
					available[i] = json.getString("available");
					if (!available[i].equals("0")) {
						slotAvailable.add(json.getString("slotNo"));
					}

				}

			} catch (ClientProtocolException e4) {
				e4.printStackTrace();
			} catch (IOException e4) {
				e4.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}
	}

	public class SlotNo extends AsyncTask<String, String, String> {

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			mBookingIdText.setText(mBookingId);
			mVehicleType.setText(mVehType);
			mBookingIdText.setEnabled(false);
			mVehicleType.setEnabled(false);
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
					"http://10.0.2.2:8080/WebService/getdetails/post");
			System.out.println("Inside PostData");
			JSONObject json = new JSONObject();
			try {
				json.put("slotno", selectedSlot);
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
				Log.d("Spinner 2", "" + mFlag);
				Log.d("entity", "" + response.getEntity());
				Log.d("params", "" + response.getParams());
				String data = EntityUtils.toString(entity);
				Log.d("Slot Data : ", data);

				JSONObject jsonSlot = new JSONObject(data);
				mBookingId = jsonSlot.getString("bookingId");
				mVehType = jsonSlot.getString("vehicle");

			} catch (ClientProtocolException e4) {
				e4.printStackTrace();
			} catch (IOException e4) {
				e4.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}

	public class BookSlots extends AsyncTask<String, String, String> {

		private String bookSuccess;

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			if (bookSuccess == null) {
				return;
			}
			if (bookSuccess.equals("true")) {
				Toast.makeText(getApplicationContext(),
						"Booking successfully done.", Toast.LENGTH_SHORT)
						.show();

				MainActivity.this.finish();

			} else {
				Toast.makeText(getApplicationContext(), "Check booking input.",
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
					"http://10.0.2.2:8080/WebService/booking/post");
			System.out.println("Inside PostData");
			JSONObject json = new JSONObject();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 1);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String formatted = format1.format(cal.getTime());

			cal.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			System.out.println(sdf.format(cal.getTime()));

			try {
				json.put("bookingid", mBookingId);
				json.put("userid", userId);
				json.put("floorno", "" + 1);
				json.put("slotno", selectedSlot);
				json.put("date", formatted);
				json.put("bookingtime", "02:50:00");
				json.put("type", "S");
				json.put("vehicleno", mVehicleNo);
				json.put("entrytime", mEntryTime);
				json.put("duration", mDurationTime);
				json.put("extension", "1");
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
				bookSuccess = EntityUtils.toString(entity);
				Log.d("Slot Data : ", bookSuccess);

			} catch (ClientProtocolException e4) {
				e4.printStackTrace();
			} catch (IOException e4) {
				e4.printStackTrace();
			}
			return null;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			mFloorNo = String.valueOf(spinner1.getSelectedItem());
			mSlotNo = String.valueOf(spinner2.getSelectedItem());
			mVehicleNo = mVehicleNoText.getText().toString();
			mBookingId = mBookingIdText.getText().toString();
			mEntryTime = mEntryTimeText.getText().toString();
			mDurationTime = mDurationTimeText.getText().toString();
			mVehType = mVehicleType.getText().toString();

			new BookSlots().execute();
			break;

		default:
			break;
		}

	}

}
