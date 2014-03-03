package com.freebz.pocopang.model;


public class Animal {

	private int id;
	private String name;
	private int ratio;
	private int count;
	
	public Animal(int id, String name, int ratio) {
		this.id = id;
		this.name = name;
		this.ratio = ratio;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRatio() {
		return ratio;
	}
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
