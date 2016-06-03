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
 * �ֻ�ע�����4 ���ע��
 * @author yjc Jeff.Yao
 *
 */
public class RegisterFragment4 extends BaseFragment implements OnClickListener {
	private RegisterActivity act;
	/*�û���TextView*/
	private TextView phoneNum;
	/*��¼��ť*/
	private Button login;

	/*�����û���*/
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
	 * ����
	 */
	private void doViews() {
		// TODO Auto-generated method stub
		login.setOnClickListener(this);
	}
	/**
	 *  �ؼ���ʼ��
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
