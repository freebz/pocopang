package com.freebz.pocopang.model;

import java.util.Arrays;
import java.util.List;

public class AnimalList {

	//private List<Animal> animals = new ArrayList<Animal>();
	
	private List<Animal> animals = Arrays.asList(new Animal[]{
		  new Animal(1, "라즈밤", 4)
		, new Animal(2, "로빈루키", 4)
		, new Animal(3, "블루밤", 325)
		, new Animal(4, "바이포", 325)
		, new Animal(5, "로즈혼", 325)
		, new Animal(6, "라이노", 325)
		, new Animal(7, "코디", 0)
		, new Animal(8, "우디포", 400)
		, new Animal(9, "메이린", 400)
		, new Animal(10, "히어로코코", 400)
		, new Animal(11, "오공지", 400)
		, new Animal(12, "스톤펀치", 400)
		, new Animal(13, "히어로보니", 400)
		, new Animal(14, "루", 400)
		, new Animal(15, "코코아", 0)
		, new Animal(16, "로젤리", 467)
		, new Animal(17, "하나비", 467)
		, new Animal(18, "둠둠", 467)
		, new Animal(19, "보니핑크", 467)
		, new Animal(20, "나비", 467)
		, new Animal(21, "코코", 0)
		, new Animal(22, "강치", 467)
		, new Animal(23, "골드", 467)
		, new Animal(24, "구리", 467)
		, new Animal(25, "젤리", 467)
		, new Animal(26, "하비", 467)
		, new Animal(27, "두두", 467)
		, new Animal(28, "보니", 467)
	});
	
	private int totalRaito;
	
	public AnimalList() {
		for (Animal animal : animals) {
			totalRaito += animal.getRatio();
		}
	}
	
	public Animal getRandom() {
		int value = (int) (Math.random() * totalRaito);
		for (Animal animal : animals) {
			value -= animal.getRatio();
			if (value <= 0) {
				return animal;
			}
		}
		return null;
	}
}
