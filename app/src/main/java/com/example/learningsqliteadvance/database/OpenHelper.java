package com.example.learningsqliteadvance.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class OpenHelper
 */
public class OpenHelper extends SQLiteOpenHelper {
	public Context context;
	/* Ten database */
	private static final String DATABASE_NAME = "DB_AUTHOR";
	/* Version database */
	private static final int DATABASE_VERSION = 2;
	/* Ten tabel va cac column trong database */
	public static final String TABLE_AUTHOR = "AUTHOR";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_CODE = "matacgia";
	public static final String COLUMN_NAME = "tentacgia";
	public static final String COLUMN_PHOTO = "hinhanh";

	public OpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_AUTHOR + " (" + COLUMN_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CODE
				+ " TEXT NOT NULL, " + COLUMN_NAME + " TEXT NOT NULL, "
				+ COLUMN_PHOTO + " BLOB NOT NULL);");
		// doOnCreateDb(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUTHOR);
		onCreate(db);
	}

	private void doOnCreateDb(SQLiteDatabase db) {
		db = context.openOrCreateDatabase("account.db", Context.MODE_PRIVATE,
				null);
		db = SQLiteDatabase.openDatabase("account.db", null,
				SQLiteDatabase.CREATE_IF_NECESSARY);
	}
}
