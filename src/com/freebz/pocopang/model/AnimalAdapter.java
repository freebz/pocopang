package com.freebz.pocopang.model;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.freebz.pocopang.R;


public class AnimalAdapter extends BaseAdapter {
	
	private ArrayList<Animal> animals = new ArrayList<Animal>();

	@Override
	public int getCount() {
		return animals.size();
	}

	@Override
	public Object getItem(int index) {
		return getItem(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			view = inflater.inflate(R.layout.animal_list_item, parent, false);
		}
		
		Animal animal = animals.get(index);
		
		
		return view;
	}

}
