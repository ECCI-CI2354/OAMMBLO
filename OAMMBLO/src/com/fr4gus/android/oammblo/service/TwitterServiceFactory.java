package com.fr4gus.android.oammblo.service;

public class TwitterServiceFactory {
	public static enum ServiceType {
		TEST, DEV, PRODUCTION
	}

	public static TwitterService getService(ServiceType type) {
		switch (type) {
		case TEST:
			return new InMemoryTwitterService();
		case DEV:
		case PRODUCTION:
			return new IdentiCaTwitterService();
		}
		return null;
	}
}
