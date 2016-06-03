package com.yjc.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.yjc.app.MyApplication;
import com.yjc.base.BaseActivity;
import com.yjc.fragment.RegisterFragment1;
import com.yjc.fragment.RegisterFragment3;
import com.yjc.fragment.RegisterFragment2;
import com.yjc.zhishanglend.R;
/**
 * 注册
 * @author yjc 
 * @since 15/4/2016
 *
 */
public class RegisterActivity extends BaseActivity implements OnClickListener {
	private RegisterActivity act;
	/*back键*/
	private ImageView back;
	private RegisterFragment1 rf1; 
	private RegisterFragment2 rf2;
	private EventHandler eh = null;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.act_register);
		act = this;
		MyApplication.instance.getActs().add(act);
		initViews();
		initFragments();
		initSMSSDK();//初始化短信注册
		doViews();
	}
	
	private void initFragments() {
		// TODO Auto-generated method stub
		rf1 = new RegisterFragment1();
		rf2 = new RegisterFragment2();
		act.getSupportFragmentManager().beginTransaction().replace(R.id.register_framelayout, rf1).commit();
	}
	/**
	 * 初始化短信注册
	 */
	private void initSMSSDK() {
		// TODO Auto-generated method stub
		eh=new EventHandler(){
			
			@Override
			public void afterEvent(int event, int result, Object data) {
				Message msg = Message.obtain();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				MyApplication.instance.getHandler().sendMessage(msg);
			}
			@Override
			public void onRegister() {
				// TODO Auto-generated method stub
				super.onRegister();
			}
			@Override
			public void onUnregister() {
				// TODO Auto-generated method stub
				super.onUnregister();
			}
			@Override
			public void beforeEvent(int arg0, Object arg1) {
				// TODO Auto-generated method stub
				super.beforeEvent(arg0, arg1);
			}	
		}; 
		SMSSDK.registerEventHandler(eh); //注册短信回调
	}

	/**
	 * 控件操作 监听事件
	 */
	private void doViews() {
		back.setOnClickListener(this);
	}
	/**
	 * 初始化控件
	 */
	private void initViews() {
		// TODO Auto-generated method stub
		back = (ImageView) findViewById(R.id.register_back);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		SMSSDK.unregisterEventHandler(eh);
		MyApplication.instance.getActs().remove(act);
		super.onDestroy();
	}

	//-----------------------------------------------------------------------
	/**
	 * 各按键的监听
	 */
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
			//返回
		case R.id.register_back:
			act.finish();
			break;
		default:
			break;
		}
	}
	
	
	
	
	
}
