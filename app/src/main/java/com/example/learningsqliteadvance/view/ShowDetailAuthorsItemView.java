package com.example.learningsqliteadvance.view;

import java.util.List;

import com.example.learningsqliteadvance.R;
import com.example.learningsqliteadvance.adapter.MyAdapter;
import com.example.learningsqliteadvance.database.MyDatabase;
import com.example.learningsqliteadvance.interfaces.ISimpleAction;
import com.example.learningsqliteadvance.model.Author;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowDetailAuthorsItemView extends LinearLayout {
	Context context;
	TextView tv_id, tv_code, tv_name;
	ImageView iv_delete, iv_avatar;
	List<Author> listAuthors;
	MyDatabase db;
	AlertDialog.Builder dialog;
	ISimpleAction deleteAction;
	DialogInterface.OnClickListener onClickListener;
	int position;

	public ShowDetailAuthorsItemView(Context context, AttributeSet attrs,
			List<Author> listAuthors) {
		super(context, attrs);
		this.context = context;
		this.listAuthors = listAuthors;
		initView();
		initListener();
	}

	private void initView() {
		LayoutInflater inflate = (LayoutInflater) context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		View view = inflate.inflate(R.layout.listview_item, this);
		tv_id = (TextView) view.findViewById(R.id.tv_author_id);
		tv_code = (TextView) view.findViewById(R.id.tv_author_code);
		tv_name = (TextView) view.findViewById(R.id.tv_author_name);
		iv_delete = (ImageView) view.findViewById(R.id.iv_delete);
		iv_avatar = (ImageView) view.findViewById(R.id.iv_avatar);
		db = new MyDatabase(context);
		db.open();
		dialog = new AlertDialog.Builder(context);
	}

	private void initListener() {
		iv_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.setTitle("Delete entry")
						.setMessage(
								"Are you sure you want to delete this entry?")
						.setPositiveButton(android.R.string.yes,
								onClickListener)
						.setNegativeButton(android.R.string.no,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).show();
			}
		});
	}

	public void setView(Author item) {
		if (item != null) {
			tv_id.setText(String.valueOf(item.getId()));
			tv_code.setText(item.getCode());
			tv_name.setText(item.getName());
			iv_avatar.setImageBitmap(item.getImage());
		}
	}

	public void setOnPositiveListener(
			DialogInterface.OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}
}
