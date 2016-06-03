package com.yjc.db;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.lidroid.xutils.exception.DbException;
import com.yjc.bean.CarTypeBean;
import com.yjc.bean.CarTypeSonBean;
import com.yjc.bean.CarXiangxiBean;
import com.yjc.bean.UserBean;
import com.yjc.config.Config;
import com.yjc.dao.UserDao;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;

public class TotalDbConnection {
	public TotalDbConnection(Context context){
		//ȡbooleanֵ Ϊ��ʹdb����һ��  
		boolean isOnce = context.getSharedPreferences("isOnce", Context.MODE_PRIVATE).getBoolean("isOnce", true);
		
		if(isOnce){
			
			UserDao dao = new UserDao(context);
			UserBean user = new UserBean(Config.name, Config.pwd, 0, String.valueOf(R.drawable.user_image));
			dao.insert(user);

			for (int i = 0; i < Config.type_audi_img.length; i++) {
				CarTypeBean bean = new CarTypeBean(0, Config.type_audi_img[i], "�µ�", Config.type_audi_name[i]);
				try {
					SystemUtils.getDbUtils(context).save(bean);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = 0; i < Config.type_bengchi_img.length; i++) {
				CarTypeBean bean = new CarTypeBean(1, Config.type_bengchi_img[i], "����", Config.type_bengchi_name[i]);
				try {
					SystemUtils.getDbUtils(context).save(bean);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = 0; i < Config.type_bmw_img.length; i++) {
				CarTypeBean bean = new CarTypeBean(2, Config.type_bmw_img[i], "����", Config.type_bmw_name[i]);
				try {
					SystemUtils.getDbUtils(context).save(bean);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = 0; i < Config.type_ferrari_img.length; i++) {
				CarTypeBean bean = new CarTypeBean(3, Config.type_ferrari_img[i], "������", Config.type_ferrari_name[i]);
				try {
					SystemUtils.getDbUtils(context).save(bean);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = 0; i < Config.type_lamborghini_img.length; i++) {
				CarTypeBean bean = new CarTypeBean(4, Config.type_lamborghini_img[i], "��������", Config.type_lamborghini_name[i]);
				try {
					SystemUtils.getDbUtils(context).save(bean);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = 0; i < Config.type_xiandai_img.length; i++) {
				CarTypeBean bean = new CarTypeBean(5, Config.type_xiandai_img[i], "�����ִ�", Config.type_xiandai_name[i]);
				try {
					SystemUtils.getDbUtils(context).save(bean);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = 0; i < Config.type_lexus_img.length; i++) {
				CarTypeBean bean = new CarTypeBean(6, Config.type_lexus_img[i], "�׿���˹", Config.type_lexus_name[i]);
				try {
					SystemUtils.getDbUtils(context).save(bean);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = 0; i < Config.type_changcheng_img.length; i++) {
				CarTypeBean bean = new CarTypeBean(7, Config.type_changcheng_img[i], "����", Config.type_changcheng_name[i]);
				try {
					SystemUtils.getDbUtils(context).save(bean);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//-----------------���ӵ������ʹ洢------------------------------
			
			//�洢���������
			for (int i = 0; i < Config.type_bmwx1_img.length; i++) {
				CarTypeSonBean bean = new CarTypeSonBean(2, "����X1",Config.type_bmwx1_name[i], Config.type_bmwx1_img[i]);
				try {
					SystemUtils.getDbUtils(context).save(bean);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//�洢�����������ϸ��Ϣ
			for (int i = 0; i < Config.type_bmwx1_name.length; i++) {
				CarXiangxiBean bean = new CarXiangxiBean(2, "����X1", Config.type_bmwx1_name[i], Config.type_bmwx1_img[i], Config.bmw_x1_car_jibie[0], Config.bmw_x1_car_fadongji[0], Config.bmw_x1_car_biansuxiang[0], Config.bmw_x1_car_jiegou[0], Config.bmw_x1_car_price[i]);
				try {
					SystemUtils.getDbUtils(context).save(bean);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
			context.getSharedPreferences("isOnce", Context.MODE_PRIVATE).edit().putBoolean("isOnce", false).commit();
		}
		
	}
}
