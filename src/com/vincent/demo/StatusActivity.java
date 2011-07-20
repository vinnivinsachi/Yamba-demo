package com.vincent.demo;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StatusActivity extends Activity{
	EditText editStatus;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);
        editStatus = (EditText)findViewById(R.id.edit_status);
        
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		String status = editStatus.getText().toString();
		String result;
		try{
			Twitter twitter = new Twitter("student", "password");
			twitter.setAPIRootUrl("http://yamba.marakana.com/api");
			twitter.setStatus(status);
			result = "Message posted successfully";
		}catch (TwitterException e){
			result = "Failed posting status";
			Log.e("yamba", "Can't Connect to Server", e);
			//e.printStackTrace();
		}
		
		Toast.makeText(this, result, Toast.LENGTH_LONG).show(); // must have .show() or it will not compile
		
		Log.d("Yamba", "onClicked "+status);
	}
}