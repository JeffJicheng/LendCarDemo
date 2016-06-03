package com.yjc.app;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.yjc.activity.RegisterActivity;
import com.yjc.fragment.RegisterFragment2;
import com.yjc.zhishanglend.R;

import cn.smssdk.SMSSDK;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class MyApplication extends Application {
	public static MyApplication instance;
	private List<Activity> acts;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == 0x000001){
				//正在计时
				RegisterFragment2.goOnCalTime();
			}else if(msg.what == 0x0000002){
				//计时结束
				RegisterFragment2.tv2Button();
			}else{
				int event = msg.arg1;
				int result = msg.arg2;
				Object data = msg.obj;
			   if (result == SMSSDK.RESULT_COMPLETE) {
					//回调完成
				   /**
                    * 有三种情况：
                    * 1. 提交验证码成功, Toast验证成功，切换Frgament设置密码
                    * 2. 正在获取验证码,Toast正在获取验证码
                    * 3. 获取验证码失败
                    * */
					if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
						//提交验证码成功
						RegisterFragment2.replaceFZhuce();
						Toast.makeText(getApplicationContext(), "验证成功", Toast.LENGTH_SHORT).show();
					}else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
						//获取验证码成功
						Toast.makeText(instance, "正在获取验证码", Toast.LENGTH_SHORT).show();
					}else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
	                //返回支持发送验证码的国家列表
	                }else{                                                                 
	                	((Throwable)data).printStackTrace(); 
	                }
				} else{
                  /**在这里解析服务器返回的错误代码，根据错误代码到Mob官方上查找相应的错误*/
					Toast.makeText(getApplicationContext(), "验证码错误", Toast.LENGTH_SHORT).show();
                  try {
                      Throwable throwable = (Throwable) data;
                      throwable.printStackTrace();
                      JSONObject object = new JSONObject(throwable.getMessage());
                      String des = object.optString("detail");//错误描述
                      int status = object.optInt("status");//错误代码
                      Log.d("yjc",status+"");
                      if (status > 0 && !TextUtils.isEmpty(des)) {
                          Log.d("yjc", des + " " + status);
                          return;
                      }
                  	} catch (Exception e) {
                      //do something
                      e.printStackTrace();
                 	}
                  }
              }
		}
	};
	private int head;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		instance = this;
		acts = new ArrayList<Activity>();
		initSMSSDK();
		super.onCreate();
	}
	/**
	 * 短信验证码初始化
	 */
	private void initSMSSDK() {
		// TODO Auto-generated method stub
		SMSSDK.initSDK(this, "11b789d4b9b2f", "200e7cd6da371bc2941c0f7cc2e67e64");
	}
	public List<Activity> getActs() {
		return acts;
	}
	public void setActs(List<Activity> acts) {
		this.acts = acts;
	}
	public Handler getHandler() {
		return handler;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
}
