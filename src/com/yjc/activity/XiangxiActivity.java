package com.yjc.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.yjc.app.MyApplication;
import com.yjc.base.BaseActivity;
import com.yjc.bean.CarXiangxiBean;
import com.yjc.bean.MyShoucangBean;
import com.yjc.bean.ShoppingBean;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;
/**
 * ������ϸ��Ϣչʾ
 * @author yjc Jeff.Yao
 *
 */
public class XiangxiActivity extends BaseActivity implements OnClickListener {
	private XiangxiActivity act;
	private ImageView car_img;
	private TextView car_name,car_price,car_type,car_jibie,car_fadongji,car_biansuxiang,car_jiegou;
	private List<CarXiangxiBean> list;
	private ImageView add,sub;//�Ӻͼ�  ���복�ӵ����ﳵ��������ť
	private Button join_gouwu;//���빺�ﳵ
	private EditText et_nums;//���빺�ﳵ������
	private Button shoucang;
	private ImageView back;
	
	private int num = 1;//���복�ӵ����ﳵ�������ı�������ʼֵΪ1
	private int img;//ͼƬ
	private String name;//����
	private String type;//����
	private String jibie;//����
	private String fadongji;//������
	private String biansuxiang;//������
	private String jiegou;//�ṹ
	private int price;//��Ǯ
	private int shopping_id=1;//id
	
