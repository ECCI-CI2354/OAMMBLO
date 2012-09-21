package com.fr4gus.android.oammblo.service;

public class TwitterServiceFactory {
	static TwitterService service = new InMemoryTwitterService();

	public static TwitterService getService(){
		return service;
	}
}
