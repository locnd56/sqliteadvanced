package com.example.learningsqliteadvance;

import java.io.ByteArrayOutputStream;

import com.example.learningsqliteadvance.database.MyDatabase;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Add_author extends Activity {
	Button btn_clear, btn_save;
	EditText edt_id;
	EditText edt_name;
	CheckBox cb_gender_woman, cb_gender_man;
	MyDatabase db;
	AlertDialog.Builder dialog;
	Dialog d;
	Bitmap bmFemale;
	Bitmap bmMale;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Dialog);
		setContentView(R.layout.add_author);
		initView();
		initData();
		initListener();
	}

	private void initView() {
		btn_clear = (Button) findViewById(R.id.btn_addauthor_clear);
		btn_save = (Button) findViewById(R.id.btn_addauthor_save);
		edt_id = (EditText) findViewById(R.id.edt_author_id);
		edt_name = (EditText) findViewById(R.id.edt_author_name);
		cb_gender_man = (CheckBox) findViewById(R.id.cb_gender_man);
		cb_gender_woman = (CheckBox) findViewById(R.id.cb_gender_woman);
		dialog = new AlertDialog.Builder(this);
		db = new MyDatabase(this);
	}

	private void initData() {
		db.open();
	}

	private void initListener() {
		btn_clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clearField();
			}
		});
		btn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (edt_id.getText().length() != 0
						&& edt_name.getText().length() != 0) {
					if (cb_gender_man.isChecked()) {
						db.createData(edt_id.getText().toString(), edt_name
								.getText().toString(),
								convertResourceToByte(R.drawable.male));
						dialog.setMessage("Add male author succesfully!");
						dialog.show();
						clearField();
					} else {
						db.createData(edt_id.getText().toString(), edt_name
								.getText().toString(),
								convertResourceToByte(R.drawable.female));
						dialog.setMessage("Add female author succesfully!");
						dialog.show();
						clearField();
					}

				} else {
					dialog.setMessage("Add author into database was failed");
					dialog.show();
				}
			}
		});
	}

	private void clearField() {
		edt_id.setText("");
		edt_name.setText("");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.e("watchlog", convertResourceToByte(R.drawable.male) + "");
	}

	@Override
	protected void onPause() {
		super.onPause();
		db.close();
	}

	public byte[] convertResourceToByte(int resource) {
		Bitmap bm = BitmapFactory.decodeResource(getResources(), resource);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] bitMapData = stream.toByteArray();
		return bitMapData;
	}
}