	private int isLogin;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.act_xiangxi);
		act = this;
		MyApplication.instance.getActs().add(act);
		initViews();
		initDatas();
		doViews();
	}
	/**
	 * ���� 
	 */
	private void doViews() {
		isLogin = act.getSharedPreferences("user", Context.MODE_PRIVATE).getInt("isLogin", 0);
		for (int i = 0; i < list.size(); i++) {
			img = list.get(i).getImg();
			car_img.setImageResource(img);
			name = list.get(i).getCarName().toString().trim();
			car_name.setText(name);
			type = list.get(i).getTypeName().toString().trim();
			car_type.setText(type);
			jibie = list.get(i).getJibie().toString().trim();
			car_jibie.setText(jibie);
			fadongji = list.get(i).getFadongji().toString().trim();
			car_fadongji.setText(fadongji);
			biansuxiang = list.get(i).getBiansuxiang().toString().trim();
			car_biansuxiang.setText(biansuxiang);
			jiegou = list.get(i).getJiegou().toString().trim();
			car_jiegou.setText(jiegou);
			price = list.get(i).getPrice();
			car_price.setText(price+"");
		}
		add.setOnClickListener(this);
		sub.setOnClickListener(this);
		join_gouwu.setOnClickListener(this);
		shoucang.setOnClickListener(this);
		car_img.setOnClickListener(this);
		back.setOnClickListener(this);
	}
	/**
	 * ��ʼ������Դ
	 */
	private void initDatas() {
		//��ѯ �������ֲ�ѯ
		list = new ArrayList<CarXiangxiBean>();
		try {
			list = SystemUtils.getDbUtils(act).findAll(Selector.from(CarXiangxiBean.class).where("carName", "=",getIntent().getStringExtra("name") ));
		} catch (DbException e) {
			e.printStackTrace();
			act.shortToast("��ѯʧ��"+e.getMessage());
			Log.e("yjc", e.getMessage().toString()+"��ѯʧ��");
		}
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initViews() {
		car_img = (ImageView) findViewById(R.id.xiangxi_carimg);
		car_name = (TextView) findViewById(R.id.xiangxi_carname);
		car_price = (TextView) findViewById(R.id.xiangxi_carprice);
		car_type = (TextView) findViewById(R.id.xiangxi_cartype);
		car_jibie = (TextView) findViewById(R.id.xiangxi_carjibie);
		car_fadongji = (TextView) findViewById(R.id.xiangxi_carfadongji);
		car_biansuxiang = (TextView) findViewById(R.id.xiangxi_carbiansuxiang);
		car_jiegou = (TextView) findViewById(R.id.xiangxi_carjiegou);
		add = (ImageView) findViewById(R.id.xiangxi_add);
		sub = (ImageView) findViewById(R.id.xiangxi_sub);
		join_gouwu = (Button) findViewById(R.id.xiangxi_shopping);
		et_nums = (EditText) findViewById(R.id.xiangxi_nums);
		shoucang = (Button) findViewById(R.id.xiangxi_shoucang);
		back = (ImageView) findViewById(R.id.xiangxi_back);
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		MyApplication.instance.getActs().remove(act);
		super.onDestroy();
	}
	
	//-----------------------------------------
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		//��
		case R.id.xiangxi_add:
			num = Integer.parseInt(et_nums.getText().toString().trim());
			num++;
			et_nums.setText(num+"");
			break;
		//��
		case R.id.xiangxi_sub:
			num = Integer.parseInt(et_nums.getText().toString().trim());
			if(num > 0){
				num--;
				et_nums.setText(num+"");
			}
			break;
		//����
		case R.id.xiangxi_shopping:
			String head = getSharedPreferences("user", Context.MODE_PRIVATE).getString("head", "");
			if(head == null || head.equals("") || head.length() == 0 || isLogin == 0){
				//Ϊ�վ���û�е�¼
				showSweetDialog("��ʾ", "�ף���û�е�¼�����ȵ�¼ร�", positive, null);
			}else{
				//��Ϊ�վ����Ѿ���¼
				//���빺�ﳵ
				saveShopping();
			}
			break;
			//�ղر���
		case R.id.xiangxi_shoucang:
			String head1 = getSharedPreferences("user", Context.MODE_PRIVATE).getString("head", "");
			if(head1 == null ||head1.equals("") || head1.length() == 0 || isLogin == 0){
				//Ϊ�վ���û�е�¼
				showSweetDialog("��ʾ", "�ף���û�е�¼�����ȵ�¼ร�", positive, null);
			}else{
				//��Ϊ�վ����Ѿ���¼
				//�ղ�
				shoucang();
			}
			break;
			//����ͼƬ����
		case R.id.xiangxi_carimg:
			Intent intent = new Intent(act, ShowImgActivity.class);
			intent.putExtra("img", img);
			startActivity(intent);
			overridePendingTransition(R.anim.sa_act_incoming, R.anim.aa_act_outexit);
			break;
		case R.id.xiangxi_back:
			act.finish();
			break;
		}
	}
	
	private void shoucang(){
		try {
			List<MyShoucangBean> datas = SystemUtils.getDbUtils(act).findAll(Selector.from(MyShoucangBean.class).where("name", "=", name));
			if(datas == null||datas.size() == 0 ){
				MyShoucangBean bean = new MyShoucangBean(img, name, "��"+price);
				SystemUtils.getDbUtils(act).save(bean);
				shortToast("�ղسɹ�");
			}else{
				shortToast("�Ѿ��ղ�");
			}
		} catch (DbException e) {
			e.printStackTrace();
			Log.e("yjc", "�ղ�ʧ��"+e.getMessage().toString());
		}
	}
	
	
	/**
	 * ���빺�ﳵ
	 */
	private void saveShopping() {
		//���빺�ﳵ��Ҫ  _id shopping_id img name brandName price number
		try {
			
			ShoppingBean sbean = new ShoppingBean(getMax()+shopping_id, type, name, num, price,img,false,0);
			SystemUtils.getDbUtils(act).save(sbean);
			shortToast("�ɹ����빺�ﳵ");
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("yjc", e.getMessage().toString());
		}
	}
	/**
	 * ����id
	 * @return
	 */
	private int getMax(){
		try {
			Cursor cursor = SystemUtils.getDbUtils(act).execQuery("select max(shopping_id) from com_yjc_bean_ShoppingBean");
			if(cursor.moveToNext()){
				return cursor.getInt(0);
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e("yjc", e.getMessage().toString());
		}
		return 0;
	}
	//-------------------------------------------------------------
	private OnSweetClickListener positive = new OnSweetClickListener() {
		
		@Override
		public void onClick(SweetAlertDialog sweetAlertDialog) {
			// TODO Auto-generated method stub
			startActivity(new Intent(act, LoginActivity.class));
			act.finish();
		}
	};
	
	
	
}

