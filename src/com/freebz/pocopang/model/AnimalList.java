package com.freebz.pocopang.model;

import java.util.Arrays;
import java.util.List;

public class AnimalList {

	//private List<Animal> animals = new ArrayList<Animal>();
	
	private List<Animal> animals = Arrays.asList(new Animal[]{
		  new Animal(1, "고백해보니", 4)
		, new Animal(2, "라즈밤", 4)
		, new Animal(3, "로빈루키", 325)
		, new Animal(4, "치코", 325)
		, new Animal(5, "타고보니", 325)
		, new Animal(6, "블루밤", 325)
		, new Animal(7, "바이포", 325)
		, new Animal(8, "로즈혼", 400)
		, new Animal(9, "라이노", 400)
		, new Animal(10, "코디", 400)
		, new Animal(11, "탐탐", 400)
		, new Animal(12, "보니체리", 400)
		, new Animal(13, "우디포", 400)
		, new Animal(14, "메이린", 400)
		, new Animal(15, "히어로코코", 400)
		, new Animal(16, "오공지", 467)
		, new Animal(17, "스톤펀치", 400)
		, new Animal(18, "히어로보니", 400)
		, new Animal(19, "루", 400)
		, new Animal(20, "코코아", 400)
		, new Animal(21, "쿠로리", 400)
		, new Animal(22, "로젤리", 400)
		, new Animal(23, "하나비", 400)
		, new Animal(24, "둠둠", 467)
		, new Animal(25, "보니핑크", 467)
		, new Animal(26, "나비", 467)
		, new Animal(27, "코코", 467)
		, new Animal(28, "강치", 467)
		, new Animal(29, "골드", 0)
		, new Animal(30, "구리", 467)
		, new Animal(31, "젤리", 467)
		, new Animal(32, "하비", 467)
		, new Animal(33, "두두", 467)
		, new Animal(34, "보니", 467)

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
