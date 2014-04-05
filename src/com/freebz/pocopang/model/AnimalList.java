package com.freebz.pocopang.model;

import java.util.Arrays;
import java.util.List;

public class AnimalList {
	
	// Max Id 37
	private List<Animal> animals = Arrays.asList(new Animal[]{
		  new Animal(35, "태극", 225, 1, 50)
		, new Animal(1, "고백해보니", 0, 1, 100)
		, new Animal(2, "라즈밤", 225, 1, 200)
		, new Animal(3, "로빈루키", 225, 1, 300)
		, new Animal(36, "수리", 838, 2, 350)
		, new Animal(4, "치코", 838, 2, 400)
		, new Animal(5, "타고보니", 0, 2, 500)
		, new Animal(6, "블루밤", 838, 2, 600)
		, new Animal(7, "바이포", 838, 2, 700)
		, new Animal(8, "로즈혼", 838, 2, 800)
		, new Animal(9, "라이노", 838, 2, 900)
//		, new Animal(10, "코디", 0, 2, 1000)
		, new Animal(37, "나비마루", 918, 3, 1050)
		, new Animal(11, "탐탐", 918, 3, 1100)
		, new Animal(12, "보니체리", 918, 3, 1200)
		, new Animal(13, "우디포", 918, 3, 1300)
		, new Animal(14, "메이린", 918, 3, 1400)
//		, new Animal(15, "히어로코코", 0, 3, 1500)
		, new Animal(16, "오공지", 918, 3, 1600)
		, new Animal(17, "스톤펀치", 918, 3, 1700)
//		, new Animal(18, "히어로보니", 0, 3, 1800)
		, new Animal(19, "루", 918, 3, 1900)
		, new Animal(20, "코코아", 0, 3, 2000)
		, new Animal(21, "쿠로리", 928, 4, 2100)
		, new Animal(22, "로젤리", 928, 4, 2200)
		, new Animal(23, "하나비", 928, 4, 2300)
		, new Animal(24, "둠둠", 928, 4, 2400)
		, new Animal(25, "보니핑크", 928, 4, 2500)
		, new Animal(26, "나비", 928, 4, 2600)
//		, new Animal(27, "코코", 0, 4, 2700)
		, new Animal(28, "강치", 928, 4, 2800)
		, new Animal(29, "골드", 928, 4, 2900)
		, new Animal(30, "구리", 928, 4, 3000)
		, new Animal(31, "젤리", 928, 4, 3100)
		, new Animal(32, "하비", 928, 4, 3200)
		, new Animal(33, "두두", 928, 4, 3300)
		, new Animal(34, "보니", 928, 4, 3400)
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
	
	public List<Animal> getAnimalList() {
		return animals;
	}
}
