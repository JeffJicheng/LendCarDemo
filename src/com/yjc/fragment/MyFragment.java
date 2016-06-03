package com.yjc.fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;

import com.lidroid.xutils.BitmapUtils;
import com.yjc.activity.HomeActivity;
import com.yjc.activity.LoginActivity;
import com.yjc.activity.MyDingdanActivity;
import com.yjc.activity.MyShoucangActivity;
import com.yjc.activity.ZManagerActivity;
import com.yjc.base.BaseFragment;
import com.yjc.bean.UserBean;
import com.yjc.dao.UserDao;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;
/**
 *  ��  ��������
 * @author yjc Jeff.Yao
 *
 */
public class MyFragment extends BaseFragment implements OnClickListener {
	private HomeActivity act;
	private LinearLayout[] ll = new LinearLayout[3];
	private TextView tv_login;
	private String head;
	private ImageView head_user;
	private ImageView head_camara;//�����
	
	private static final int REQUEST_CODE = 1;
	private static final int REQUEST_CODE1 = 2;
	
	private int isLogin = 0;
	
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
		return inflater.inflate(R.layout.fragment_my, null);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		initViews();
		AutoLogin();
		doViews();
		super.onActivityCreated(savedInstanceState);
	}
	/**
	 * ����
	 */
	private void doViews() {
		// TODO Auto-generated method stub
		tv_login.setOnClickListener(this);
		head_user.setOnClickListener(this);
	}
	/**
	 * �ؼ���ʼ��
	 */
	private void initViews() {
		
		ll[0] = (LinearLayout) findviewbyid(R.id.my_ll1);
		ll[1] = (LinearLayout) findviewbyid(R.id.my_ll2);
		ll[2] = (LinearLayout) findviewbyid(R.id.my_ll3);
		for (int i = 0; i < ll.length; i++) {
			ll[i].setOnClickListener(this);
		}
		head_user = (ImageView) findviewbyid(R.id.my_user_head);
		head_camara = (ImageView) findviewbyid(R.id.my_user_camara);
		tv_login = (TextView) findviewbyid(R.id.my_login);
		head = act.getSharedPreferences("user", Context.MODE_PRIVATE).getString("head", "");
	}
	
	/**
	 * �Զ���¼  
	 */
	private void AutoLogin() {
		// TODO Auto-generated method stub
		String head = act.getSharedPreferences("user", Context.MODE_PRIVATE).getString("head", "");
		isLogin = act.getSharedPreferences("user", Context.MODE_PRIVATE).getInt("isLogin", 0);
		//Ϊ�ղ��Զ���¼����Ϊ���Զ���¼
		if(head == null || head.length() == 0 || head.equals("") || isLogin == 0){
			//û��¼��ʾ������¼��TextView
			tv_login.setVisibility(View.VISIBLE);
			head_user.setImageResource(R.drawable.user_image);
			//Ϊ�վͲ��Զ���¼
		}else{
			//��¼�������Ǹ�������¼��TextView
			tv_login.setVisibility(View.INVISIBLE);
			// ��ͷ���Զ���¼
			File dir = new File("/mnt/sdcard/zhishang/head/head.jpg");
			if(dir.exists()){
				//����
				head_user.setImageBitmap(BitmapFactory.decodeFile(dir.toString()));//����ͷ��
				head_camara.setVisibility(View.INVISIBLE);
			}else{
				//������
				head_user.setImageBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.user_image));
			}
		}
	}
	
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		//������¼
		case R.id.my_login:
			startActivity(new Intent(act, LoginActivity.class));
			act.finish();
			break;
			//�ҵĶ���
		case R.id.my_ll1:
			if(head == null || head.equals("") || head.length() == 0 || isLogin == 0 ){
				//Ϊ�վ���û�е�¼
				act.showSweetDialog("��ʾ", "�ף���û�е�¼�����ȵ�¼ร�", positive, null);
			}else{
				//��Ϊ�վ����Ѿ���¼
				act.startActivity(new Intent(act, MyDingdanActivity.class));
			}
			break;
			//�ҵ��ղ�
		case R.id.my_ll2:
			if(head == null || head.equals("") || head.length() == 0 || isLogin == 0){
				//Ϊ�վ���û�е�¼
				act.showSweetDialog("��ʾ", "�ף���û�е�¼�����ȵ�¼ร�", positive, null);
			}else{
				//��Ϊ�վ����Ѿ���¼
				startActivity(new Intent(act, MyShoucangActivity.class));
			}
			break;
			//�˺Ź���
		case R.id.my_ll3:
			if(head == null || head.equals("") || head.length() == 0 || isLogin == 0){
				act.showSweetDialog("��ʾ", "�ף���û�е�¼�����ȵ�¼ร�", positive, null);
			}else{
				String name = act.getSharedPreferences("user", Context.MODE_PRIVATE).getString("name", "");
				String head = act.getSharedPreferences("user", Context.MODE_PRIVATE).getString("head", "");
				Intent intent = new Intent();
				intent.setClass(act, ZManagerActivity.class);
				intent.putExtra("name", name);//���û���ʹ��Intent����ȥ
				intent.putExtra("head", head);
				startActivity(intent);
			}
			
			break;
			//���պ�ѡ��ͷ��ĸ���
		case R.id.my_user_head:
			if(head == null || head.equals("") || head.length() == 0 || isLogin == 0){
				act.showSweetDialog("��ʾ", "�ף���û�е�¼�����ȵ�¼ร�", positive, null);
			}else{
				showDialog();
			}
			break;
		}
	}
	/**
	 * չʾ�Ի���
	 */
	private void showDialog() {
		AlertDialog.Builder dialog = new Builder(act);
		dialog.setIcon(android.R.drawable.ic_dialog_alert).setTitle("��ʾ").setItems(new String[]{"�����ϴ�","�������"},new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if(arg1 == 0){
					startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_CODE);
				}else if(arg1 == 1){
					int version = Build.VERSION.SDK_INT;//��ȡ�汾��
					if(version < 19){
						//�Ͱ汾
						Intent intent = new Intent(Intent.ACTION_PICK);
						intent.setData(Media.EXTERNAL_CONTENT_URI);
						startActivityForResult(intent, REQUEST_CODE1);
					}else{
						//�߰汾
						Intent intent = new Intent();
						intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
						intent.addCategory(Intent.CATEGORY_OPENABLE);
						intent.setType("image/*");
						startActivityForResult(intent, REQUEST_CODE1);
					}
				}
			}
		});
		dialog.setNegativeButton("ȡ��", null).create().show();
	}
	//---------------���պ����ѡ��---------------------------------------
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_CODE){
			if(data == null){
				return;
			}else{
				Bitmap bm = (Bitmap) data.getExtras().get("data");
				head_user.setImageBitmap(bm);
				saveBitmapHeader(bm);
			}
		}else if(requestCode == REQUEST_CODE1){
			if(data == null){
				return;
			}
			Uri uri = data.getData();
			Bitmap bit = null;
			if(uri == null){
				//�Ͱ汾
				Bundle bundle = data.getExtras();
				bit = (Bitmap) bundle.get("data");
			}else{
				//�߰汾
				try {
					bit = Media.getBitmap(act.getContentResolver(), uri);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					Log.e("yjc", "�ļ�û�ҵ���"+e.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
					Log.e("yjc", "��������쳣��"+e.getMessage());
				}
			}
			head_user.setImageBitmap(bit);
			saveBitmapHeader(bit);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	/**
	 * ����ͼƬ
	 */
	private void saveBitmapHeader(Bitmap bm) {
		OutputStream os = null;
		//���浽����
		File dir = new File("/mnt/sdcard/zhishang/head/");
		if(!dir.exists()){
			//�������򴴽�
			dir.mkdirs();
		}
		try {
			String path = Environment.getExternalStorageDirectory().getPath().toString();
			os = new FileOutputStream(path+"/zhishang/head/head.jpg");
			bm.compress(CompressFormat.JPEG, 100, os);//����ѹ��
			act.getSharedPreferences("user", Context.MODE_PRIVATE).edit().putString("head",bm.toString().trim());
			
			UserDao dao = new UserDao(act);
			String user_name = act.getSharedPreferences("user", Context.MODE_PRIVATE).getString("name", "");
			String user_pwd = act.getSharedPreferences("user", Context.MODE_PRIVATE).getString("pwd", "");
			
			UserBean user = new UserBean(user_name, user_pwd, 1, bm.toString().trim());
			dao.updateByMaxId(dao.getMaxId(), user);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Log.e("yjc", e.getMessage().toString()+"����ʧ��");
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
				}
			}
		}
		

	}
//----------------------------�Ի���ȷ�ϵ���Ӧ����---------------------------------------
	private OnSweetClickListener positive = new OnSweetClickListener() {
		
		@Override
		public void onClick(SweetAlertDialog sweetAlertDialog) {
			startActivity(new Intent(act, LoginActivity.class));
			act.finish();
		}
	};
	
}
