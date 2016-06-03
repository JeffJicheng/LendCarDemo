package com.yjc.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.yjc.activity.ChankanActivity;
import com.yjc.activity.HomeActivity;
import com.yjc.activity.XiangxiActivity;
import com.yjc.adapter.TypeBrandAdapter;
import com.yjc.base.BaseFragment;
import com.yjc.bean.CarBrandBean;
import com.yjc.bean.CarTypeBean;
import com.yjc.bean.CarTypeSonBean;
import com.yjc.config.Config;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;
/**
 * 类型fragment
 * @author yjc jeff.Yao
 *
 */
public class TypeFragment extends BaseFragment{
	private HomeActivity act;
	private ExpandableListView lv;
	private ImageView back;
	private List<CarBrandBean> datas;
	private TypeBrandAdapter adapter;
	private List<List<CarTypeBean>> son_datas;
	private List<CarTypeBean> s_datas;
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
		return inflater.inflate(R.layout.act_type, null);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		initViews();
		initDatas();
		doViews();
		super.onActivityCreated(savedInstanceState);
	}
	/**
	 * 操作
	 */
	private void doViews() {
		adapter = new TypeBrandAdapter(act, datas, son_datas);
		lv.setAdapter(adapter);
		lv.setGroupIndicator(null);//消除自带的ListView的指示图片
		lv.setOnChildClickListener(onchildListener);
	}
	/**
	 * 初始化数据源
	 */
	private void initDatas() {
		s_datas = new ArrayList<CarTypeBean>();
		son_datas = new ArrayList<List<CarTypeBean>>();
		datas = new ArrayList<CarBrandBean>();
		//父类的数据源
		for (int i = 0; i < Config.home_carType.length; i++) {
			CarBrandBean bean = new CarBrandBean(i, Config.home_carType[i], Config.home_carName[i]);
			datas.add(bean);
		}
		//子类的数据源
		try {
			for (int i = 0; i < Config.home_carName.length; i++) {
				s_datas = SystemUtils.getDbUtils(act).findAll(Selector.from(CarTypeBean.class).where("type_id", "=", i));
				son_datas.add(s_datas);
			}
		} catch (DbException e) {
			e.printStackTrace();
			Log.e("yjc", "子类的数据源查询失败");
		}
	}
	/**
	 * 初始化控件
	 */
	private void initViews() {
		lv = (ExpandableListView) findviewbyid(R.id.type_lv);
		back = (ImageView) findviewbyid(R.id.type_back);
		back.setVisibility(View.INVISIBLE);
	}
	//----------------------------------------------------------------
	private OnChildClickListener onchildListener = new OnChildClickListener() {
		
		@Override
		public boolean onChildClick(ExpandableListView arg0, View arg1, int arg2,
				int arg3, long arg4) {
			CarTypeBean ctbean = (CarTypeBean) adapter.getChild(arg2, arg3);
			String name = ctbean.getName();
			Intent intent = new Intent(act,ChankanActivity.class);
			intent.putExtra("name", name);
			startActivity(intent);
			return false;
		}
	};
}
