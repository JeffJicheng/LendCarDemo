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
 * ��������
 * @author yjc Jeff.Yao
	д������δ�����˼·��1.��û�и���ʱ��֮������back������ô����Ҫ���浽δ��ɵĶ����������� ������Ҫ����һ�����������������Ҫ��ն�����������ݿ� 
					2.�������ȷ�ϸ����Ҫ�������µ�ʱ��Ͷ���״̬���浽����ɵĶ������������ݿ��� ��������ü��붩���ţ����������Ҫ��ն�����������ݿ� 
 */
public class ShoppingXQActivity extends BaseActivity implements OnClickListener{
	private ShoppingXQActivity act;
	/*�ؼ�*/
	private ListView lv;
	private Button sendMoney;
	private TextView money;
	private TextView dingdanhao;
	private ImageView back;
	
	/*������������Դ*/
	private List<ShoppingXQBean> datas;
	private SXQAdapter adapter;
	
	/*���� */
	private int totalMoney = 0;//�ܹ���Ǯ
	private String dingdanNumber;//������
	
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
	 * ����Դ��ʼ��
	 */
	private void initDatas() {
		/**
		 * ListView������Դ
		 */
		try {
			datas = SystemUtils.getDbUtils(act).findAll(Selector.from(ShoppingXQBean.class));
			Collections.reverse(datas);//�����ݷ�ת
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * ��ʼ���ܸ����Ǯ
		 */
		for (int i = 0; i < datas.size(); i++) {
			totalMoney += datas.get(i).getMoney() * datas.get(i).getNumbers();
		}
	}
	/**
	 * ����
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
	 * ��ʼ���ؼ�
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
	//-------------------ȷ�ϸ���---------------------
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.shoppingxq_dingdanhao:
			act.showSweetDialog("����", "ȷ��Ҫ������", positive1, null);
			break;
		case R.id.shoppingxq_back:
			act.showSweetDialog("����", "ȷ��Ҫ����������", positive, null);
			break;

		default:
			break;
		}
		
	}
	
	/**
	 * back��
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			act.showSweetDialog("����", "ȷ��Ҫ�������", positive, null);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	//--------------------��������ļ���----------------
	private OnSweetClickListener positive = new OnSweetClickListener() {
		
		@Override
		public void onClick(SweetAlertDialog sweetAlertDialog) {
			//�洢δ��ɵĶ���
			for (int i = 0; i < datas.size(); i++) {
				NoFinishBean bean = new NoFinishBean(datas.get(i).getImg(), datas.get(i).getName(), "��"+datas.get(i).getMoney(), "X"+datas.get(i).getNumbers());
				try {
					SystemUtils.getDbUtils(act).save(bean);
				} catch (DbException e) {
					e.printStackTrace();
					Log.e("yjc", e.getMessage().toString()+"δ��ɵĶ����洢�ɹ�");
				}
			}
			//�����е�ɾ����
			try {
				SystemUtils.getDbUtils(act).deleteAll(ShoppingXQBean.class);
				datas.clear();
			} catch (DbException e) {
				e.printStackTrace();
				Log.e("yjc", e.getMessage().toString()+"ûɾ���ɹ�");
			}
			act.finish();
		}
	};
	//--------------------ȷ������----------------------
	OnSweetClickListener positive1 = new OnSweetClickListener() {
		
		@Override
		public void onClick(SweetAlertDialog sweetAlertDialog) {
			for (int i = 0; i < datas.size(); i++) {
				String time = StringUtils.getTime(System.currentTimeMillis());
				FinishBean bean = new FinishBean(dingdanNumber, totalMoney, time, "�Ѹ���",datas.get(i).getName(),datas.get(i).getImg(),"��"+datas.get(i).getMoney(),"X "+datas.get(i).getNumbers());
				try {
					SystemUtils.getDbUtils(act).save(bean);
					act.shortToast("����ɹ�");
				} catch (DbException e) {
					e.printStackTrace();
					Log.e("yjc", e.getMessage().toString()+":����ʧ��");
					act.shortToast("����ʧ��");
				}
			}
			//�����е�ɾ����
			try {
				SystemUtils.getDbUtils(act).deleteAll(ShoppingXQBean.class);
				datas.clear();
			} catch (DbException e) {
				e.printStackTrace();
				Log.e("yjc", e.getMessage().toString()+"ûɾ���ɹ�");
			}
			startActivity(new Intent(act, FinishedDingdanActivity.class));
			act.finish();
			
		}
	};
	
}
