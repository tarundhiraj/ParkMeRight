package com.ghrce.network;

import android.os.AsyncTask;

public class JsonReader extends AsyncTask<String, String, String> {
	int selectedItem;
	String data;

	public JsonReader(int selectedItem) {

		this.selectedItem = selectedItem;
	}

	@Override
	protected String doInBackground(String... params) {

		

		return data;
	}

}
