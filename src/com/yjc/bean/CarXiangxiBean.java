package com.yjc.bean;

import java.io.Serializable;
/**
 * 汽车详细信息bean
 * @author yjc Jeff.Yao
 *
 */
public class CarXiangxiBean implements Serializable{
	// 车辆（编号，车系，名称，图片，级别、发动机、变速箱、结构、价格）
	private int _id;
	private int xiangxi_id;
	private String typeName;//分类名
	private String carName;//车类型名 如：宝马X1 2014款 sDriver18i 手动型
	private int img;//车的图片
	private String jibie;//级别
	private String fadongji;//发动机
	private String biansuxiang;//变速箱
	private String jiegou;//结构
	private int price;//价钱
	
	public CarXiangxiBean() {
		super();
	}
	public CarXiangxiBean(int xiangxi_id, String typeName, String carName,
			int img, String jibie, String fadongji, String biansuxiang,
			String jiegou, int price) {
		super();
		this.xiangxi_id = xiangxi_id;
		this.typeName = typeName;
		this.carName = carName;
		this.img = img;
		this.jibie = jibie;
		this.fadongji = fadongji;
		this.biansuxiang = biansuxiang;
		this.jiegou = jiegou;
		this.price = price;
	}
	public int getXiangxi_id() {
		return xiangxi_id;
	}
	public void setXiangxi_id(int xiangxi_id) {
		this.xiangxi_id = xiangxi_id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}
	public String getJibie() {
		return jibie;
	}
	public void setJibie(String jibie) {
		this.jibie = jibie;
	}
	public String getFadongji() {
		return fadongji;
	}
	public void setFadongji(String fadongji) {
		this.fadongji = fadongji;
	}
	public String getBiansuxiang() {
		return biansuxiang;
	}
	public void setBiansuxiang(String biansuxiang) {
		this.biansuxiang = biansuxiang;
	}
	public String getJiegou() {
		return jiegou;
	}
	public void setJiegou(String jiegou) {
		this.jiegou = jiegou;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _id;
		result = prime * result
				+ ((biansuxiang == null) ? 0 : biansuxiang.hashCode());
		result = prime * result + ((carName == null) ? 0 : carName.hashCode());
		result = prime * result
				+ ((fadongji == null) ? 0 : fadongji.hashCode());
		result = prime * result + img;
		result = prime * result + ((jibie == null) ? 0 : jibie.hashCode());
		result = prime * result + ((jiegou == null) ? 0 : jiegou.hashCode());
		result = prime * result + price;
		result = prime * result
				+ ((typeName == null) ? 0 : typeName.hashCode());
		result = prime * result + xiangxi_id;
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
		CarXiangxiBean other = (CarXiangxiBean) obj;
		if (_id != other._id)
			return false;
		if (biansuxiang == null) {
			if (other.biansuxiang != null)
				return false;
		} else if (!biansuxiang.equals(other.biansuxiang))
			return false;
		if (carName == null) {
			if (other.carName != null)
				return false;
		} else if (!carName.equals(other.carName))
			return false;
		if (fadongji == null) {
			if (other.fadongji != null)
				return false;
		} else if (!fadongji.equals(other.fadongji))
			return false;
		if (img != other.img)
			return false;
		if (jibie == null) {
			if (other.jibie != null)
				return false;
		} else if (!jibie.equals(other.jibie))
			return false;
		if (jiegou == null) {
			if (other.jiegou != null)
				return false;
		} else if (!jiegou.equals(other.jiegou))
			return false;
		if (price != other.price)
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		if (xiangxi_id != other.xiangxi_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CarXiangxiBean [_id=" + _id + ", xiangxi_id=" + xiangxi_id
				+ ", typeName=" + typeName + ", carName=" + carName + ", img="
				+ img + ", jibie=" + jibie + ", fadongji=" + fadongji
				+ ", biansuxiang=" + biansuxiang + ", jiegou=" + jiegou
				+ ", price=" + price + "]";
	}
	
	
	
}
