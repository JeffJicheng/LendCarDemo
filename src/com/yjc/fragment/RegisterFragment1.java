package com.yjc.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;

import cn.smssdk.SMSSDK;

import com.yjc.activity.HomeActivity;
import com.yjc.activity.RegisterActivity;
import com.yjc.base.BaseFragment;
import com.yjc.zhishanglend.R;
/**
 * �ֻ�ע�����1
 * @author yjc Jeff.Yao
 *
 */
public class RegisterFragment1 extends BaseFragment implements OnClickListener, OnCheckedChangeListener {
	private RegisterActivity act;
	/*���ڵ� �� �ֻ����� */
	private EditText city,phoneNumer;
	/*ѡ�п�*/
	private CheckBox checkBox;
	/*������֤�밴ť*/
	private Button sendCheck;
	
	private boolean isSelected;
	
	private static String phone;
	private String city_num;
	
	private RegisterFragment2 rf2;
	
	
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
		return inflater.inflate(R.layout.fragment_register1, null);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		initViews();
		initFragments();
		doViews();
		super.onActivityCreated(savedInstanceState);
	}
	
	/**
	 * Frgament��ʼ��
	 */
	private void initFragments() {
		// TODO Auto-generated method stub
		rf2 = new RegisterFragment2();
	}
	/**
	 * �ؼ����� �����¼�
	 */
	private void doViews() {
		sendCheck.setOnClickListener(this);
		checkBox.setOnCheckedChangeListener(this);
	}
	/**
	 * ��ʼ���ؼ�
	 */
	private void initViews() {
		// TODO Auto-generated method stub
		city = (EditText) findviewbyid(R.id.register_city);
		phoneNumer = (EditText) findviewbyid(R.id.register_phonenumer);
		checkBox = (CheckBox) findviewbyid(R.id.register_checkbox);
		sendCheck = (Button) findviewbyid(R.id.register_sendcheck);
	}
	//-----------------------------------------------------------------------
	/**
	 * �������ļ���
	 */
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
			//������֤��
		case R.id.register_sendcheck:
			city_num = city.getText().toString().trim();
			phone = phoneNumer.getText().toString().trim();
			setPhone(phone);
			if(phone == null || phone.equals("")){
				shortToast(act,"�ֻ��Ų���Ϊ��");
				return;
			}
			if(isSelected){
				shortToast(act,"���Ķ���ͬ�⡶�������");
				return;
			}
			sendCheck();//������֤��
			act.getSupportFragmentManager().beginTransaction().replace(R.id.register_framelayout, rf2).commit();
			break;
		default:
			break;
		}
	}
	/**
	 * ���Ͷ�����֤��
	 */
	private void sendCheck() {
		// TODO Auto-generated method stub
		/*�����ȡ������֤�룬�ڼ����з���*/
		SMSSDK.getVerificationCode("+86", phone);
	}
	/**
	 * ͬ���������ѡ�п����
	 */
	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		isSelected = !isSelected;
	}
	
	
	public static String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
