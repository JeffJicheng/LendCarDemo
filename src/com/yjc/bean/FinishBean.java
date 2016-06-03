package com.yjc.bean;

import java.io.Serializable;

/**
 * �������
 * @author yjc Jeff.Yao
 *�����µ�ʱ��Ͷ���״̬  ������
 */
public class FinishBean implements Serializable{
	private int _id;
	private String dingdanNumber;//������
	private int money;//�ܹ����
	private String time;//�µ�ʱ��
	private String zhuangtai;//����״̬
	
	private String name;//����
	private int img;//ͼƬ
	private String singleMoney;//�������
	private String singleNumbers;//��������
	
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
