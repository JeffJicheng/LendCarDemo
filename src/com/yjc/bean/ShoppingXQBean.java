package com.yjc.bean;

import java.io.Serializable;
/**
 * ¹ºÎï³µÏêÇébean
 * @author yjc Jeff.Yao
 *
 */
public class ShoppingXQBean implements Serializable{
	private int _id;
	private int img;
	private String name;
	private int money;
	private int numbers;
	
	
	public ShoppingXQBean() {
		super();
	}
	
	public ShoppingXQBean(int img, String name, int money, int numbers) {
		super();
		this.img = img;
		this.name = name;
		this.money = money;
		this.numbers = numbers;
	}

	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getNumbers() {
		return numbers;
	}
	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
	
}
