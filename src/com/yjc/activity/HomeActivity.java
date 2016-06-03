package com.yjc.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;

import com.lidroid.xutils.exception.DbException;
import com.yjc.adapter.TypeBrandAdapter;
import com.yjc.app.MyApplication;
import com.yjc.base.BaseActivity;
import com.yjc.bean.CarTypeBean;
import com.yjc.bean.CarTypeSonBean;
import com.yjc.bean.UserBean;
import com.yjc.dao.UserDao;
import com.yjc.db.TotalDbConnection;
import com.yjc.fragment.HomeFragment;
import com.yjc.fragment.MyFragment;
import com.yjc.fragment.ShoppingFragment;
import com.yjc.fragment.TypeFragment;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;
import com.yjc.zhishanglend.R.color;
/**
 * 首页
 * @author yjc Jeff.Yao
 *
 */
public class HomeActivity extends BaseActivity implements OnClickListener {
	private static HomeActivity act;
	private LinearLayout[] ll = new LinearLayout[4];
	private int imgs[] = {R.drawable.main_tab1_pressed,R.drawable.main_tab2_pressed,R.drawable.main_tab4_pressed,R.drawable.main_tab5_pressed};
	private int un_imgs[] = {R.drawable.main_tab1_nomal,R.drawable.main_tab2_nomal,R.drawable.main_tab4_nomal,R.drawable.main_tab5_nomal};
	private final int Color_Orange = Color.parseColor("#FF5809");
	private final int Color_Black = Color.parseColor("#000000");
	
	private MyFragment mf; //我的
	private HomeFragment hf;//首页
	private TotalDbConnection connection;//加入数据
	private ShoppingFragment sf;//购物车
	private TypeFragment tf;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.act_home);
		ControlTime();//计算时间是否超过期限
		act = this;
		MyApplication.instance.getActs().add(act);
		connection = new TotalDbConnection(act);
		initViews();
		initFragments();
		doViews();
	}
	/**
	 * Frgament初始化
	 */
	private void initFragments() {
		// TODO Auto-generated method stub
		mf = new MyFragment();
		hf = new HomeFragment();
		sf = new ShoppingFragment();
		tf = new TypeFragment();
		replaceF(0);
	}
	/**
	 * 操作
	 */
	private void doViews() {
		// TODO Auto-generated method stub
		for (int i = 0; i < ll.length; i++) {
			ll[i].setOnClickListener(this);
		}
	}
	/**
	 * 初始化控件
	 */
	private void initViews() {
		// TODO Auto-generated method stub
		ll[0] = (LinearLayout) findViewById(R.id.home_bottom_tab_ll1);
		ll[1] = (LinearLayout) findViewById(R.id.home_bottom_tab_ll2);
		ll[2] = (LinearLayout) findViewById(R.id.home_bottom_tab_ll3);
		ll[3] = (LinearLayout) findViewById(R.id.home_bottom_tab_ll4);
	}
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MyApplication.instance.getActs().remove(act);
		super.onDestroy();
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.home_bottom_tab_ll1:
			changedSelected(0);
			replaceF(0);
			break;
		case R.id.home_bottom_tab_ll2:
			changedSelected(1);
			replaceF(1);
			break;
		case R.id.home_bottom_tab_ll3:
			changedSelected(2);
			replaceF(2);
			break;
		case R.id.home_bottom_tab_ll4:
			changedSelected(3);
			replaceF(3);
			break;
			//homeFragment的图形按钮  更多  切换到TypeFragment
		case R.id.home_more:
			changedSelected(1);
			replaceF(1);
			break;
		default:
			break;
		}
	}
	/**
	 * 对导航栏进行处理
	 * @param pos
	 */
	private void changedSelected(int pos){
		for (int i = 0; i < ll.length; i++) {
			if(i == pos){
				//选中
				ImageView iv = (ImageView) ll[i].getChildAt(0);
				TextView tv = (TextView) ll[i].getChildAt(1);
				iv.setImageResource(imgs[i]);
				tv.setTextColor(Color_Orange);
			}else{
				//未选中
				ImageView iv = (ImageView) ll[i].getChildAt(0);
				TextView tv = (TextView) ll[i].getChildAt(1);
				iv.setImageResource(un_imgs[i]);
				tv.setTextColor(Color_Black);
			}
		}
	}
	/**
	 * 切换Fragment
	 * @param pos
	 */
	private void replaceF(int pos){
		switch (pos) {
		case 0:
			act.getSupportFragmentManager().beginTransaction().replace(R.id.home_fl, hf).commit();
			break;
		case 1:
			act.getSupportFragmentManager().beginTransaction().replace(R.id.home_fl, tf).commit();
			break;
		case 2:
			act.getSupportFragmentManager().beginTransaction().replace(R.id.home_fl, sf).commit();
			break;
		case 3:
			act.getSupportFragmentManager().beginTransaction().replace(R.id.home_fl, mf).commit();
			break;
		}
	}
	/**
	 * 计算时间是否超过期限
	 */
	private void ControlTime(){
		long time = getSharedPreferences("user", Context.MODE_PRIVATE).getLong("time", 0);
		if(System.currentTimeMillis() - time >= 3 * 24 * 60 * 60 * 1000){
			getSharedPreferences("user", Context.MODE_PRIVATE).edit()
			.putString("name", "")
			.putString("pwd", "")
			.putString("head", "")
			.putInt("isLogin", 0)
			.putLong("time", System.currentTimeMillis())
			.commit();
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			showSweetDialog("警告", "确定要退出吗？", positive, null);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	//---------------------------------------
	private OnSweetClickListener positive = new OnSweetClickListener() {
		
		@Override
		public void onClick(SweetAlertDialog sweetAlertDialog) {
			// TODO Auto-generated method stub
			act.finish();
		}
	};
	
	/**
	 * ModifyPwd  修改密码
	 * @param name
	 * @param new_password
	 * @param head
	 */
	public static boolean modifyPwd(String name,String old_password,String new_password,String head){
		UserBean user = new UserBean(name, new_password, 0, head);
//		shortToast(getIntent().getStringExtra("name"));
//		HomeActivity h = new HomeActivity();
		UserDao dao = new UserDao(act);
		if(dao.findNameAndPwdIsOk(name, old_password)){
			dao.delete(0);
			dao.insert(user);
			act.getSharedPreferences("user", Context.MODE_PRIVATE).edit()
			.putString("name", name)
			.putString("pwd", new_password)
			.putString("head", head)
			.putInt("isLogin", 0)
			.commit();
			return true;
		}else{
			return false;
		}
		
//		shortToast("修改密码成功");
	}
	
	
	
	
	
}
