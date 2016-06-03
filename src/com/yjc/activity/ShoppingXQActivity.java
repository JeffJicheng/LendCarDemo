package com.yjc.activity;

import java.util.Collections;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.yjc.adapter.SXQAdapter;
import com.yjc.app.MyApplication;
import com.yjc.base.BaseActivity;
import com.yjc.bean.FinishBean;
import com.yjc.bean.NoFinishBean;
import com.yjc.bean.ShoppingXQBean;
import com.yjc.util.StringUtils;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;
/**
 * 订单详情
 * @author yjc Jeff.Yao
	写付款与未付款的思路：1.当没有付款时，之间点击了back键，那么则需要保存到未完成的订单的容器中 所以需要创建一个容器，保存完后需要清空订单详情的数据库 
					2.当点击了确认付款，需要将金额和下单时间和订单状态保存到已完成的订单的容器数据库中 若可以最好加入订单号，保存完后需要清空订单详情的数据库 
 */
public class ShoppingXQActivity extends BaseActivity implements OnClickListener{
	private ShoppingXQActivity act;
	/*控件*/
	private ListView lv;
	private Button sendMoney;
	private TextView money;
	private TextView dingdanhao;
	private ImageView back;
	
	/*适配器和数据源*/
	private List<ShoppingXQBean> datas;
	private SXQAdapter adapter;
	
	/*变量 */
	private int totalMoney = 0;//总共的钱
	private String dingdanNumber;//订单号
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.act_shoppingxq);
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
		/**
		 * ListView的数据源
		 */
		try {
			datas = SystemUtils.getDbUtils(act).findAll(Selector.from(ShoppingXQBean.class));
			Collections.reverse(datas);//让数据翻转
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * 初始化总付款的钱
		 */
		for (int i = 0; i < datas.size(); i++) {
			totalMoney += datas.get(i).getMoney() * datas.get(i).getNumbers();
		}
	}
	/**
	 * 操作
	 */
	private void doViews() {
		adapter = new SXQAdapter(act, datas);
		lv.setAdapter(adapter);
		money.setText(totalMoney+"");
		Intent intent = getIntent();
		dingdanNumber = intent.getStringExtra("dingdanhao");
		dingdanhao.setText(dingdanNumber);
		sendMoney.setOnClickListener(this);
		back.setOnClickListener(this);
	}
	/**
	 * 初始化控件
	 */
	private void initViews() {
		lv = (ListView) findViewById(R.id.shoppingxq_lv);
		sendMoney = (Button) findViewById(R.id.shoppingxq_yes);
		money = (TextView) findViewById(R.id.shoppingxq_money);
		dingdanhao = (TextView) findViewById(R.id.shoppingxq_dingdanhao);
		back = (ImageView) findViewById(R.id.shoppingxq_back);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MyApplication.instance.getActs().remove(act);
		super.onDestroy();
	}
	//-------------------确认付款---------------------
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.shoppingxq_dingdanhao:
			act.showSweetDialog("警告", "确定要付款吗？", positive1, null);
			break;
		case R.id.shoppingxq_back:
			act.showSweetDialog("警告", "确定要放弃付款吗？", positive, null);
			break;

		default:
			break;
		}
		
	}
	
	/**
	 * back键
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			act.showSweetDialog("警告", "确定要放弃付款？", positive, null);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	//--------------------放弃付款的监听----------------
	private OnSweetClickListener positive = new OnSweetClickListener() {
		
		@Override
		public void onClick(SweetAlertDialog sweetAlertDialog) {
			//存储未完成的订单
			for (int i = 0; i < datas.size(); i++) {
				NoFinishBean bean = new NoFinishBean(datas.get(i).getImg(), datas.get(i).getName(), "￥"+datas.get(i).getMoney(), "X"+datas.get(i).getNumbers());
				try {
					SystemUtils.getDbUtils(act).save(bean);
				} catch (DbException e) {
					e.printStackTrace();
					Log.e("yjc", e.getMessage().toString()+"未完成的订单存储成功");
				}
			}
			//将现有的删除掉
			try {
				SystemUtils.getDbUtils(act).deleteAll(ShoppingXQBean.class);
				datas.clear();
			} catch (DbException e) {
				e.printStackTrace();
				Log.e("yjc", e.getMessage().toString()+"没删除成功");
			}
			act.finish();
		}
	};
	//--------------------确定付款----------------------
	OnSweetClickListener positive1 = new OnSweetClickListener() {
		
		@Override
		public void onClick(SweetAlertDialog sweetAlertDialog) {
			for (int i = 0; i < datas.size(); i++) {
				String time = StringUtils.getTime(System.currentTimeMillis());
				FinishBean bean = new FinishBean(dingdanNumber, totalMoney, time, "已付款",datas.get(i).getName(),datas.get(i).getImg(),"￥"+datas.get(i).getMoney(),"X "+datas.get(i).getNumbers());
				try {
					SystemUtils.getDbUtils(act).save(bean);
					act.shortToast("付款成功");
				} catch (DbException e) {
					e.printStackTrace();
					Log.e("yjc", e.getMessage().toString()+":付款失败");
					act.shortToast("付款失败");
				}
			}
			//将现有的删除掉
			try {
				SystemUtils.getDbUtils(act).deleteAll(ShoppingXQBean.class);
				datas.clear();
			} catch (DbException e) {
				e.printStackTrace();
				Log.e("yjc", e.getMessage().toString()+"没删除成功");
			}
			startActivity(new Intent(act, FinishedDingdanActivity.class));
			act.finish();
			
		}
	};
	
}
