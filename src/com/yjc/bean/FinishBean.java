package com.yjc.bean;

import java.io.Serializable;

/**
 * 付款完成
 * @author yjc Jeff.Yao
 *金额和下单时间和订单状态  订单号
 */
public class FinishBean implements Serializable{
	private int _id;
	private String dingdanNumber;//订单号
	private int money;//总共金额
	private String time;//下单时间
	private String zhuangtai;//订单状态
	
	private String name;//名称
	private int img;//图片
	private String singleMoney;//单个金额
	private String singleNumbers;//单个数量
	
	public FinishBean() {
		super();
	}
	
	public FinishBean(String dingdanNumber, int money, String time,
			String zhuangtai) {
		super();
		this.dingdanNumber = dingdanNumber;
		this.money = money;
		this.time = time;
		this.zhuangtai = zhuangtai;
	}

	public FinishBean(String dingdanNumber, int money, String time,
			String zhuangtai, String name, int img, String singleMoney,
			String singleNumbers) {
		super();
		this.dingdanNumber = dingdanNumber;
		this.money = money;
		this.time = time;
		this.zhuangtai = zhuangtai;
		this.name = name;
		this.img = img;
		this.singleMoney = singleMoney;
		this.singleNumbers = singleNumbers;
	}
	public String getDingdanNumber() {
		return dingdanNumber;
	}
	public void setDingdanNumber(String dingdanNumber) {
		this.dingdanNumber = dingdanNumber;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getZhuangtai() {
		return zhuangtai;
	}
	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getImg() {
		return img;
	}
	public void setImg(int img) {
		this.img = img;
	}
	public String getSingleMoney() {
		return singleMoney;
	}
	public void setSingleMoney(String singleMoney) {
		this.singleMoney = singleMoney;
	}
	public String getSingleNumbers() {
		return singleNumbers;
	}
	public void setSingleNumbers(String singleNumbers) {
		this.singleNumbers = singleNumbers;
	}
	
	
	
}
