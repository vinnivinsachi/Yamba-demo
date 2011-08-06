package com.vincent.demo;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StatusActivity extends Activity{
	EditText editStatus;
	ProgressDialog postingDialog;
	static final int DIALOG_ID = 100;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);
        editStatus = (EditText)findViewById(R.id.edit_status);
       // Debug.startMethodTracing("Yamba.trace");
    }
    
	@Override
	protected void onStop() {
		super.onStop();
		//Debug.stopMethodTracing();
	}

	

	

	public void onClick(View v) {
		String status = editStatus.getText().toString();

		showDialog(DIALOG_ID);
		new PostToTwitter().execute(status);
	
		
		Log.d("yamba", "onClicked "+status);
	}
	
	///////dialog stuff
	@Override
	protected Dialog onCreateDialog(int id) {
		switch(id){
		
			case DIALOG_ID: {
				ProgressDialog dialog = new ProgressDialog(this);
		         dialog.setTitle(StatusActivity.this.getString(R.string.progress_title));
		         dialog.setMessage(StatusActivity.this.getString(R.string.progress_string));
		         dialog.setIndeterminate(true);
		         dialog.setCancelable(true);
		         return dialog;
			}
		
		}
		
		return null;
		 
    }

	
	
	///////Menu Stuff

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		//instead of 
		//get menu
		//menu.add(r.menuitem1)
		//menu.add(r.menuitem2)
		//menu.add(r.menuitem3)
		//we call the getMenuInflater and inflate the menu with the menu.xml
		
		getMenuInflater().inflate(R.menu.menu, menu);
		

		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
			case R.id.itemPrefs:
				startActivity(new Intent(this, PrefsActivity.class));
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	///////PostToTwitter stuff
	private class PostToTwitter extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... status) { // it packages all arguments
			//into an array. 
			String result;
			try{
				Twitter twitter = new Twitter("student", "password");
				twitter.setAPIRootUrl("http://yamba.marakana.com/api");
				twitter.setStatus(status[0]);//access the first element of argument array
				result = StatusActivity.this.getString(R.string.possitive_feedback);
			}catch (TwitterException e){
				result = StatusActivity.this.getString(R.string.negative_feedback);
				Log.e("yamba", "Can't Connect to Server", e);
				e.printStackTrace();
			}
			
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dismissDialog(DIALOG_ID);
			Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG).show(); // must have .show() or it will not compile
		}
		
		
	}
}

