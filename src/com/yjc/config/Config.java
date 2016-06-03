package com.yjc.config;

import com.yjc.zhishanglend.R;
/**
 * �����ļ�
 * @author yjc Jeff.Yao
 *
 */
public interface Config {
	
	String name = "12345";
	String pwd = "12345";
	
	String[] home_carName = {"�µ�","����","����","������","��������","�����ִ�","�׿���˹","����"};
	int[] home_carType={R.drawable.car_audi,R.drawable.car_benz,R.drawable.car_bmw,
							 R.drawable.car_ferrari,R.drawable.car_lamborghini,
							 R.drawable.car_xiandai,R.drawable.car_lexus,
							 R.drawable.car_changcheng};
	
	
	//�µ�
	int[] type_audi_img = {R.drawable.auti_a1,R.drawable.auti_a3,R.drawable.auti_a4,R.drawable.auti_a5,R.drawable.auti_a6,R.drawable.auti_a7,R.drawable.auti_a8};
	String[] type_audi_name={"�µ�A1","�µ�A3","�µ�A4","�µ�A5","�µ�A6","�µ�A7","�µ�A8"};
	
	//����
	int[] type_bengchi_img={R.drawable.benz_c,R.drawable.benz_e,R.drawable.benz_gla,R.drawable.benz_glc,R.drawable.benz_glk};
	String[] type_bengchi_name={"����Cϵ","����Eϵ","����GLAϵ","����GLCϵ","����GLKϵ"};
	
	//����
	int[] type_bmw_img={R.drawable.bmw_x1,R.drawable.bmw_x2,R.drawable.bmw_x3,R.drawable.bmw_x4,R.drawable.bmw_x5,R.drawable.bmw_x6,R.drawable.bmw_m3,R.drawable.bmw_m4,R.drawable.bmw_m6,};
	String[] type_bmw_name={"����X1","����X2","����X3","����X4","����X5","����X6","����M3","����M4","����M6"};
	
	//����X1
	int[] type_bmwx1_img={R.drawable.bmw_x1_1,R.drawable.bmw_x1_2,R.drawable.bmw_x1_3,R.drawable.bmw_x1_4,R.drawable.bmw_x1_5,R.drawable.bmw_x1_6,R.drawable.bmw_x1_7};
	String[] type_bmwx1_name={"����X1 2014�� sDriver18i �ֶ���","����X1 2015�� sDriver18i ʱ�н�����","����X1 2015�� sDriver18i ���Ƚ�����","����X1 2014�� sDriver18i ʱ����","����X1 2014�� sDriver18i ������","����X1 2014�� sDriver18i �˶������װ","����X1 2014�� sDriver18i X�����װ"};
	
	
	//��ʱ��
	int[] type_porsche_img={R.drawable.porsche_911,R.drawable.porsche_918,R.drawable.porsche_boxster,R.drawable.porsche_cayman,R.drawable.porsche_ky,R.drawable.porsche_macan,R.drawable.porsche_panamera};
	String[] type_porsche_name={"��ʱ��911","��ʱ��918","��ʱ��boxster","��ʱ��cayman","��ʱ��ky","��ʱ��macan","��ʱ��panamera"};
	
	//������
	int[] type_ferrari_img={R.drawable.ferrari_360,R.drawable.ferrari_458,R.drawable.ferrari_488,R.drawable.ferrari_575m,R.drawable.ferrari_599,R.drawable.ferrari_612,R.drawable.ferrari_f430,};
	String[] type_ferrari_name={"������360","������458","������488","������575","������599","������612","������f430"};

	//��������
	int[] type_lamborghini_img={R.drawable.lamborghini_aventador,R.drawable.lamborghini_gallardo,R.drawable.lamborghini_huracan,R.drawable.lamborghini_murcielago,R.drawable.lamborghini_reventon};
	String[] type_lamborghini_name={"��������aventador","��������gallardo","��������huracan","��������murcielago","��������reventon"};
	
	//�����ִ�
	int[] type_xiandai_img={R.drawable.xiandai_mt,R.drawable.xiandai_ld,R.drawable.xiandai_rn,R.drawable.xiandai_ry,R.drawable.xiandai_yd};
	String[] type_xiandai_name={"�����ִ�MT","�����ִ�LD","�����ִ�RN","�����ִ�RY","�����ִ�YD",};
	
	//�׿���˹
	int[] type_lexus_img={R.drawable.lexus_ct,R.drawable.lexus_es,R.drawable.lexus_gs,R.drawable.lexus_is,R.drawable.lexus_ls,R.drawable.lexus_nx};
	String[] type_lexus_name={"�׿���˹CT","�׿���˹ES","�׿���˹GS","�׿���˹IS","�׿���˹LS","�׿���˹NX"};
	
	//����
	int[] type_changcheng_img={R.drawable.changcheng_c20r,R.drawable.changcheng_c30,R.drawable.changcheng_c50,R.drawable.changcheng_m2,R.drawable.changcheng_m4};
	String[] type_changcheng_name={"����C20R","����C30","����C50","����M2","����M4"};
	
	//���Ӽ��� 
	String[] bmw_x1_car_jibie = {"������SUV"};//����x1�ṹ
	//���ӷ�����
	String[] bmw_x1_car_fadongji={"2.0T 156���� L4"};//����x1������
	//���ӱ�����
	String[] bmw_x1_car_biansuxiang = {"6���ֶ�"};//����x1������
	//���ӽṹ
	String[] bmw_x1_car_jiegou = {"SUV"};//����x1�ṹ
	//���ӵļ۸�
	int[] bmw_x1_car_price = {250000,280000,290000,210000,310000,285000,256000};//����x1�۸�
}
