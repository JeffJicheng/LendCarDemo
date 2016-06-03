package com.yjc.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;

import com.yjc.app.MyApplication;
import com.yjc.base.BaseActivity;
import com.yjc.bean.UserBean;
import com.yjc.dao.UserDao;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;
/**
 * ��¼���� 
 * @author yjc Jeff.Yao
 *
 */
public class LoginActivity extends BaseActivity implements OnClickListener {
	private static LoginActivity act;
	private EditText user_name;
	private EditText user_pwd;
	private Button login;
	private Button register;
	private ImageView iv_head;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		act = this;
		MyApplication.instance.getActs().add(act);
		setContentView(R.layout.act_login);
		initViews();
		doViews();
	}
	/**
	 * ����
	 */
	private void doViews() {
		// TODO Auto-generated method stub
		login.setOnClickListener(this);
		register.setOnClickListener(this);
	}
	/**
	 * ��ʼ���ؼ�
	 */
	private void initViews() {
		// TODO Auto-generated method stub
		user_name = (EditText) findViewById(R.id.login_center_username);
		user_name.setText(getIntent().getStringExtra("username"));
		user_pwd = (EditText) findViewById(R.id.login_center_pwd);
		login = (Button) findViewById(R.id.login_btn);
		register = (Button) findViewById(R.id.login_register);
		iv_head = (ImageView) findViewById(R.id.my_user_head);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MyApplication.instance.getActs().remove(act);
		super.onDestroy();
	}
	
	/**
	 * �ֻ��ϵļ�
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			showSweetDialog("����", "ȷ��Ҫ�˳���", positive, null);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	
	
	
	//---------------------------------------------------------------
	private OnSweetClickListener positive = new OnSweetClickListener() {
		
		@Override
		public void onClick(SweetAlertDialog sweetAlertDialog) {
			// TODO Auto-generated method stub
			act.finish();
		}
	};
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		//��¼
		case R.id.login_btn:
			String name = user_name.getText().toString().trim();
			String pwd = user_pwd.getText().toString().trim();
			if(!judgeIsNull(name, pwd)){
				//�ж��Ƿ�Ϊ�� false
			}else{
				//�洢�û���Ϣ
				UserDao dao = new UserDao(act);
				UserBean user = dao.findNameAndPwd(name, pwd);
				if(user == null || user.equals("")){
					shortToast("�û������������������������!");
				}else{
					getSharedPreferences("user", Context.MODE_PRIVATE).edit()
					.putString("name", name)
					.putString("pwd", pwd)
					.putString("head", user.getHead())
					.putInt("isLogin", 1)
					.putLong("time", System.currentTimeMillis())
					.commit();
					startActivity(new Intent(act, HomeActivity.class));
					UserBean bean = new UserBean(name, pwd, 1, user.getHead());
					dao.update(1, bean);
					shortToast("��¼�ɹ�");
					act.finish();
				}
			}
			break;
		case R.id.login_register:
			startActivity(new Intent(act, RegisterActivity.class));
			break;
		}
	}
}
