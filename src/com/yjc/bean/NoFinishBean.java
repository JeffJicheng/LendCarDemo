package com.yjc.bean;

import java.io.Serializable;

/**
 * ¸¶¿îÎ´Íê³É
 * @author yjc Jeff.Yao
 *
 */
public class NoFinishBean implements Serializable{
	private int _id;
	private int img;
	private String name;
	private String price;
	private String numbers;
	
	public NoFinishBean() {
		super();
	}
	public NoFinishBean(int img, String name, String price, String numbers) {
		super();
		this.img = img;
		this.name = name;
		this.price = price;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	
}
