package com.fr4gus.android.oammblo.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fr4gus.android.oammblo.bo.Tweet;
import com.fr4gus.android.oammblo.util.HTTPClientFactory;
import com.fr4gus.android.oammblo.util.LogIt;
import com.fr4gus.android.oammblo.util.HTTPClientFactory.ClientType;
import com.google.gson.Gson;

public class IdentiCaTwitterService extends TwitterService {

	private static final String API_BASE_URL = "https://identi.ca/api";
	private static final String AUTH = "/account/verify_credentials.json";
	private static final String PUBLIC_TIMELINE = "/statuses/public_timeline.json";

	@Override
	public boolean authenticate(String username, String password)
			throws TwitterServiceException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			httpclient.getCredentialsProvider().setCredentials(
					new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
					new UsernamePasswordCredentials(username, password));

			HttpPost request = new HttpPost(API_BASE_URL + AUTH);

			LogIt.d(this, "executing request" + request.getRequestLine());
			HttpResponse response = httpclient.execute(request);

			int statusCode = response.getStatusLine().getStatusCode();

			HttpEntity entity = response.getEntity();

			LogIt.d(this, "Response:", response.getStatusLine());
			if (entity != null) {
				LogIt.d(this,
						"Response content length: " + entity.getContentLength());
			}
			if (statusCode == 200) {
				return true;
			}
		} catch (ClientProtocolException e) {
			LogIt.e(this, e, e.getMessage());
			throw new TwitterServiceException(e);
		} catch (IOException e) {
			LogIt.e(this, e, e.getMessage());
			throw new TwitterServiceException(e);
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
		return false;
	}

	@Override
	public List<Tweet> getTimeline() {
		String path = API_BASE_URL + PUBLIC_TIMELINE;
		try {
			InputStream in = HTTPClientFactory.getInputStream(path,
					ClientType.HTTP_URL_CONNECTION);

			Gson gson = new Gson();

			Tweet[] tweets = gson.fromJson(new InputStreamReader(in),
					Tweet[].class);

			return Arrays.asList(tweets);

		} catch (ClientProtocolException e) {
			LogIt.e(this, e, e.getMessage());
		} catch (IOException e) {
			LogIt.e(this, e, e.getMessage());
		}

		return null;
	}

	@Override
	public List<Tweet> getPublicTimeline() {
		// TODO Auto-generated method stub
		return null;
	}

}
