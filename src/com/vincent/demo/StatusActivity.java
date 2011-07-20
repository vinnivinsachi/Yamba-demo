package com.vincent.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class StatusActivity extends Activity implements OnClickListener {
	EditText editStatus;
	Button buttonUpdate;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);
        editStatus = (EditText)findViewById(R.id.edit_status);
        buttonUpdate = (Button)findViewById(R.id.button_update);
        
        buttonUpdate.setOnClickListener(this);
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.d("Yamba", "onClicked");
	}
}