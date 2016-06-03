package com.yjc.bean;

import java.io.Serializable;
/**
 * 用户User bean
 * @author yjc Jeff.Yao
 *
 */
public class UserBean implements Serializable{
	private int _id;
	private String user_name;
	private String user_pwd;
	private int isLogin;//1 为已经登录 0 为还未登录  用于判断用户是否是第一次登录
	private String head;
	public UserBean(int _id, String user_name, String user_pwd, int islogin,String head) {
		super();
		this._id = _id;
		this.user_name = user_name;
		this.user_pwd = user_pwd;
		this.isLogin = islogin;
		this.head = head;
	}
	
	public UserBean(String user_name, String user_pwd, int isLogin,String head) {
		super();
		this.user_name = user_name;
		this.user_pwd = user_pwd;
		this.isLogin = isLogin;
		this.head = head;
	}
	
	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public int isLogin() {
		return isLogin;
	}
	public void setLogin(int isLogin) {
		this.isLogin = isLogin;
	}

	@Override
	public String toString() {
		return "UserBean [_id=" + _id + ", user_name=" + user_name
				+ ", user_pwd=" + user_pwd + ", isLogin=" + isLogin + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBean other = (UserBean) obj;
		if (_id != other._id)
			return false;
		if (head != other.head)
			return false;
		if (isLogin != other.isLogin)
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		if (user_pwd == null) {
			if (other.user_pwd != null)
				return false;
		} else if (!user_pwd.equals(other.user_pwd))
			return false;
		return true;
	}

	
}
