package com.yjc.activity;

import java.util.List;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.yjc.adapter.WSAdapter;
import com.yjc.app.MyApplication;
import com.yjc.base.BaseActivity;
import com.yjc.bean.FinishBean;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
/**
 * 待收货
 * @author yjc Jeff.Yao
 *
 */
public class WaitShoppingsActivity extends BaseActivity implements OnClickListener {
	private WaitShoppingsActivity act;
	private ImageView back;
	private ListView lv;
	private List<FinishBean> datas;
	private WSAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_waitshopping);
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
		String dingdanNumber = getIntent().getStringExtra("dingdanNum");
		try {
			datas = SystemUtils.getDbUtils(act).findAll(Selector.from(FinishBean.class).where("dingdanNumber", "=", dingdanNumber));
		} catch (DbException e) {
			e.printStackTrace();
			Log.e("yjc", "查询失败:"+e.getMessage().toString());
		}
	}
	/**
	 * 操作
	 */
	private void doViews() {
		adapter = new WSAdapter(act, datas);
		lv.setAdapter(adapter);
		back.setOnClickListener(this);
	}
	/**
	 * 初始化控件
	 */
	private void initViews() {
		// TODO Auto-generated method stub
		lv = (ListView) findViewById(R.id.waitshopping_lv);
		back = (ImageView) findViewById(R.id.waitshopping_back);
	}
	@Override
	protected void onDestroy() {
		MyApplication.instance.getActs().remove(act);
		super.onDestroy();
	}
//-----------------------------------------------
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.waitshopping_back:
			act.finish();
			break;

		default:
			break;
		}
	}
}
