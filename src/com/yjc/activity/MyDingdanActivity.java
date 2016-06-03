package com.yjc.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.yjc.adapter.MyFinishVpAdapter;
import com.yjc.app.MyApplication;
import com.yjc.base.BaseActivity;
import com.yjc.fragment.FinishedFragment;
import com.yjc.fragment.NoneFinishFragment;
import com.yjc.zhishanglend.R;
/**
 * 我的订单
 * @author yjc Jeff.Yao
 *
 */
public class MyDingdanActivity extends BaseActivity implements OnClickListener {
	private MyDingdanActivity act;
	private ViewPager vp;
	private TextView[] tvs= new TextView[2]; 
	private List<Fragment> fragments;
	private FinishedFragment ff;
	private NoneFinishFragment nff;
	private MyFinishVpAdapter adapter;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.act_mydingdan);
		act = this;
		MyApplication.instance.getActs().add(act);
		initViews();
		initFragment();
		initDatas();
		doViews();
	}
	/**
	 * 数据源初始化
	 */
	private void initDatas() {
		fragments = new ArrayList<Fragment>();
		fragments.add(ff);
		fragments.add(nff);
	}
	/**
	 * Fragment、初始化
	 */
	private void initFragment() {
		// TODO Auto-generated method stub
		ff = new FinishedFragment();
		nff = new NoneFinishFragment();
	}
	/**
	 * 操作
	 */
	private void doViews() {
		adapter = new MyFinishVpAdapter(getSupportFragmentManager(), fragments);
		vp.setAdapter(adapter);
		vp.setOnPageChangeListener(listener);
		for (int i = 0; i < tvs.length; i++) {
			tvs[i].setOnClickListener(this);
		}
	}
	/**
	 * 初始化数据源
	 */
	private void initViews() {
		vp = (ViewPager) findViewById(R.id.mydingdan_vp);
		tvs[0] = (TextView) findViewById(R.id.mydingdan_finish);
		tvs[1] = (TextView) findViewById(R.id.mydingdan_nonefinish);
	}
	@Override
	protected void onDestroy() {
		MyApplication.instance.getActs().remove(act);
		super.onDestroy();
	}
	//------------------------------------------
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		//已经完成的订单
		case R.id.mydingdan_finish:
			vp.setCurrentItem(0);//当前pager为0号
			changeTvColor(0);//切换TextView的颜色
			break;
		//未完成的订单
		case R.id.mydingdan_nonefinish:
			vp.setCurrentItem(1);//当前pager为1号
			changeTvColor(1);//切换TextView的颜色
			break;
		}
	}
	//------------------------------------------------------------------------
	private OnPageChangeListener listener = new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int arg0) {
			changeTvColor(arg0);
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}
	};
	/**
	 * 设置TextView的颜色
	 * @param pos
	 */
	private void changeTvColor(int pos){
		for (int i = 0; i < tvs.length; i++) {
			if(pos == i){
				tvs[i].setBackgroundColor(Color.parseColor("#FF5809"));
			}else{
				tvs[i].setBackgroundColor(Color.parseColor("#e0620d"));
			}
		}
	}
	
	
}
