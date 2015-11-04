package com.example.learningsqliteadvance.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.learningsqliteadvance.database.MyDatabase;
import com.example.learningsqliteadvance.model.Author;
import com.example.learningsqliteadvance.view.ShowDetailAuthorsItemView;

public class MyAdapter extends ArrayAdapter<Author> {
	Context context;
	List<Author> listAuthors;
	DialogInterface.OnClickListener onClickListener;
	MyDatabase db;

	public MyAdapter(Context context, int resource, List<Author> objects) {
		super(context, resource, objects);
		this.context = context;
		listAuthors = objects;
		db = new MyDatabase(context);
		
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		db.open();
		if (convertView == null) {
			convertView = new ShowDetailAuthorsItemView(context, null,
					listAuthors);
		}
		((ShowDetailAuthorsItemView) convertView).setView(listAuthors
				.get(position));
		((ShowDetailAuthorsItemView) convertView)
				.setOnPositiveListener(new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						int n = db.deleteData((listAuthors.get(position)
								.getId()));
						if (n > 0) {
							listAuthors.remove(position);
							notifyDataSetChanged();
							Toast.makeText(context, "Remove succesful",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(context, "Remove fail",
									Toast.LENGTH_SHORT).show();
						}

					}
				});
		return convertView;
	}
}
