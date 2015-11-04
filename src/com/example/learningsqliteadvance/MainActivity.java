package com.example.learningsqliteadvance;

import com.example.learningsqliteadvance.database.MyDatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btn_add, btn_showview, btn_manager,btn_clear;
	MyDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initListener();
	}

	private void initView() {
		btn_add = (Button) findViewById(R.id.btn_addauthor);
		btn_manager = (Button) findViewById(R.id.btn_booksmanager);
		btn_showview = (Button) findViewById(R.id.btn_showlistauthors);
		btn_clear = (Button) findViewById(R.id.btn_mainactivity_clear);
		db = new MyDatabase(this);
	}

	private void initListener() {
		btn_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Add_author.class);
				startActivity(intent);
			}
		});
		btn_showview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						ShowListAuthors.class);
				startActivity(intent);
			}
		});
		btn_manager.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						BooksManager.class);
				startActivity(intent);
			}
		});
		btn_clear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				db.deleteAllData();
			}
		});
	}
	@Override
	protected void onResume() {
		super.onResume();
		db.open();
	}
}
