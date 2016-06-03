package com.yjc.fragment;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.yjc.activity.MyDingdanActivity;
import com.yjc.adapter.FinishDdAdapter;
import com.yjc.adapter.NoneFinishedAdapter;
import com.yjc.base.BaseFragment;
import com.yjc.bean.FinishBean;
import com.yjc.bean.NoFinishBean;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;
/**
 * 未完成订单fragment
 * @author yjc Jeff.Yao
 *
 */

public class NoneFinishFragment extends BaseFragment {
	private MyDingdanActivity act;
	private ListView lv;
	private List<NoFinishBean> datas;
	private NoneFinishedAdapter adapter;
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
	private void doViews() {
		// TODO Auto-generated method stub
		adapter = new NoneFinishedAdapter(act, datas);
		lv.setAdapter(adapter);
	}
	private void initDatas() {
		try {
			datas = SystemUtils.getDbUtils(act).findAll(Selector.from(NoFinishBean.class));
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("yjc", "查找错误"+e.getMessage());
		}
	}
	private void initViews() {
		// TODO Auto-generated method stub
		lv = (ListView) findviewbyid(R.id.fragment_finished_lv);
	}
}
