package com.yjc.activity;

import com.yjc.app.MyApplication;
import com.yjc.zhishanglend.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
/**
 * 应用启动动画
 * @author yjc Jeff.Yao
 *
 */
public class SplashActivity extends Activity implements AnimationListener {
	private AnimationSet animation;
	private ImageView splash;
	private SplashActivity act;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_splash);
		act = this;
		MyApplication.instance.getActs().add(act);
		initViews();
		doViews();
	}
	private void doViews() {
		// TODO Auto-generated method stub
		splash.setAnimation(animation);
		animation.setAnimationListener(this);
	}
	private void initViews() {
		// TODO Auto-generated method stub
		splash = (ImageView) findViewById(R.id.act_splash);
		animation = (AnimationSet) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.set_splash);
		
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MyApplication.instance.getActs().remove(act);
		super.onDestroy();
	}
	//-----------------------------------
	@Override
	public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this, HomeActivity.class));
		this.finish();
	}
	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
