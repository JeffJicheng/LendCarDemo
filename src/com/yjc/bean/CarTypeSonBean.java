package com.yjc.bean;

import java.io.Serializable;
/**
 * 车子品牌子类的bean
 * @author yjc Jeff.Yao
 *
 */
public class CarTypeSonBean implements Serializable{
	private int _id;
	private int typeson_id;//子id
	private String introduction;//介绍
	private int img;//图片
	private String XinghaoName;
	public int getTypeson_id() {
		return typeson_id;
	}
	public void setTypeson_id(int typeson_id) {
		this.typeson_id = typeson_id;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}
	
	public String getXinghaoName() {
		return XinghaoName;
	}
	public void setXinghaoName(String xinghaoName) {
		XinghaoName = xinghaoName;
	}
	public CarTypeSonBean(int typeson_id, String XinghaoName,String introduction, int img) {
		super();
		this.typeson_id = typeson_id;
		this.XinghaoName = XinghaoName;
		this.introduction = introduction;
		this.img = img;
	}
	
	public CarTypeSonBean() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _id;
		result = prime * result + img;
		result = prime * result
				+ ((introduction == null) ? 0 : introduction.hashCode());
		result = prime * result + typeson_id;
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
		CarTypeSonBean other = (CarTypeSonBean) obj;
		if (_id != other._id)
			return false;
		if (img != other.img)
			return false;
		if (introduction == null) {
			if (other.introduction != null)
				return false;
		} else if (!introduction.equals(other.introduction))
			return false;
		if (typeson_id != other.typeson_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CarTypeSonBean [_id=" + _id + ", typeson_id=" + typeson_id
				+ ", introduction=" + introduction + ", img=" + img + "]";
	}
	
}
