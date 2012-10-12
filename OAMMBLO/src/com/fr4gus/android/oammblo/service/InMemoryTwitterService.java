package com.fr4gus.android.oammblo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fr4gus.android.oammblo.bo.Tweet;
import com.fr4gus.android.oammblo.bo.User;

public class InMemoryTwitterService extends TwitterService {
	public static final String TAG = InMemoryTwitterService.class
			.getSimpleName();

	private void simulateBlock() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ignore) {
		}

	}

	public boolean authenticate(String username, String password) {
		simulateBlock();
		return true;
	}

	public List<Tweet> getTimeline() {
		simulateBlock();
		List<Tweet> tweets = new ArrayList<Tweet>();
		tweets.add(new Tweet(new User(1, "John Doe"), "Hacia la casa",
				new Date()));
		tweets.add(new Tweet(new User(2, "John Doe"), "Hay presa :(",
				new Date()));
		tweets.add(new Tweet(new User(3, "Mary Doe"), "@jdoe :'(",
				new Date(), true));
		tweets.add(new Tweet(new User(4, "Juan P"), "Mi gato se escapo",
				new Date()));
		tweets.add(new Tweet(new User(3, "Juan P"), "Mi gato se escapo",
				new Date()));
		tweets.add(new Tweet(new User(3, "Juan P"), "Mi gato se escapo",
				new Date()));
		tweets.add(new Tweet(new User(3, "Juan P"), "Mi gato se escapo",
				new Date()));
		tweets.add(new Tweet(new User(3, "Juan P"), "Mi gato se escapo",
				new Date()));
		tweets.add(new Tweet(new User(3, "Juan P"), "Mi gato se escapo",
				new Date()));
		tweets.add(new Tweet(new User(3, "Juan P"), "Mi gato se escapo",
				new Date()));
		tweets.add(new Tweet(new User(3, "Juan P"), "Who?", new Date()));
		tweets.add(new Tweet(new User(3, "Juan P"), "What?", new Date()));
		tweets.add(new Tweet(new User(3, "Juan P"), "Lorem Ipsum",
				new Date()));
		return tweets;
	}

	@Override
	public List<Tweet> getPublicTimeline() {
		// TODO Auto-generated method stub
		return null;
	}

}
