package com.fr4gus.android.oammblo;

import com.fr4gus.android.oammblo.service.TwitterService;
import com.fr4gus.android.oammblo.service.TwitterServiceFactory;
import com.fr4gus.android.oammblo.service.TwitterServiceFactory.ServiceType;

import android.app.Application;

public class OammbloApp extends Application {
	private TwitterService service;

	private static OammbloApp application;

	@Override
	public void onCreate() {
		super.onCreate();
		service = TwitterServiceFactory.getService(ServiceType.DEV);
		application = this;
	}

	public static OammbloApp getInstance() {
		return application;
	}

	public TwitterService getService() {
		return service;
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}

}
