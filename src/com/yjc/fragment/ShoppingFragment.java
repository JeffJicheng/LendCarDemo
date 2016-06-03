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
 * ���ﳵ
 * @author yjc jeff.Yao
 *
 */
public class ShoppingFragment extends BaseFragment implements OnClickListener {
	private HomeActivity act;
	private ListView shopping_lv;//ListView
	private ShoppingLvAdapter adapter;//������
	private List<ShoppingBean> datas;//����Դ
	private static TextView money;//�ܼƣ� �Ƕ���Ԫ 
	private CheckBox all_check;//ȫѡ
	private Button jiesuan;//����
	
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
	 * ����Դ��ʼ��
	 */
	private void initDatas() {
		// TODO Auto-generated method stub
		try {
			datas = SystemUtils.getDbUtils(act).findAll(Selector.from(ShoppingBean.class));
			if(datas == null || datas.size() == 0){
				datas = new ArrayList<ShoppingBean>();
				Collections.reverse(datas);//�����ݽ��з�ת
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			Log.e("yjc", e.getMessage().toString());
			e.printStackTrace();
		}
	}
	/**
	 * ����
	 */
	private void doViews() {
		adapter = new ShoppingLvAdapter(act, datas);
		shopping_lv.setAdapter(adapter);
		all_check.setOnCheckedChangeListener(listener);
		jiesuan.setOnClickListener(this);
	}
	/**
	 * �õ�Money�ؼ�
	 * @return
	 */
	public static TextView getMoney() {
		return money;
	}
	/**
	 * ����Monkey�ؼ�
	 * @param money
	 */
	public void setMoney(TextView money) {
		this.money = money;
	}
	/**
	 * ��ʼ���ؼ�
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
				datas.get(i).setSelected(arg1);//�����Ƿ�ѡ�еĽ��
				if(arg1){
					sum += datas.get(i).getPrice() * datas.get(i).getNums();
					ShoppingFragment.getMoney().setText("�ϼƣ� ��"+sum);
					datas.get(i).setIsSelectedNum(1);
				}else{
					sum -= datas.get(i).getPrice() * datas.get(i).getNums();
					ShoppingFragment.getMoney().setText("�ϼƣ� ��"+sum);
					datas.get(i).setIsSelectedNum(0);
				}
			}
			adapter = new ShoppingLvAdapter(act, datas);
			shopping_lv.setAdapter(adapter);
		}
		
	};
	//--------------���㰴ť---------------
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
	 * ���浽Shoppingxiangqing
	 */
	private void saveAndIntent(){
		boolean bool = false;
		for (int i = 0; i < datas.size(); i++) {
			if(datas.get(i).isSelected()){
				bool = true;
//				Log.i("yjc", datas.get(i).getIsSelectedNum()+"datas.get(i).getIsSelectedNum()");
//				Log.i("yjc", i+"��ѡ����");
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
			act.showSweetDialog("��ʾ", "������ѡ��һ����Ʒ!");
		}else{
			/**
			 * ���ȫ���Ĺ��ﳵ�еļ�¼
			 */
			try {
				money.setText("�ϼƣ� ��0");
				all_check.setChecked(false);
				long time = System.currentTimeMillis();//��ʱ������������
				String s = String.valueOf(time).substring(0, 7);//��ǰ7λ
				Intent intent = new Intent(act, ShoppingXQActivity.class);
				intent.putExtra("dingdanhao", s);
				startActivity(intent);
				SystemUtils.getDbUtils(act).deleteAll(ShoppingBean.class);
				datas.clear();
//				shortToast(act, "����ɹ�");
				Log.i("yjc", "����ɹ�");
			} catch (DbException e) {
				e.printStackTrace();
				Log.e("yjc", e.getMessage().toString());
			}
		}
	}
}
