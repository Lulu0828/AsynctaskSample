package com.dome.asynctasksample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	
	Button asyncTastButton;
	Button threadHandlerButton;
	Button threadHandlerPostButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		asyncTastButton = (Button)findViewById(R.id.AsyncTast);
		threadHandlerButton = (Button)findViewById(R.id.ThreadHandler);
		threadHandlerPostButton = (Button)findViewById(R.id.ThreadHandlerPost);
		
		asyncTastButton.setOnClickListener(this);
		threadHandlerButton.setOnClickListener(this);
		threadHandlerPostButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		
		if (v == asyncTastButton) {
			Intent intent1 = new Intent(this, AsyncTastActivity.class);
			startActivity(intent1);
		} else if (v == threadHandlerButton) {
			Intent intent2 = new Intent(this, ThreadHandlerActivity.class);
			startActivity(intent2);
		} else if (v == threadHandlerPostButton) {
			Intent intent3 = new Intent(this, ThreadHandlerPostActivity.class);
			startActivity(intent3);
		}
	}

}
