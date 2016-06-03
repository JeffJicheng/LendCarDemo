package com.yjc.util;

import android.content.Context;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.yjc.zhishanglend.R;
/**
 *  xUtils系统工具类
 * @author yjc jeff.Yao
 *
 */
public class SystemUtils {
	public static BitmapUtils getBitmapUtils(Context context){
		BitmapUtils bu = new BitmapUtils(context);
		bu.configDefaultLoadFailedImage(R.drawable.user_image);
		bu.configDiskCacheEnabled(true);
		bu.configMemoryCacheEnabled(true);
		return bu;
	}
	public static DbUtils getDbUtils(Context context){
		DbUtils db = DbUtils.create(context);
		return db;
	}
}
