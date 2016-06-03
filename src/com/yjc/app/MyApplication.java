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
				//���ڼ�ʱ
				RegisterFragment2.goOnCalTime();
			}else if(msg.what == 0x0000002){
				//��ʱ����
				RegisterFragment2.tv2Button();
			}else{
				int event = msg.arg1;
				int result = msg.arg2;
				Object data = msg.obj;
			   if (result == SMSSDK.RESULT_COMPLETE) {
					//�ص����
				   /**
                    * �����������
                    * 1. �ύ��֤��ɹ�, Toast��֤�ɹ����л�Frgament��������
                    * 2. ���ڻ�ȡ��֤��,Toast���ڻ�ȡ��֤��
                    * 3. ��ȡ��֤��ʧ��
                    * */
					if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
						//�ύ��֤��ɹ�
						RegisterFragment2.replaceFZhuce();
						Toast.makeText(getApplicationContext(), "��֤�ɹ�", Toast.LENGTH_SHORT).show();
					}else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
						//��ȡ��֤��ɹ�
						Toast.makeText(instance, "���ڻ�ȡ��֤��", Toast.LENGTH_SHORT).show();
					}else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
	                //����֧�ַ�����֤��Ĺ����б�
	                }else{                                                                 
	                	((Throwable)data).printStackTrace(); 
	                }
				} else{
                  /**������������������صĴ�����룬���ݴ�����뵽Mob�ٷ��ϲ�����Ӧ�Ĵ���*/
					Toast.makeText(getApplicationContext(), "��֤�����", Toast.LENGTH_SHORT).show();
                  try {
                      Throwable throwable = (Throwable) data;
                      throwable.printStackTrace();
                      JSONObject object = new JSONObject(throwable.getMessage());
                      String des = object.optString("detail");//��������
                      int status = object.optInt("status");//�������
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
	 * ������֤���ʼ��
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
