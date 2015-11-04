package com.example.learningsqliteadvance.database;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.learningsqliteadvance.model.Author;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.provider.SyncStateContract.Columns;
import android.widget.Toast;

public class MyDatabase {
	/* Các đối tượng khác */
	private static Context context;
	static SQLiteDatabase db;
	private OpenHelper openHelper;

	public MyDatabase(Context context) {
		MyDatabase.context = context;
	}

	/*
	 * Hàm mở kết nối tới database
	 */
	public MyDatabase open() throws SQLiteException {
		openHelper = new OpenHelper(context);
		db = openHelper.getWritableDatabase();
		return this;
	}

	/*
	 * Hàm đóng kết nối database
	 */
	public void close() {
		openHelper.close();
	}

	/*
	 * Hàm createData dùng để chèn dữ liệu mới dữ liệu vào database
	 */
	public void createData(String matacgia, String tentacgia, byte[] image) {
		ContentValues cv = new ContentValues();
		cv.put(OpenHelper.COLUMN_CODE, matacgia);
		cv.put(OpenHelper.COLUMN_NAME, tentacgia);
		cv.put(OpenHelper.COLUMN_PHOTO, image);
		if (db.insert(OpenHelper.TABLE_AUTHOR, null, cv) == -1) {
			Toast.makeText(context, "Faild to insert record", Toast.LENGTH_LONG)
					.show();
		} else {
			Toast.makeText(context, "insert record is successul",
					Toast.LENGTH_LONG).show();
		}
	}

	/*
	 * Hamf getData trả về taonf bộ dữ liệu của table ACCount của database dưới
	 * 1 chuỗi
	 */
	/*
	 * Hàm getData trả về toàn bộ dữ liệu của table ACCOUNT của database dưới 1
	 * chuỗi
	 */
	public List<Author> getData() {
		/*
		 * Cursor c = db.query(OpenHelper.TABLE_ACCOUNT, null, null, null, null,
		 * null, null); c.moveToFirst(); String data=""; while(c.isAfterLast()
		 * == false){
		 * data+=c.getString(0)+"-"+c.getString(1)+"-"+c.getString(2)+
		 * "-"+c.getString(3); data+="\n"; c.moveToNext(); }
		 * Toast.makeText(this,data,Toast.LENGTH_LONG).show(); c.close;
		 */

		String[] columns = new String[] { OpenHelper.COLUMN_ID,
				OpenHelper.COLUMN_CODE, OpenHelper.COLUMN_NAME,
				OpenHelper.COLUMN_PHOTO };
		Cursor c = db.query(OpenHelper.TABLE_AUTHOR, columns, null, null, null,
				null, null);
		/*
		 * if(c==null) Log.v("Cursor", "C is NULL");
		 */
		List<Author> result = new ArrayList<Author>();
		// getColumnIndex(COLUMN_ID); là lấy chỉ số, vị trí của cột COLUMN_ID
		// ...
		int iRow = c.getColumnIndex(OpenHelper.COLUMN_ID);
		int iCode = c.getColumnIndex(OpenHelper.COLUMN_CODE);
		int iName = c.getColumnIndex(OpenHelper.COLUMN_NAME);
		int iImage = c.getColumnIndex(OpenHelper.COLUMN_PHOTO);
		// Vòng lặp lấy dữ liệu của con trỏ
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			Bitmap bitMap = getPhoto(c.getBlob(c.getColumnIndex(OpenHelper.COLUMN_PHOTO)));
			Author aut = new Author(Integer.parseInt(c.getString(iRow)),
					c.getString(iCode), c.getString(iName), bitMap);

			result.add(aut);
		}
		c.close();
		// Log.v("Result", result);
		return result;
	}

	public int deleteData(int id) {
		// Xoa du lieu theo 1 ma nao do
		int n = db.delete(OpenHelper.TABLE_AUTHOR, OpenHelper.COLUMN_ID + "=?",
				new String[] { String.valueOf(id) });
		return n;

	}

	public void deleteAllData() {
		// Xoa toan bo du lieu trong bang
		db.delete(OpenHelper.TABLE_AUTHOR, null, null);
	}

	public int updateData(int id, String code, String name) {
		ContentValues values = new ContentValues();
		values.put(OpenHelper.COLUMN_ID, id);
		values.put(OpenHelper.COLUMN_CODE, code);
		values.put(OpenHelper.COLUMN_NAME, name);
		int ret = db.update(OpenHelper.TABLE_AUTHOR, values,
				OpenHelper.COLUMN_ID + "=?",
				new String[] { String.valueOf(id) });
		// if (ret == 0) {
		// Toast.makeText(context, "Faild to insert record", Toast.LENGTH_LONG)
		// .show();
		// } else {
		// Toast.makeText(context, "insert record is successul",
		// Toast.LENGTH_LONG).show();
		// }
		return ret;
	}

	public static Bitmap getPhoto(byte[] image) {
		return BitmapFactory.decodeByteArray(image, 0, image.length);
	}
}
