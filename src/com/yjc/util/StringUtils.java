package com.yjc.util;

import java.text.SimpleDateFormat;
/**
 *  String×Ö·û´®¹¤¾ßÀà 
 * @author yjc jeff.Yao
 *
 */
public class StringUtils {
	public static String getTime(long time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(time);
	}
}
