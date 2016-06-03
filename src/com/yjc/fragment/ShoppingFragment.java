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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.yjc.activity.HomeActivity;
import com.yjc.activity.ShoppingXQActivity;
import com.yjc.adapter.ShoppingLvAdapter;
import com.yjc.base.BaseFragment;
import com.yjc.bean.ShoppingBean;
import com.yjc.bean.ShoppingXQBean;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;
/**
 * 购物车
 * @author yjc jeff.Yao
 *
 */
public class ShoppingFragment extends BaseFragment implements OnClickListener {
	private HomeActivity act;
	private ListView shopping_lv;//ListView
	private ShoppingLvAdapter adapter;//适配器
	private List<ShoppingBean> datas;//数据源
	private static TextView money;//总计￥ 是多少元 
	private CheckBox all_check;//全选
	private Button jiesuan;//结算
	
	private int sum;
	
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
		return inflater.inflate(R.layout.fragment_shopping, null);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		initViews();
		initDatas();
		doViews();
		super.onActivityCreated(savedInstanceState);
	}
	/**
	 * 数据源初始化
	 */
	private void initDatas() {
		// TODO Auto-generated method stub
		try {
			datas = SystemUtils.getDbUtils(act).findAll(Selector.from(ShoppingBean.class));
			if(datas == null || datas.size() == 0){
				datas = new ArrayList<ShoppingBean>();
				Collections.reverse(datas);//将数据进行翻转
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			Log.e("yjc", e.getMessage().toString());
			e.printStackTrace();
		}
	}
	/**
	 * 操作
	 */
	private void doViews() {
		adapter = new ShoppingLvAdapter(act, datas);
		shopping_lv.setAdapter(adapter);
		all_check.setOnCheckedChangeListener(listener);
		jiesuan.setOnClickListener(this);
	}
	/**
	 * 得到Money控件
	 * @return
	 */
	public static TextView getMoney() {
		return money;
	}
	/**
	 * 设置Monkey控件
	 * @param money
	 */
	public void setMoney(TextView money) {
		this.money = money;
	}
	/**
	 * 初始化控件
	 */
	private void initViews() {
		shopping_lv = (ListView) findviewbyid(R.id.shopping_lv);
		money = (TextView) findviewbyid(R.id.shopping_money);
		setMoney(money);
		all_check = (CheckBox) findviewbyid(R.id.shopping_allcheckbox);
		jiesuan = (Button) findviewbyid(R.id.shopping_jiesuan);
	}
	//------------------------------------------------------------------
	private OnCheckedChangeListener listener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
			for (int i = 0; i < datas.size(); i++) {
				datas.get(i).setSelected(arg1);//设置是否被选中的结果
				if(arg1){
					sum += datas.get(i).getPrice() * datas.get(i).getNums();
					ShoppingFragment.getMoney().setText("合计： ￥"+sum);
					datas.get(i).setIsSelectedNum(1);
				}else{
					sum -= datas.get(i).getPrice() * datas.get(i).getNums();
					ShoppingFragment.getMoney().setText("合计： ￥"+sum);
					datas.get(i).setIsSelectedNum(0);
				}
			}
			adapter = new ShoppingLvAdapter(act, datas);
			shopping_lv.setAdapter(adapter);
		}
		
	};
	//--------------结算按钮---------------
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.shopping_jiesuan:
			saveAndIntent();
			break;
		default:
			break;
		}
	}
	/**
	 * 保存到Shoppingxiangqing
	 */
	private void saveAndIntent(){
		boolean bool = false;
		for (int i = 0; i < datas.size(); i++) {
			if(datas.get(i).isSelected()){
				bool = true;
//				Log.i("yjc", datas.get(i).getIsSelectedNum()+"datas.get(i).getIsSelectedNum()");
//				Log.i("yjc", i+"被选中了");
				ShoppingXQBean bean = new ShoppingXQBean(datas.get(i).getImg(), datas.get(i).getCarName(), datas.get(i).getPrice(), datas.get(i).getNums());
				try {
					SystemUtils.getDbUtils(act).save(bean);
				} catch (DbException e) {
					e.printStackTrace();
					Log.e("yjc", e.getMessage().toString());
				}
			}
		}
		if(!bool){
			act.showSweetDialog("提示", "请至少选择一件商品!");
		}else{
			/**
			 * 清楚全部的购物车中的记录
			 */
			try {
				money.setText("合计： ￥0");
				all_check.setChecked(false);
				long time = System.currentTimeMillis();//用时间来做订单号
				String s = String.valueOf(time).substring(0, 7);//用前7位
				Intent intent = new Intent(act, ShoppingXQActivity.class);
				intent.putExtra("dingdanhao", s);
				startActivity(intent);
				SystemUtils.getDbUtils(act).deleteAll(ShoppingBean.class);
				datas.clear();
//				shortToast(act, "保存成功");
				Log.i("yjc", "保存成功");
			} catch (DbException e) {
				e.printStackTrace();
				Log.e("yjc", e.getMessage().toString());
			}
		}
	}
}
