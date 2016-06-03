package com.yjc.bean;

import java.io.Serializable;
/**
 * 车子类型品牌
 * @author yjc Jeff.Yao
 *
 */
public class CarTypeBean implements Serializable{
	private int _id;
	private int type_id;
	private int img;
	private String brandName;
	private String name;
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
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
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public CarTypeBean(int type_id, int img, String brandName, String name) {
		super();
		this.type_id = type_id;
		this.img = img;
		this.brandName = brandName;
		this.name = name;
	}
	public CarTypeBean() {
		super();
	}
	
}
