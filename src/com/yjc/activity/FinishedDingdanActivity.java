package com.yjc.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.SqlInfo;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;
import com.yjc.adapter.FinishDdAdapter;
import com.yjc.app.MyApplication;
import com.yjc.base.BaseActivity;
import com.yjc.bean.FinishBean;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;
/**
 * 已完成订单
 * @author yjc Jeff.Yao
 *
 */
public class FinishedDingdanActivity extends BaseActivity implements OnItemClickListener, OnClickListener {
	private FinishedDingdanActivity act;
	private ListView lv;
	private ImageView back;
	private FinishDdAdapter adapter;
	private List<FinishBean> datas;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.act_finishdingdan);
		act = this;
		MyApplication.instance.getActs().add(act);
		initViews();
		initDatas();
		doViews();
	}
	/**
	 * 数据源初始化
	 */
	private void initDatas() {
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
	/**
	 * 操作 监听事件声明等
	 */
	private void doViews() {
		adapter = new FinishDdAdapter(act, datas);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		back.setOnClickListener(this);
	}
	/**
	 * 初始化控件
	 */
	private void initViews() {
		lv = (ListView) findViewById(R.id.finishdingdan_lv);
		back = (ImageView) findViewById(R.id.finishdingdan_back);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MyApplication.instance.getActs().remove(act);
		super.onDestroy();
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String dingdanNum = datas.get(arg2).getDingdanNumber();
		Intent intent = new Intent();
		intent.putExtra("dingdanNum", dingdanNum);
		intent.setClass(act, WaitShoppingsActivity.class);
		startActivity(intent);
	}
	//----------------------------------------------
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.finishdingdan_back:
			act.finish();
			break;

		default:
			break;
		}
	}
}
