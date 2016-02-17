package com.example.learningsqliteadvance;

import com.example.learningsqliteadvance.adapter.MyAdapter;
import com.example.learningsqliteadvance.database.MyDatabase;
import com.example.learningsqliteadvance.model.Author;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ShowDetailAuthor extends Activity {

	Button btn_clear, btn_update;
	EditText edt_id;
	EditText edt_name;
	MyDatabase db;
	AlertDialog.Builder dialog;
	Dialog d;
	Author author;
	MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Dialog);
		setContentView(R.layout.show_detail_author);
		Intent i = getIntent();
		author = (Author) i.getSerializableExtra("currentItem");
		initView();
		initData();
		initListener();
	}

	private void initView() {
		btn_clear = (Button) findViewById(R.id.btn_addauthor_clear_update);
		btn_update = (Button) findViewById(R.id.btn_addauthor_save_update);
		edt_id = (EditText) findViewById(R.id.edt_author_id_update);
		edt_name = (EditText) findViewById(R.id.edt_author_name_update);
		dialog = new AlertDialog.Builder(this);
		db = new MyDatabase(this);
		db.open();

	}

	private void initData() {

		edt_id.setText(author.getCode());
		edt_name.setText(author.getName());
	}

	private void initListener() {
		btn_clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clearField();
			}
		});
		btn_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				db.updateData(author.getId(), edt_id.getText().toString(),
						edt_name.getText().toString());
			}
		});
	}

	private void clearField() {
		edt_id.setText("");
		edt_name.setText("");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	protected void onPause() {
		super.onPause();
	}

}
