package com.yjc.bean;

import java.io.Serializable;
/**
 *  Œ“µƒ ’≤ÿbean
 * @author yjc Jeff.Yao
 *
 */
public class MyShoucangBean implements Serializable{
	private int _id;
	private int img;
	private String name;
	private String price;
	public MyShoucangBean() {
		super();
	}
	public MyShoucangBean(int img, String name, String price) {
		super();
		this.img = img;
		this.name = name;
		this.price = price;
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
	
}
