package com.vincent.demo;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class YambaApplication extends Application {

	SharedPreferences prefs;
	Twitter twitter;

	// this is created when the application is created
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		prefs = PreferenceManager.getDefaultSharedPreferences(this);
	}

	
	/**
	 * Lazily initializes the connection to the online service
	 * @return Twitter object representing connection to online service
	 */
	public Twitter getTwitter() {
		if (twitter == null) { // this method is lazy initialization

			// get shared preferences values
			String username = prefs.getString("username", "");
			String password = prefs.getString("password", "");

			// use it in twitter
			twitter = new Twitter(username, password);

			twitter.setAPIRootUrl("http://yamba.marakana.com/api");

		}
		return twitter;
	}

}
