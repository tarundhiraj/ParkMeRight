package com.ghrce.network;

import javax.net.ssl.HostnameVerifier;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class NetworkManager {

	/** TAG */
	private static final String TAG = NetworkManager.class.getSimpleName();

	private static boolean DEBUG = true;

	private static final String BASE_URL = "http://192.168.0.14:8080/WebService/login/post";// add
	// network
	// path

	public static final String SIGN_UP_REQUEST_URL = ""; // add sign up file

	public static final String SIGN_IN_REQUEST_URL = ""; // add
															// login
															// file

	public static final String BOOKING_PARKING = ""; // add book parking file

	public static final String FORGET_PASSWORD = ""; // add book forget_password
														// file

	public static AsyncHttpClient syncClient = new AsyncHttpClient();

	public static void get(String url, RequestParams params,
			final RequestListener listener) {
		syncClient.get(getAbsoluteUrl(url), params,
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject response) {
						if (listener != null) {
							listener.onSuccess(response);
						}
					}

					@Override
					public void onFailure(Throwable thr, JSONObject failed) {
						if (listener != null) {
							listener.onFailure(getErrorCode(failed));
						}
					}

				});
	}

	public static void post(String url, RequestParams params,
			final RequestListener listener) {
		HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
		SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
		socketFactory
				.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
		syncClient.setSSLSocketFactory(socketFactory);
		syncClient.post(getAbsoluteUrl(url), params,
				new JsonHttpResponseHandler() {
			
					@Override
					public void onSuccess(JSONObject response) {
						listener.onSuccess(response);
						System.out.println("REsponse is "+response.toString());
					}

					@Override
					public void onFailure(Throwable thr, JSONObject failed) {

						Log.d(TAG, "onFailure" + failed);
						listener.onFailure(getErrorCode(failed));
					}

				});
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		Log.d("relative path :", relativeUrl);
		Log.d("Base path :", BASE_URL + relativeUrl);
		return BASE_URL;
	}

	protected static int getErrorCode(JSONObject json) {
		int errorCode = NetworkConstants.REQUEST_FAILED;
		try {
			if (json.getString(NetworkConstants.ERROR_CODE).equalsIgnoreCase(
					NetworkConstants.EMAIL_ALREADY_TAKEN_ERROR)) {
				errorCode = NetworkConstants.SignUpRequestConstants.EMAIL_ALREADY_TAKEN;
			}
		} catch (JSONException ex) {

		}
		return errorCode;
	}

	public interface RequestListener {
		public void onSuccess(final JSONObject json);

		public void onFailure(final int errorCode);
	}

}
