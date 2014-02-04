package com.freebz.pocopang.model;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.freebz.pocopang.R;


public class AnimalAdapter extends BaseAdapter {
	
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	
	public AnimalAdapter() {
		
		AnimalList list = new AnimalList();
		
		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
//		animals.add(list.getRandom());
		
	}

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
		
		TextView name = (TextView) view.findViewById(R.id.name);
		name.setText(animal.getName());
		
		TextView count = (TextView) view.findViewById(R.id.count);
		count.setText("0");
		
		return view;
	}
	
	public void addAnimal(Animal animal) {
		animals.add(animal);
	}

}
