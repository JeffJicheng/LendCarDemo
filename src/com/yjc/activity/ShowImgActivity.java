package com.yjc.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import cn.pedant.SweetAlert.SweetAlertDialog;

import com.yjc.app.MyApplication;
import com.yjc.base.BaseActivity;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;
/**
 * չʾ����ͼƬ
 * @author yjc Jeff.Yao
 *
 */
public class ShowImgActivity extends BaseActivity implements OnClickListener {
	private ShowImgActivity act;
	private ImageView show_img;
	private ImageView save_img;
	
	private SweetAlertDialog dialog;
	
	private boolean isShow = false;
	private int img;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		act = this;
		MyApplication.instance.getActs().add(act);
		super.onCreate(arg0);
		setContentView(R.layout.act_shoimg);
		img = act.getIntent().getIntExtra("img", 0);
		initViews();
		doViews();
	}
	/**
	 * ����
	 */
	private void doViews() {
		show_img.setOnClickListener(this);
		show_img.setImageResource(img);
		save_img.setOnClickListener(this);
	}
	/**
	 * �ؼ���ʼ��
	 */
	private void initViews() {
		show_img = (ImageView) findViewById(R.id.showimg_img);
		save_img = (ImageView) findViewById(R.id.showimg_save);
	}
	@Override
	protected void onDestroy() {
		MyApplication.instance.getActs().remove(act);
		super.onDestroy();
	}
	//-----------------------------------
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.showimg_img:
			isShow = !isShow;
			if(isShow){
				save_img.setVisibility(View.VISIBLE);
			}else{
				save_img.setVisibility(View.INVISIBLE);
			}
			break;

		case R.id.showimg_save:
			FileOutputStream fos = null;
			File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/zhishang/pic/");
			if(!dir.exists()){
				dir.mkdirs();
			}
			Bitmap bm = BitmapFactory.decodeResource(getResources(), img);
			try {
				fos = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"/zhishang/pic/System.currentTimeMillis()"+".png");
				dialog = act.showSweetDialogProgress("������...");
				bm.compress(CompressFormat.PNG, 100, fos);
				fos.flush();
				dialog.dismissWithAnimation();
				act.shortToast("����ɹ�");
				save_img.setVisibility(View.INVISIBLE);
				isShow = false;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				Log.e("yjc", "����ʧ��,�ļ�û�ҵ�"+e.getMessage().toString());
			} catch (IOException e) {
				e.printStackTrace();
				Log.e("yjc", "����ʧ��,д��IO�쳣"+e.getMessage().toString());
			}finally{
				if(fos!=null){
					try {
						fos.close();
					} catch (IOException e) {
					}
				}
			}
			break;
		}
	}
	/**
	 *�жϵ���������Ƿ���ͼƬ��,Ҳ����˵�Ƿ������״�
	 * PointF ��һ��javabean ������ʾ����ȵȣ���ϸ���Կ�Դ��
	 */
	public boolean isPicIn(PointF pf){
		//PointF ��һ��javabean ������ʾ����ȵȣ���ϸ���Կ�Դ��
		int top = show_img.getTop();//�õ�����
		int bottom = show_img.getBottom();//�õ��ײ�
		int left = show_img.getLeft();//�õ���߽�
		int right = show_img.getRight();//�õ��ұ߽�
		boolean bo = false;//�����ж��Ƿ���ͼƬ֮��
		if(pf.x > left && pf.x < right && pf.y > top && pf.y < bottom){
			bo = true;
		}
		return bo;
	}
	
	/**
	 * �����Ļ���ж�
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//�ж��Ƿ��� Ȼ���жϵ�����Ƿ���ͼƬ֮��
		PointF pf = new PointF(event.getX(), event.getY());
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			if(!isPicIn(pf)){
				ShowImgActivity.this.finish();
			}
			return true;
		}
		return super.onTouchEvent(event);
	}
}
