package com.example.add_seq;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Sql {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_SEQ_NAME = "sequence_name";
	public static final String KEY_LENGTH = "number_of_images";
	
	private static final String DATABASE_NAME = "SequenceDb.db";
	private static final String DATABASE_TABLE = "logTable";
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private String seq_name;
	private String length;
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_SEQ_NAME + " TEXT NOT NULL, " +
					KEY_LENGTH + " TEXT NOT NULL);"
			);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public Sql(Context c){
		ourContext = c;
	}
	
	public void setname(String sname)
	{
		seq_name = sname;
		Log.d("sname","sname->"+seq_name);
	}

	public void setlevel(Integer level)
	{
		length =level.toString();
		Log.d("game_level","game_level->"+length);
	}
	
	public Sql open(){
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
	
	public long createEntry(String name, String length){
		ContentValues cv = new ContentValues();
		cv.put(KEY_SEQ_NAME, name);
		cv.put(KEY_LENGTH, length);
		Log.d("db", "entry in db");
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	
	public String getData(String length_query) {
		// TODO Auto-generated method stub
		
		String[] columns = new String[]{KEY_ROWID, KEY_SEQ_NAME, KEY_LENGTH};
		String where_clause = KEY_LENGTH + "= ?";
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, where_clause,  new String[]{length_query}, null, null, null, null);
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_SEQ_NAME);
		int iLevel = c.getColumnIndex(KEY_LENGTH);
		
		for(c.moveToFirst(); !c.isAfterLast();c.moveToNext()){			
			result = result + c.getString(iName)+"\n";
			
		}
		
		return result;
	}
	
}
