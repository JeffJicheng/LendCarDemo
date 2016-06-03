package com.yjc.bean;

import java.io.Serializable;
/**
 * 购物bean
 * @author yjc Jeff.Yao
 *
 */
public class ShoppingBean implements Serializable{
	private int _id;
	private int shopping_id;//序号
	private String brandName;//品牌名
	private String carName;//车名
	private int nums;//数量
	private int price;//价钱
	private int img;
	
	private boolean isSelected;
	private int isSelectedNum;
	
	
	public ShoppingBean() {
		super();
	}
	
	
	public ShoppingBean(int shopping_id, String brandName, String carName,
			int nums, int price, int img, boolean isSelected,int isSelectedNum) {
		super();
		this.shopping_id = shopping_id;
		this.brandName = brandName;
		this.carName = carName;
		this.nums = nums;
		this.price = price;
		this.img = img;
		this.isSelected = isSelected;
		this.isSelectedNum = isSelectedNum;
	}


	public int getImg() {
		return img;
	}

	public void setImg(int img) {
		this.img = img;
	}

	public int getShopping_id() {
		return shopping_id;
	}
	public void setShopping_id(int shopping_id) {
		this.shopping_id = shopping_id;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ShoppingBean [_id=" + _id + ", shopping_id=" + shopping_id
				+ ", brandName=" + brandName + ", carName=" + carName
				+ ", nums=" + nums + ", price=" + price + ", img=" + img + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _id;
		result = prime * result
				+ ((brandName == null) ? 0 : brandName.hashCode());
		result = prime * result + ((carName == null) ? 0 : carName.hashCode());
		result = prime * result + img;
		result = prime * result + nums;
		result = prime * result + price;
		result = prime * result + shopping_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingBean other = (ShoppingBean) obj;
		if (_id != other._id)
			return false;
		if (brandName == null) {
			if (other.brandName != null)
				return false;
		} else if (!brandName.equals(other.brandName))
			return false;
		if (carName == null) {
			if (other.carName != null)
				return false;
		} else if (!carName.equals(other.carName))
			return false;
		if (img != other.img)
			return false;
		if (nums != other.nums)
			return false;
		if (price != other.price)
			return false;
		if (shopping_id != other.shopping_id)
			return false;
		return true;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}


	public int getIsSelectedNum() {
		return isSelectedNum;
	}


	public void setIsSelectedNum(int isSelectedNum) {
		this.isSelectedNum = isSelectedNum;
	}
	
	
	
}
