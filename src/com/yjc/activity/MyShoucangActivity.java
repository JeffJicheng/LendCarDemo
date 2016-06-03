package com.yjc.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.yjc.adapter.MyShoucangAdapter;
import com.yjc.app.MyApplication;
import com.yjc.base.BaseActivity;
import com.yjc.bean.MyShoucangBean;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;

/**
 *  我的收藏
 * @author yjc 
 * @since 28-4-2016
 *
 */
public class MyShoucangActivity extends BaseActivity implements OnItemClickListener, OnClickListener {
	private MyShoucangActivity act;
	private ListView lv;
	private ImageView back;
	private List<MyShoucangBean> datas;
	private MyShoucangAdapter adapter;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.act_myshoucang);
		act = this;
		MyApplication.instance.getActs().add(act);
		initViews();
		initDatas();
		doViews();
		
	}
	/**
	 * 初始化数据源
	 */
	private void initDatas() {
		try {
			datas = SystemUtils.getDbUtils(act).findAll(Selector.from(MyShoucangBean.class));
			if(datas == null || datas.size() == 0 || datas.equals("")){
				datas = new ArrayList<MyShoucangBean>();
			}
		} catch (DbException e) {
			e.printStackTrace();
			Log.e("yjc", "查询出错"+e.getMessage().toString());
		}
	}
	/**
	 * 操作
	 */
	private void doViews() {
		adapter = new MyShoucangAdapter(act, datas);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		back.setOnClickListener(this);
	}
	/**
	 * 初始化控件
	 */
	private void initViews() {
		lv = (ListView) findViewById(R.id.myshoucang_lv);
		back = (ImageView) findViewById(R.id.myshoucang_back);
	}

	@Override
	protected void onDestroy() {
		MyApplication.instance.getActs().remove(act);
		super.onDestroy();
	}
	//-------------------------------------------------------------------------
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		 String name = datas.get(arg2).getName();
		 Intent intent = new Intent();
		 intent.putExtra("name", name);
		 intent.setClass(act, XiangxiActivity.class);
		 startActivity(intent);
	}
	//-----------------------------------------
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.myshoucang_back:
			act.finish();
			break;

		default:
			break;
		}
	}
}
