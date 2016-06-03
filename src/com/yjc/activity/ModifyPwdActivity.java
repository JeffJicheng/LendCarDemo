package com.yjc.activity;

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
import com.yjc.zhishanglend.R;

/**
 * 修改密码
 * @author yjc Jeff.Yao
 *
 */
public class ModifyPwdActivity extends BaseActivity implements OnClickListener{
	private ModifyPwdActivity act;
	private EditText oldpwd,newpwd,repwd;
	private Button yes;
	private ImageView back;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.act_modifypwd);
		act = this;
		MyApplication.instance.getActs().add(act);
		initViews();
		doViews();
	}
	/**
	 * 操作
	 */
	private void doViews() {
		yes.setOnClickListener(this);
		back.setOnClickListener(this);
	}
	/**
	 * 初始化控件
	 */
	private void initViews() {
		oldpwd = (EditText) findViewById(R.id.modifypwd_oldpwd);
		newpwd = (EditText) findViewById(R.id.modifypwd_newpwd);
		repwd = (EditText) findViewById(R.id.modifypwd_repwd);
		yes = (Button) findViewById(R.id.modifypwd_yes);
		back = (ImageView) findViewById(R.id.modifypwd_back);
	}
	@Override
	protected void onDestroy() {
		MyApplication.instance.getActs().remove(act);
		super.onDestroy();
	}
	//------------------------------------------------------------
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.modifypwd_yes:
			String new_password = newpwd.getText().toString().trim();
			String re_password = repwd.getText().toString().trim();
			String old_password = oldpwd.getText().toString().trim();
			if(old_password == null || old_password.equals("")){
				shortToast("旧密码不能为空");
				return;
			}
			if(new_password == null || re_password == null || new_password.equals("") || re_password.equals("")){
				shortToast("密码不能为空");
				return;
			}
			if(!judgeIsSame(new_password, re_password)){
				shortToast("两次输入的新密码不一样,请重新输入");
				return;
			}
			
			String name = getIntent().getStringExtra("name").toString().trim();
			String head = getIntent().getStringExtra("head").toString().trim();
			if(HomeActivity.modifyPwd(name,old_password, new_password, head)){
				shortToast("修改密码成功");
				act.showSweetDialog("提示", "密码修改成功\r\n需要重新登录", positive);
			}else{
				act.shortToast("旧密码错误，请确认无误后再修改");
			}
			break;
		case R.id.modifypwd_back:
			act.finish();
			break;
		}
	}
	/**
	 * 判断新密码和重新输入的新密码是否一致
	 * @param new_password
	 * @param re_password
	 * @return
	 */
	private boolean judgeIsSame(String new_password,String re_password){
		if((new_password.length() == re_password.length()) && (new_password.equals(re_password))){
			return true;
		}
		return false;
	}
	//----------------------------------------------------
	private OnSweetClickListener positive = new OnSweetClickListener() {
		
		@Override
		public void onClick(SweetAlertDialog sweetAlertDialog) {
			act.startActivity(new Intent(act, LoginActivity.class));
			for (int i = 0; i < MyApplication.instance.getActs().size(); i++) {
				MyApplication.instance.getActs().get(i).finish();
			}
		}
	};
}
