package com.yjc.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.smssdk.SMSSDK;

import com.yjc.activity.RegisterActivity;
import com.yjc.app.MyApplication;
import com.yjc.base.BaseFragment;
import com.yjc.zhishanglend.R;
/**
 * �ֻ�ע�����2
 * @author yjc Jeff.Yao
 *
 */
public class RegisterFragment2 extends BaseFragment implements OnClickListener {
	private static RegisterActivity act;
	private EditText yanzhengma;//��֤��
	private static TextView tishi_yanzhengma;
	private static Button resend;
	private Button tijiao;
	
	private final static int RETRY_INTERVAL = 5;//ʱ��
	private static int time = RETRY_INTERVAL;
	
	private static RegisterFragment3 rf3;
	
	
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
		return inflater.inflate(R.layout.fragment_register2, null);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		initViews();
		initFragments();
		doViews();
		super.onActivityCreated(savedInstanceState);
	}
	/**
	 * 
	 */
	private void initFragments() {
		// TODO Auto-generated method stub
		rf3 = new RegisterFragment3();
	}
	/**
	 * ����
	 */
	private void doViews() {
		// TODO Auto-generated method stub
		resend.setOnClickListener(this);
		tijiao.setOnClickListener(this);
		countDown();
	}
	/**
	 * �ؼ���ʼ��
	 */
	private void initViews() {
		// TODO Auto-generated method stub
		yanzhengma = (EditText) findviewbyid(R.id.register_yanzhengmaNum);
		tishi_yanzhengma = (TextView) findviewbyid(R.id.register_tv_tishiyanzhengma);
		resend = (Button) findviewbyid(R.id.register_resendchecknum);
		tijiao = (Button) findviewbyid(R.id.register_tijiao);
	}
	/**
	 * ��ʱ��ʾ�ĵ���ʱUI�л�
	 */
	public static void goOnCalTime(){
		StringBuffer sbuffer = new StringBuffer();
		String text = sbuffer.append(time).append("���������»����֤��").toString();
		tishi_yanzhengma.setText(text);
	}
	/**
	 * TextView -> Button ���·�����֤�밴������
	 */
	public static void tv2Button(){
		tishi_yanzhengma.setVisibility(View.INVISIBLE);
		resend.setVisibility(View.VISIBLE);
		
	}
	/**
	 * Button -> TextView ��ʱ��ʾ����
	 */
	public static void button2Tv(){
		tishi_yanzhengma.setVisibility(View.VISIBLE);
		resend.setVisibility(View.INVISIBLE);
	}
	
	//=================================================
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		//���·���
		case R.id.register_resendchecknum:
		 	SMSSDK.getVerificationCode("+86",RegisterFragment1.getPhone());
		 	button2Tv();
		 	countDown();
			break;
		//�ύ��֤��
		case R.id.register_tijiao:
			String yzm = yanzhengma.getText().toString().trim();
			if(yzm == null || yzm.equals("")){
				shortToast(act, "��֤�벻��Ϊ��");
				return;
			}
			SMSSDK.submitVerificationCode("+86", RegisterFragment1.getPhone(), yanzhengma.getText().toString().trim());
			break;
		}
	}
	/**
	 * �л���ע��ĵ���������
	 */
	
	public static void replaceFZhuce(){
		act.getSupportFragmentManager().beginTransaction().replace(R.id.register_framelayout, rf3).commit();
	} 
	/**
	 * ��ʱ
	 * */
	private void countDown(){
		new Thread(){
			public void run() {
				while (time-- > 0) {
					MyApplication.instance.getHandler().sendEmptyMessage(0x000001);//���ڼ�ʱ
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.e("yjc", e.getMessage().toString());
					}
				}
				time = RETRY_INTERVAL;
				MyApplication.instance.getHandler().sendEmptyMessage(0x0000002);//��ʱ����
			}
		}.start();
	}
}
