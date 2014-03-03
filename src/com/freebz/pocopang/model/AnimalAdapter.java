package com.freebz.pocopang.model;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.freebz.pocopang.R;


public class AnimalAdapter extends CursorAdapter {
	
	@SuppressWarnings("deprecation")
	public AnimalAdapter(Context context, Cursor cursor) {
		super(context, cursor);
	}
	
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView name = (TextView) view.findViewById(R.id.name);
		name.setText(cursor.getString(cursor.getColumnIndex("name")));
		
		TextView count = (TextView) view.findViewById(R.id.count);
		count.setText(cursor.getString(cursor.getColumnIndex("count")));
		
		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		int resId = context.getResources().getIdentifier("_" + id, "drawable", "com.freebz.pocopang");
		ImageView image = (ImageView) view.findViewById(R.id.image);
		image.setImageResource(resId);
	}
	
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.animal_list_item, parent, false);
		return view;
	}

}
