package com.freebz.pocopang.model;

import java.util.Arrays;
import java.util.List;

public class AnimalList {

	//private List<Animal> animals = new ArrayList<Animal>();
	
	private List<Animal> animals = Arrays.asList(new Animal[]{
		  new Animal(1, "�����", 4)
		, new Animal(2, "�κ��Ű", 4)
		, new Animal(3, "����", 325)
		, new Animal(4, "������", 325)
		, new Animal(5, "����ȥ", 325)
		, new Animal(6, "���̳�", 325)
		, new Animal(7, "�ڵ�", 0)
		, new Animal(8, "�����", 400)
		, new Animal(9, "���̸�", 400)
		, new Animal(10, "���������", 400)
		, new Animal(11, "������", 400)
		, new Animal(12, "������ġ", 400)
		, new Animal(13, "����κ���", 400)
		, new Animal(14, "��", 400)
		, new Animal(15, "���ھ�", 0)
		, new Animal(16, "������", 467)
		, new Animal(17, "�ϳ���", 467)
		, new Animal(18, "�ҵ�", 467)
		, new Animal(19, "������ũ", 467)
		, new Animal(20, "����", 467)
		, new Animal(21, "����", 0)
		, new Animal(22, "��ġ", 467)
		, new Animal(23, "���", 467)
		, new Animal(24, "����", 467)
		, new Animal(25, "����", 467)
		, new Animal(26, "�Ϻ�", 467)
		, new Animal(27, "�ε�", 467)
		, new Animal(28, "����", 467)
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
