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
 * �޸�����
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
	 * ����
	 */
	private void doViews() {
		yes.setOnClickListener(this);
		back.setOnClickListener(this);
	}
	/**
	 * ��ʼ���ؼ�
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
				shortToast("�����벻��Ϊ��");
				return;
			}
			if(new_password == null || re_password == null || new_password.equals("") || re_password.equals("")){
				shortToast("���벻��Ϊ��");
				return;
			}
			if(!judgeIsSame(new_password, re_password)){
				shortToast("��������������벻һ��,����������");
				return;
			}
			
			String name = getIntent().getStringExtra("name").toString().trim();
			String head = getIntent().getStringExtra("head").toString().trim();
			if(HomeActivity.modifyPwd(name,old_password, new_password, head)){
				shortToast("�޸�����ɹ�");
				act.showSweetDialog("��ʾ", "�����޸ĳɹ�\r\n��Ҫ���µ�¼", positive);
			}else{
				act.shortToast("�����������ȷ����������޸�");
			}
			break;
		case R.id.modifypwd_back:
			act.finish();
			break;
		}
	}
	/**
	 * �ж������������������������Ƿ�һ��
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
