package com.yjc.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yjc.activity.LoginActivity;
import com.yjc.activity.RegisterActivity;
import com.yjc.base.BaseFragment;
import com.yjc.zhishanglend.R;
/**
 * 手机注册界面4 完成注册
 * @author yjc Jeff.Yao
 *
 */
public class RegisterFragment4 extends BaseFragment implements OnClickListener {
	private RegisterActivity act;
	/*用户名TextView*/
	private TextView phoneNum;
	/*登录按钮*/
	private Button login;

	/*变量用户名*/
	private String phone;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		act = (RegisterActivity) activity;
		super.onAttach(activity);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_register4, null);
	}	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		initViews();
		doViews();
		super.onActivityCreated(savedInstanceState);
	}
	/**
	 * 操作
	 */
	private void doViews() {
		// TODO Auto-generated method stub
		login.setOnClickListener(this);
	}
	/**
	 *  控件初始化
	 */
	private void initViews() {
		// TODO Auto-generated method stub
		phoneNum = (TextView) findviewbyid(R.id.register_yonghuming);
		phone = RegisterFragment1.getPhone();
		phoneNum.setText(phone);
		login = (Button) findviewbyid(R.id.register_login);
	}
	
	//-------------------------------------------
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(act, LoginActivity.class);
		intent.putExtra("username", phone);
		act.startActivity(intent);
		act.finish();
	}
}
