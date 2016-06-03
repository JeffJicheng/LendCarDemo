package com.yjc.config;

import com.yjc.zhishanglend.R;
/**
 * 配置文件
 * @author yjc Jeff.Yao
 *
 */
public interface Config {
	
	String name = "12345";
	String pwd = "12345";
	
	String[] home_carName = {"奥迪","奔驰","宝马","法拉利","兰博基尼","北京现代","雷克萨斯","长城"};
	int[] home_carType={R.drawable.car_audi,R.drawable.car_benz,R.drawable.car_bmw,
							 R.drawable.car_ferrari,R.drawable.car_lamborghini,
							 R.drawable.car_xiandai,R.drawable.car_lexus,
							 R.drawable.car_changcheng};
	
	
	//奥迪
	int[] type_audi_img = {R.drawable.auti_a1,R.drawable.auti_a3,R.drawable.auti_a4,R.drawable.auti_a5,R.drawable.auti_a6,R.drawable.auti_a7,R.drawable.auti_a8};
	String[] type_audi_name={"奥迪A1","奥迪A3","奥迪A4","奥迪A5","奥迪A6","奥迪A7","奥迪A8"};
	
	//奔驰
	int[] type_bengchi_img={R.drawable.benz_c,R.drawable.benz_e,R.drawable.benz_gla,R.drawable.benz_glc,R.drawable.benz_glk};
	String[] type_bengchi_name={"奔驰C系","奔驰E系","奔驰GLA系","奔驰GLC系","奔驰GLK系"};
	
	//宝马
	int[] type_bmw_img={R.drawable.bmw_x1,R.drawable.bmw_x2,R.drawable.bmw_x3,R.drawable.bmw_x4,R.drawable.bmw_x5,R.drawable.bmw_x6,R.drawable.bmw_m3,R.drawable.bmw_m4,R.drawable.bmw_m6,};
	String[] type_bmw_name={"宝马X1","宝马X2","宝马X3","宝马X4","宝马X5","宝马X6","宝马M3","宝马M4","宝马M6"};
	
	//宝马X1
	int[] type_bmwx1_img={R.drawable.bmw_x1_1,R.drawable.bmw_x1_2,R.drawable.bmw_x1_3,R.drawable.bmw_x1_4,R.drawable.bmw_x1_5,R.drawable.bmw_x1_6,R.drawable.bmw_x1_7};
	String[] type_bmwx1_name={"宝马X1 2014款 sDriver18i 手动型","宝马X1 2015款 sDriver18i 时尚晋级版","宝马X1 2015款 sDriver18i 领先晋级版","宝马X1 2014款 sDriver18i 时尚型","宝马X1 2014款 sDriver18i 领先型","宝马X1 2014款 sDriver18i 运动设计套装","宝马X1 2014款 sDriver18i X设计套装"};
	
	
	//保时捷
	int[] type_porsche_img={R.drawable.porsche_911,R.drawable.porsche_918,R.drawable.porsche_boxster,R.drawable.porsche_cayman,R.drawable.porsche_ky,R.drawable.porsche_macan,R.drawable.porsche_panamera};
	String[] type_porsche_name={"保时捷911","保时捷918","保时捷boxster","保时捷cayman","保时捷ky","保时捷macan","保时捷panamera"};
	
	//法拉利
	int[] type_ferrari_img={R.drawable.ferrari_360,R.drawable.ferrari_458,R.drawable.ferrari_488,R.drawable.ferrari_575m,R.drawable.ferrari_599,R.drawable.ferrari_612,R.drawable.ferrari_f430,};
	String[] type_ferrari_name={"法拉利360","法拉利458","法拉利488","法拉利575","法拉利599","法拉利612","法拉利f430"};

	//兰博基尼
	int[] type_lamborghini_img={R.drawable.lamborghini_aventador,R.drawable.lamborghini_gallardo,R.drawable.lamborghini_huracan,R.drawable.lamborghini_murcielago,R.drawable.lamborghini_reventon};
	String[] type_lamborghini_name={"兰博基尼aventador","兰博基尼gallardo","兰博基尼huracan","兰博基尼murcielago","兰博基尼reventon"};
	
	//北京现代
	int[] type_xiandai_img={R.drawable.xiandai_mt,R.drawable.xiandai_ld,R.drawable.xiandai_rn,R.drawable.xiandai_ry,R.drawable.xiandai_yd};
	String[] type_xiandai_name={"北京现代MT","北京现代LD","北京现代RN","北京现代RY","北京现代YD",};
	
	//雷克萨斯
	int[] type_lexus_img={R.drawable.lexus_ct,R.drawable.lexus_es,R.drawable.lexus_gs,R.drawable.lexus_is,R.drawable.lexus_ls,R.drawable.lexus_nx};
	String[] type_lexus_name={"雷克萨斯CT","雷克萨斯ES","雷克萨斯GS","雷克萨斯IS","雷克萨斯LS","雷克萨斯NX"};
	
	//长城
	int[] type_changcheng_img={R.drawable.changcheng_c20r,R.drawable.changcheng_c30,R.drawable.changcheng_c50,R.drawable.changcheng_m2,R.drawable.changcheng_m4};
	String[] type_changcheng_name={"长城C20R","长城C30","长城C50","长城M2","长城M4"};
	
	//车子级别 
	String[] bmw_x1_car_jibie = {"紧凑型SUV"};//宝马x1结构
	//车子发动机
	String[] bmw_x1_car_fadongji={"2.0T 156马力 L4"};//宝马x1发动机
	//车子变速箱
	String[] bmw_x1_car_biansuxiang = {"6档手动"};//宝马x1变速箱
	//车子结构
	String[] bmw_x1_car_jiegou = {"SUV"};//宝马x1结构
	//车子的价格
	int[] bmw_x1_car_price = {250000,280000,290000,210000,310000,285000,256000};//宝马x1价格
}
