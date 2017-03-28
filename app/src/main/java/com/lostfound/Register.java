package com.lostfound;
import android.app.*;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.*;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.KeyEvent.KEYCODE_ENTER;

public class Register extends Activity
{

	Button register_submit ;
	TextView name_in,pass_in,pass_again;
	boolean name_ok = false;


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);

		register_submit = (Button)findViewById(R.id.register_submit);
		name_in = (TextView)findViewById(R.id.name_in);
		pass_in = (TextView)findViewById(R.id.pass_in);
		pass_again = (TextView)findViewById(R.id.pass_again);

		/*
		短时后台handle
		 */
		final  Handler handle = new Handler();
		name_in.setOnKeyListener(new myKeyListener());
		name_in.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				String name = name_in.getText().toString();
				ImageView iv = (ImageView)findViewById(R.id.name_img);
				if (!hasFocus) {
					Toast.makeText(Register.this,name_in.getText().toString(),Toast.LENGTH_SHORT).show();
					if(isNameOk(name)) {
						iv.setImageResource(R.drawable.ok);
					}else {
						iv.setImageResource(R.drawable.notok);
					}

					}

			}
		});


		/*
		注册按钮单独设置点击监听器
		 */
		register_submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				onRegister();
			}
		});
	}


	/*
	isNameOk()
	判断用户名是否合法
	 */
	private boolean isNameOk(String name)
	{
		DataHelper dh = new DataHelper(this,"lostfound");
		SQLiteDatabase db = dh.getReadableDatabase();
		Cursor cur = db.rawQuery("select * from users where name='" + name +"'",null);
		if (cur.moveToNext()) {
			Toast.makeText(Register.this,"name exits",Toast.LENGTH_SHORT).show();
		}else {
		return  true;
		}
		cur.close();
		return  false;
	}

	/*
	onRegister ()
	处理注册
	 */
	private void onRegister()
	{
		DataHelper dh = new DataHelper(this,"lostfound");
		SQLiteDatabase db = dh.getWritableDatabase();
		ContentValues conv = new ContentValues();
		conv.put("name","admin");
		conv.put("passwd","123456");
		conv.put("permission_level",2);

		if (db != null) {
			db.insert("users",null,conv);
		}
	}


	/*
	 class : myKeyListener
	 监听用户名 密码输入框 密码确认框 输入 并过滤
	 */

	class myKeyListener implements View.OnKeyListener {
		public boolean onKey(View v,int keyCode,KeyEvent event){
			if (event.getAction() == KeyEvent.ACTION_UP) {
				if (keyCode == KEYCODE_ENTER ) {
					return  true;
				}
			}
			return  false;
		}

	}
}
