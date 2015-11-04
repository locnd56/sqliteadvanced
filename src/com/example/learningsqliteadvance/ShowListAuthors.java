package com.example.learningsqliteadvance;

import java.util.ArrayList;
import java.util.List;

import com.example.learningsqliteadvance.adapter.MyAdapter;
import com.example.learningsqliteadvance.database.MyDatabase;
import com.example.learningsqliteadvance.model.Author;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class ShowListAuthors extends Activity {
	ListView lv_show;
	CheckBox cb_deleteall;
	TextView tv_cancel;
	MyAdapter adapter;
	MyDatabase db;
	List<Author> dataListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_list_authors);
		initView();
		initData();
		initListener();
	}

	private void initView() {
		lv_show = (ListView) findViewById(R.id.lv_showlistauthors);
		cb_deleteall = (CheckBox) findViewById(R.id.cb_showlistauthor_deleteall);
		tv_cancel = (TextView) findViewById(R.id.tv_showlistauthor_cancel);
	}

	private void initData() {
		db = new MyDatabase(this);
		db.open();
		dataListView = new ArrayList<Author>();
		adapter = new MyAdapter(this, android.R.layout.simple_list_item_1,
				dataListView);
		lv_show.setAdapter(adapter);

	}

	private void initListener() {
		lv_show.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> container, View view,
					int position, long id) {
				Intent intent = new Intent(ShowListAuthors.this,
						ShowDetailAuthor.class);
				Author author = db.getData().get(position);
				intent.putExtra("currentItem", author);
				startActivity(intent);
			}
		});
		tv_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (cb_deleteall.isChecked()) {
					db.deleteAllData();

					dataListView.clear();
					// dataListView = new ArrayList<Author>();
					adapter.notifyDataSetChanged();
					cb_deleteall.setChecked(false);
				}
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		refreshData();
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.e("watchlog", "ShowListAuthors pause");
	}

	public void refreshData() {
		dataListView.clear();
		dataListView.addAll(db.getData());
		adapter.notifyDataSetChanged();
	}
}
