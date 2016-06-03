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
 * �ҵĶ���
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
	 * ����Դ��ʼ��
	 */
	private void initDatas() {
		fragments = new ArrayList<Fragment>();
		fragments.add(ff);
		fragments.add(nff);
	}
	/**
	 * Fragment����ʼ��
	 */
	private void initFragment() {
		// TODO Auto-generated method stub
		ff = new FinishedFragment();
		nff = new NoneFinishFragment();
	}
	/**
	 * ����
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
	 * ��ʼ������Դ
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
		//�Ѿ���ɵĶ���
		case R.id.mydingdan_finish:
			vp.setCurrentItem(0);//��ǰpagerΪ0��
			changeTvColor(0);//�л�TextView����ɫ
			break;
		//δ��ɵĶ���
		case R.id.mydingdan_nonefinish:
			vp.setCurrentItem(1);//��ǰpagerΪ1��
			changeTvColor(1);//�л�TextView����ɫ
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
	 * ����TextView����ɫ
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
