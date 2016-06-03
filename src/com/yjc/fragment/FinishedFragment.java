package com.yjc.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.yjc.activity.FinishedDingdanActivity;
import com.yjc.activity.MyDingdanActivity;
import com.yjc.activity.WaitShoppingsActivity;
import com.yjc.adapter.FinishDdAdapter;
import com.yjc.adapter.FinishedAdapter;
import com.yjc.base.BaseFragment;
import com.yjc.bean.FinishBean;
import com.yjc.bean.NoFinishBean;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;
/**
 * 已经完成的订单
 * @author yjc Jeff.Yao
 *
 */
public class FinishedFragment extends BaseFragment implements OnItemClickListener {
	private MyDingdanActivity act;
	private ListView lv;
	private List<FinishBean> datas;
	private FinishedAdapter adapter;
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		act = (MyDingdanActivity) activity;
		super.onAttach(activity);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_finished, null);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		initViews();
		initDatas();
		doViews();
		super.onActivityCreated(savedInstanceState);
	}
	
	private void initDatas() {
		// TODO Auto-generated method stub
		try {
			datas = SystemUtils.getDbUtils(act).findAll(Selector.from(FinishBean.class));
			if(datas == null || datas.size() == 0 || datas.equals("")){
				datas = new ArrayList<FinishBean>();
				Collections.reverse(datas);
			}
		} catch (DbException e) {
			e.printStackTrace();
			Log.e("yjc", e.getMessage().toString()+"查询失败");
		}
	}
	private void doViews() {
		adapter = new FinishedAdapter(act, datas);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
	}
	/**
	  初始化数据源
	 */
	private void initViews() {
		lv = (ListView) findviewbyid(R.id.fragment_finished_lv);
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String dingdanNum = datas.get(arg2).getDingdanNumber();
		Intent intent = new Intent();
		intent.putExtra("dingdanNum", dingdanNum);
		intent.setClass(act, WaitShoppingsActivity.class);
		startActivity(intent);
	}
}
