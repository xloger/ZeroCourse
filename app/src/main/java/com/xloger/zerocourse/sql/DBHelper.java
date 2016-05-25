package com.xloger.zerocourse.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "timeTable.db";
	public static final String TABLE_NAME = "TimeTable";
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	//第一次创建数据库时调用，创建表
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql="CREATE TABLE if not exists "+
                TABLE_NAME+
				"(id INTEGER PRIMARY KEY,"+
				"week INTEGER,"+
				"park INTEGER,"+
				"name VARCHAR,"+
				"place VARCHAR,"+
				"teacher VARCHAR,"+
				"length VARCHAR,"+
				"friend VARCHAR,"+
				"isDan INTEGER)";
		db.execSQL(sql);
	}
	//版本更新时调用
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
