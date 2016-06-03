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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.yjc.adapter.SearchGvAdapter;
import com.yjc.app.MyApplication;
import com.yjc.base.BaseActivity;
import com.yjc.bean.CarBrandBean;
import com.yjc.config.Config;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;
/**
 * 查找功能
 * @author yjc Jeff.Yao
 *
 */
public class SearchActivity extends BaseActivity implements OnClickListener, OnItemClickListener {
	private SearchActivity act;
	private ImageView search;
	private GridView gv;
	private EditText et;
	private ProgressBar pb;
	private SearchGvAdapter adapter;
	private ImageView back;
	
	private List<CarBrandBean> datas = new ArrayList<CarBrandBean>();
	private List<CarBrandBean> ss = new ArrayList<CarBrandBean>();
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		act = this;
		MyApplication.instance.getActs().add(act);
		setContentView(R.layout.act_search);
		initViews();
		initDatas();
		doViews();
	}
	/**
	 * 操作
	 */
	private void doViews() {
		search.setOnClickListener(this);
		gv.setOnItemClickListener(this);
		back.setOnClickListener(this);
	}
	/**
	 * 初始化数据源
	 */
	private void initDatas() {
		String content = et.getText().toString().trim();//编辑框的内容 搜索的内容
		for (int i = 0; i < Config.home_carName.length; i++) {
			CarBrandBean bean = new CarBrandBean(i, Config.home_carType[i], Config.home_carName[i]);
			ss.add(bean);
		}
		for (int i = 0; i < ss.size(); i++) {
			String brandName = ss.get(i).getCarsName().toString().trim();
			if(brandName.equals(content)){
				CarBrandBean bean = new CarBrandBean(i, ss.get(i).getImg(), ss.get(i).getCarsName());
				datas.add(bean);
			}
		}
		
	}
	/**
	 * 初始化控件
	 */
	private void initViews() {
		search = (ImageView) findViewById(R.id.search_iv);
		gv = (GridView) findViewById(R.id.search_gv);
		et = (EditText) findViewById(R.id.search_et);
		pb = (ProgressBar) findViewById(R.id.search_pb);
		back = (ImageView) findViewById(R.id.search_back);
	}
	@Override
	protected void onDestroy() {
		MyApplication.instance.getActs().remove(act);
		super.onDestroy();
	}
	
	//--------------------------------------------------------
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.search_iv:
			String content = et.getText().toString().trim();//编辑框的内容 搜索的内容
			pb.setVisibility(View.VISIBLE);
			if(content == null || content.equals("")){
				adapter = new SearchGvAdapter(ss, act);
				pb.setProgress(50);
			}else{
				adapter = new SearchGvAdapter(datas, act);
				pb.setProgress(50);
			}
			gv.setAdapter(adapter);
			pb.setProgress(100);
			pb.setVisibility(View.INVISIBLE);
			break;

		case R.id.search_back:
			act.finish();
			break;
		}
	}
	//------------------------------------------------
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(act, TypeActivity.class);
		intent.putExtra("position", arg2);
		act.shortToast(arg2+"");
		startActivity(intent);
	}
}
