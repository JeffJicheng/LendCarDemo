package com.yjc.activity;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.yjc.adapter.TypeAdapter;
import com.yjc.app.MyApplication;
import com.yjc.base.BaseActivity;
import com.yjc.bean.CarTypeBean;
import com.yjc.bean.CarTypeSonBean;
import com.yjc.config.Config;
import com.yjc.db.TotalDbConnection;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ExpandableListView.OnChildClickListener;
/**
 * 车型分类类型
 * @author yjc Jeff.Yao
 *
 */
public class TypeActivity extends BaseActivity implements OnClickListener{
	private TypeActivity act;
	private ImageView back;
	private ExpandableListView expand_lv;
	
	private List<CarTypeBean> datas;//车型的数据源
	private TypeAdapter adapter;//适配器
	private List<List<CarTypeSonBean>> son_datas;//车型分类的数据源一级数据源
	private List<CarTypeSonBean> s_datas;//车型分类的数据源二级数据源
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_type);
		act = this;
		MyApplication.instance.getActs().add(act);
		initViews();
		initDatas();
		doViews();
	}
	/**
	 * ExpandableListView 数据源初始化
	 */
	private void initDatas() {
		son_datas = new ArrayList<List<CarTypeSonBean>>();
		try {
			datas = SystemUtils.getDbUtils(act).findAll(Selector.from(CarTypeBean.class).where("type_id", "=", getIntent().getIntExtra("position", 0)));
			s_datas = SystemUtils.getDbUtils(act).findAll(Selector.from(CarTypeSonBean.class).where("typeson_id", "=", getIntent().getIntExtra("position", 0)));
			son_datas.add(s_datas);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 操作  事件
	 */
	private void doViews() {
		back.setOnClickListener(this);
		adapter = new TypeAdapter(datas, act, son_datas);
		expand_lv.setAdapter(adapter);
		expand_lv.setGroupIndicator(null);//消除自带的ListView的指示图片
		expand_lv.setOnChildClickListener(onChildClickListener);
		
	}
	/**
	 * 初始化控件
	 */
	private void initViews() {
		back = (ImageView) findViewById(R.id.type_back);
		expand_lv = (ExpandableListView) findViewById(R.id.type_lv);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MyApplication.instance.getActs().remove(act);
		super.onDestroy();
	}
	
	//-------------------------------------
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		//返回
		case R.id.type_back:
			act.finish();
			break;
		}
	}
	/**
	 * 二级布局的监听
	 */
	private OnChildClickListener onChildClickListener = new OnChildClickListener() {
		
		@Override
		public boolean onChildClick(ExpandableListView arg0, View arg1, int arg2,
				int arg3, long arg4) {
			//从Adapter取
			//取出点击相关的信息
			CarTypeSonBean cs = (CarTypeSonBean) adapter.getChild(arg2, arg3);
			String name = cs.getIntroduction();
			//跳转到XiangxiActivity
			Intent intent = new Intent(act, XiangxiActivity.class);
			intent.putExtra("name", name);//将名称传入过去
			startActivity(intent);
			return false;
		}
	};
}
