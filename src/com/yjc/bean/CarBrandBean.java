package com.yjc.bean;

import java.io.Serializable;
/**
 * ³µµÄÆ·ÅÆ
 * @author yjc Jeff.Yao
 *
 */
public class CarBrandBean implements Serializable{
	private int _id;
	private int carsBrand_id;
	private int img;
	private String carsName;
	public int getCarsBrand_id() {
		return carsBrand_id;
	}
	public void setCarsBrand_id(int carsBrand_id) {
		this.carsBrand_id = carsBrand_id;
	}
	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}
	public String getCarsName() {
		return carsName;
	}
	public void setCarsName(String carsName) {
		this.carsName = carsName;
	}
	public CarBrandBean(int carsBrand_id, int img, String carsName) {
		super();
		this.carsBrand_id = carsBrand_id;
		this.img = img;
		this.carsName = carsName;
	}
	@Override
	public String toString() {
		return "CarBrandBean [_id=" + _id + ", carsBrand_id=" + carsBrand_id
				+ ", img=" + img + ", carsName=" + carsName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _id;
		result = prime * result + carsBrand_id;
		result = prime * result
				+ ((carsName == null) ? 0 : carsName.hashCode());
		result = prime * result + img;
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
		CarBrandBean other = (CarBrandBean) obj;
		if (_id != other._id)
			return false;
		if (carsBrand_id != other.carsBrand_id)
			return false;
		if (carsName == null) {
			if (other.carsName != null)
				return false;
		} else if (!carsName.equals(other.carsName))
			return false;
		if (img != other.img)
			return false;
		return true;
	}
	
	
}
