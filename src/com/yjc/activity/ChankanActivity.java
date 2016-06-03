package com.yjc.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.yjc.adapter.ChanKanAdapter;
import com.yjc.app.MyApplication;
import com.yjc.base.BaseActivity;
import com.yjc.bean.CarTypeSonBean;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;
/**
 * 分类的二级菜单的查看
 * @author yjc Jeff.Yao
 *
 */
public class ChankanActivity extends BaseActivity implements OnItemClickListener, OnClickListener {
	private ChankanActivity act;
	private ListView lv;
	private List<CarTypeSonBean> datas;
	private ChanKanAdapter adapter;
	private ImageView back;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.act_chankan);
		act = this;
		MyApplication.instance.getActs().add(act);
		initViews();
		initDatas();
		doViews();
	}
	/**
	 * 操作
	 */
	private void doViews() {
		// TODO Auto-generated method stub
		adapter = new ChanKanAdapter(act, datas);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		back.setOnClickListener(this);
	}
	/**
	 * 初始化数据源
	 */
	private void initDatas() {
		try {
			datas = SystemUtils.getDbUtils(act).findAll(Selector.from(CarTypeSonBean.class).where("XinghaoName","=",getIntent().getStringExtra("name")));
			if(datas == null || datas.size() == 0 || datas.equals("")){
				datas = new ArrayList<CarTypeSonBean>();
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 初始化控件
	 */
	private void initViews() {
		// TODO Auto-generated method stub
		lv = (ListView) findViewById(R.id.chankan_lv);
		back = (ImageView) findViewById(R.id.chankan_back);
	}
	@Override
	protected void onDestroy() {
		MyApplication.instance.getActs().remove(act);
		super.onDestroy();
	}
	
	//-----------------------------------------------------------
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		String name = datas.get(arg2).getIntroduction();
		Intent intent = new Intent(act, XiangxiActivity.class);
		intent.putExtra("name", name);
		startActivity(intent);
	}
	
	//-----------------------
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.chankan_back:
			act.finish();
			break;
		}
	}
}
