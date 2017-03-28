package com.lostfound;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.database.sqlite.*;
import android.content.*;
import android.view.View.*;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		lostfound = new DataHelper(MainActivity.this, "lostfound");
		SQLiteDatabase db = lostfound.getReadableDatabase();
		
		login_bt = (Button)findViewById(R.id.login_button);
		register_bt = (Button)findViewById(R.id.register_button);
		
		login_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v)
			{
				onLogin();
			}
		});


		
	}
	
	DataHelper lostfound = null;
	Button login_bt, register_bt;
	
	
	public void register_page (View v) {
		Intent it = new Intent();
		it.setClass(MainActivity.this,Register.class);
		
		MainActivity.this. startActivity(it);
		
		//setContentView(R.layout.register_layout);
		
	}
	
	public void onLogin()
	{
		Intent it = new Intent();
		it.setClass(this,Login.class);
		startActivity(it);
	}
}
