package com.yjc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yjc.app.MyApplication;
import com.yjc.base.BaseActivity;
import com.yjc.zhishanglend.R;

/**
 * 账户管理
 * @author yjc Jeff.Yao
 *
 */
public class ZManagerActivity extends BaseActivity implements OnClickListener{
	public static ZManagerActivity act;
	private LinearLayout[] ll = new LinearLayout[2];
	private ImageView back;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.act_zmanager);
		act = this;
		MyApplication.instance.getActs().add(act);
		initViews();
		doViews();
	}
	/**
	 * 操作
	 */
	private void doViews() {
		for (int i = 0; i < ll.length; i++) {
			ll[i].setOnClickListener(this);
		}
		back.setOnClickListener(this);
	}
	/**
	 * 初始化数据源
	 */
	private void initViews() {
		ll[0] = (LinearLayout) findViewById(R.id.zmanager_ll1);
		ll[1] = (LinearLayout) findViewById(R.id.zmanager_ll2);
		back = (ImageView) findViewById(R.id.zmanager_back);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MyApplication.instance.getActs().remove(act);
		super.onDestroy();
	}
	//------------------------------------------
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		//修改密码
		case R.id.zmanager_ll1:
			Intent intent = new Intent();
			intent.setClass(act, ModifyPwdActivity.class);
			intent.putExtra("name", getIntent().getStringExtra("name"));
			intent.putExtra("head", getIntent().getStringExtra("head"));
			startActivity(intent);
			act.finish();
			break;
		//切换用户
		case R.id.zmanager_ll2:
			startActivity(new Intent(act, LoginActivity.class));
			for (int i = 0; i < MyApplication.instance.getActs().size(); i++) {
				MyApplication.instance.getActs().get(i).finish();
			}
			break;
			//back键
		case R.id.zmanager_back:
			act.finish();
			break;
		}
	}
}
