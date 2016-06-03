package com.yjc.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yjc.activity.LoginActivity;
import com.yjc.activity.RegisterActivity;
import com.yjc.base.BaseFragment;
import com.yjc.bean.UserBean;
import com.yjc.dao.UserDao;
import com.yjc.zhishanglend.R;
/**
 * 手机注册界面3
 * @author yjc Jeff.Yao
 *
 */
public class RegisterFragment3 extends BaseFragment implements OnClickListener {
	private RegisterActivity act;
	private EditText password,repassword;
	private Button finish_register;
	
	private RegisterFragment4 rf4;
	
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
		return inflater.inflate(R.layout.fragment_register3, null);
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
	 * Fragment初始化
	 */
	private void initFragments() {
		// TODO Auto-generated method stub
		rf4 = new RegisterFragment4();
	}
	/**
	 * 操作
	 */
	private void doViews() {
		// TODO Auto-generated method stub
		finish_register.setOnClickListener(this);
	}
	/**
	 * 控件初始化
	 */
	private void initViews() {
		// TODO Auto-generated method stub
		password = (EditText) findviewbyid(R.id.register_pwd);
		repassword = (EditText) findviewbyid(R.id.register_repwd);
		finish_register = (Button) findviewbyid(R.id.register_finish);
	}
	//================================================
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String pwd = password.getText().toString().trim();
		String re_pwd = repassword.getText().toString().trim();
		if(!pwd.endsWith(re_pwd)){
			shortToast(act, "两次密码不一样,请重新输入");
			return;
		}
		//若确认密码ok,则进行下一步  完成注册
		String phone = RegisterFragment1.getPhone();
		UserBean user = new UserBean(phone, pwd, 0, String.valueOf(R.drawable.user_image));
		UserDao dao = new UserDao(act);
		dao.insert(user);
		shortToast(act, "注册成功");
		act.getSupportFragmentManager().beginTransaction().replace(R.id.register_framelayout, rf4).commit();
	}
}
