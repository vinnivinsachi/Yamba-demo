package com.vincent.demo;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PrefsActivity extends PreferenceActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTitle(R.string.title_preferences);
		addPreferencesFromResource(R.xml.prefs);
	}
	
} 
 