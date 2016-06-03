package com.yjc.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.yjc.activity.HomeActivity;
import com.yjc.activity.SearchActivity;
import com.yjc.activity.TypeActivity;
import com.yjc.adapter.HomeVpAdapter;
import com.yjc.base.BaseFragment;
import com.yjc.bean.CarBrandBean;
import com.yjc.config.Config;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;
/**
 * 主页面的Fragment
 * @author yjc Jeff.Yao
 *
 */
public class HomeFragment extends BaseFragment implements OnClickListener{
	private HomeActivity act;
	private HomeVpAdapter vp_adapter;
	private ViewPager home_vp;
	private List<View> pagers;
	private ImageView home_more;
	private ImageView home_search;
	
	private int currentItem;//当前的ViewPager
	
	private ScheduledExecutorService scheduledExecutorService;//自动计时切换ViewPager的类  
	
	/*GridView适配器*/
	private SimpleAdapter adapter;
	private GridView home_gv;
	private List<HashMap<String, Object>> gv_datas; 
	
	private TypeFragment tf;
	
	/**
	 * 切换当前显示的图片
	 */
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == 1){
				int item = (Integer) msg.obj;
				home_vp.setCurrentItem(item);
				
			}
		}
	};
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示  
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 5, TimeUnit.SECONDS);  
		super.onStart();
	}
	// 当Activity不可见的时候停止切换  
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		scheduledExecutorService.shutdown();
		super.onStop();
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		act = (HomeActivity) activity;
		super.onAttach(activity);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_home, null);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		initViews();
		initFragments();
		initDatas();
		doViews();
		super.onActivityCreated(savedInstanceState);
	}
	/**
	 * Fragment初始化
	 */
	private void initFragments() {
		tf = new TypeFragment();
	}
	/**
	 * 数据源初始化
	 */
	private void initDatas() {
		// TODO Auto-generated method stub
		// ViewPager的数据源
		pagers = new ArrayList<View>();
		View v1 = act.getLayoutInflater().inflate(R.layout.item_home_vp1, null);
		View v2 = act.getLayoutInflater().inflate(R.layout.item_home_vp2, null);
		View v3 = act.getLayoutInflater().inflate(R.layout.item_home_vp3, null);
		pagers.add(v1);
		pagers.add(v2);
		pagers.add(v3);
		
		//GridView的数据源
		gv_datas = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < Config.home_carName.length; i++) {
			HashMap<String, Object> maps = new HashMap<String, Object>();
			CarBrandBean bean = new CarBrandBean(i, Config.home_carType[i], Config.home_carName[i]);
			maps.put("iv", Config.home_carType[i]);
			maps.put("tv", Config.home_carName[i]);
			gv_datas.add(maps);
		}
		
	}
	/**
	 * 操作
	 */
	private void doViews() {
		//ViewPager的adapter
		vp_adapter = new HomeVpAdapter(pagers);
		home_vp.setAdapter(vp_adapter);
		home_vp.setOnPageChangeListener(listener);
		
		//GridView的adapter
		adapter = new SimpleAdapter(act, gv_datas, R.layout.item_home_gv, new String[]{"iv","tv"} , new int[]{R.id.item_home_gv_iv,R.id.item_home_gv_tv});
		home_gv.setAdapter(adapter);
		home_gv.setOnItemClickListener(listener1);
		home_more.setOnClickListener(act);
		home_search.setOnClickListener(this);
	}
	/**
	 * 初始化控件
	 */
	private void initViews() {
		// TODO Auto-generated method stub
		home_vp = (ViewPager) findviewbyid(R.id.home_vp);
		home_gv = (GridView) findviewbyid(R.id.home_gv);
		home_more = (ImageView) findviewbyid(R.id.home_more);
		home_search = (ImageView) findviewbyid(R.id.home_search);
	}
	
	//---------------------------------------------
	private OnPageChangeListener listener = new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			currentItem = arg0;
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	/**
	 * 执行ViewPager切换任务
	 * @author Administrator
	 *
	 */
	private class ScrollTask implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized (home_vp) {
				currentItem = (currentItem + 1) % pagers.size();
				Message msg = Message.obtain();
				msg.obj = currentItem;
				msg.what = 1;
				handler.sendMessage(msg);
			}
		}
	}
	
	//----------------------------GridView点击事件--------------------------------------
	private OnItemClickListener listener1 = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent = new Intent(act, TypeActivity.class);
			intent.putExtra("position", arg2);
//			shortToast(act, arg2+"");
			startActivity(intent);
		}
	};
	//-----------------------------------
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		//搜索
		case R.id.home_search:
			startActivity(new Intent(act, SearchActivity.class));
			break;

		default:
			break;
		}
	}
	
	
}
