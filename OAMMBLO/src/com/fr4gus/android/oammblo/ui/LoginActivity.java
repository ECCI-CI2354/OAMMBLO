package com.fr4gus.android.oammblo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fr4gus.android.oammblo.OammbloApp;
import com.fr4gus.android.oammblo.R;
import com.fr4gus.android.oammblo.service.TwitterService;
import com.fr4gus.android.oammblo.service.TwitterServiceException;

public class LoginActivity extends Activity {
	public static final int DIALOG_MULTIPLE_CHOICE = 0;
	EditText mUsername;
	EditText mPassword;
	TwitterService mService = OammbloApp.getInstance().getService();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		mUsername = (EditText) findViewById(R.id.login_username);
		mPassword = (EditText) findViewById(R.id.login_password);

	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	public void doAuthenticate(View view) {
		AsyncTask<String, Void, Boolean> task = new AsyncTask<String, Void, Boolean>() {
			@Override
			protected Boolean doInBackground(String... params) {
				if (params.length < 2) {
					return false;
				}
				String username = params[0];
				String password = params[1];

				boolean result = false;
				try {
					result = mService.authenticate(username, password);
				} catch (TwitterServiceException e) {
				}
				return result;
			}

			@Override
			protected void onPostExecute(Boolean result) {
				super.onPostExecute(result);
				if (result) {
					startActivity(new Intent(LoginActivity.this,
							TimelineActivity.class));
				} else {
					Toast.makeText(getApplicationContext(), "Error",
							Toast.LENGTH_LONG).show();
				}

			}

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}

		};
		task.execute(mUsername.getText().toString(), mPassword.getText()
				.toString());
	}

}
