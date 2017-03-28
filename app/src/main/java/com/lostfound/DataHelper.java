package com.lostfound;
import android.database.sqlite.*;
import android.content.*;


/*
Sqlite数据库操作 继承自SQLiteOpenHelper
 */
public class DataHelper extends SQLiteOpenHelper
{

	final static int version = 1;
	SQLiteDatabase db = null;
	public DataHelper(Context con, String dbname)
	{
		super(con,dbname,null,version);
	}


	/*
	初始化数据库
	users表：用户名 密码  权限级别
	work表： 类型（失物/招领） 物品名字 物品信息 图片路径 物品分类
	 */
	@Override
	public void onCreate(SQLiteDatabase p1)
	{
		// TODO: Implement this method
		p1.execSQL("create table users( name varchar(50) not null primary key, passwd varchar(50) not null,permission_level int)");
		p1.execSQL("insert into users values('root','root',0)");
		p1.execSQL("create table work(type int,name varchar(50) not null, msg varchar(512),imgpath varchar(512),class varchar(50))");
		//p1.execSQL("create table found(name varchar(50) not null, msg varchar(256),imgpath varchar(512), class varchar(50))");
 
	}


	public SQLiteDatabase getDatabase()
	{
		return db;
	}
	@Override
	public void onUpgrade(SQLiteDatabase p1, int p2, int p3)
	{
		// TODO: Implement this method
	}
	
}
