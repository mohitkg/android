package com.example.add_seq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FrontPage extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.frontpage);
	}
	
	/** Called when the user clicks the Play button */
	public void student(View v) {
	    // Do something in response to button
		Intent intent = new Intent(this, menu.class);
	    startActivity(intent);

	}
	
	/** Called when the user clicks the Record button */
	public void teacher(View v) {
	    // Do something in response to button
		Intent intent = new Intent(this, menu_teacher.class);
		
	    startActivity(intent);
		Log.d("record","clicked");
	}
}
