package com.yjc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yjc.bean.UserBean;
import com.yjc.db.DbHelper;
//_id user_name user_pwd head isLogin
/**
 * UserDao 
 * @author yjc Jeff.Yao
 *
 */
public class UserDao {
	private DbHelper db;
	private SQLiteDatabase sd;
	
	public UserDao(Context context) {
		db = new DbHelper(context, 1);
	}
	/**
	 * 插入
	 * @param user
	 */
	public void insert(UserBean user){
		sd = db.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("_id", getMaxId()+1);
		values.put("user_name", user.getUser_name());
		values.put("user_pwd", user.getUser_pwd());
		values.put("head", user.getHead());
		values.put("isLogin", user.isLogin());
		sd.insert("T_user", null, values);
		sd.close();
	}
	/**
	 * 删除
	 * @param _id
	 */
	public void delete(int _id){
		sd = db.getWritableDatabase();
		sd.delete("T_user", "_id=?",new String[]{_id+""});
		sd.close();
	}
	/**
	 * 修改
	 */
	public void update(int _id,UserBean user){
		sd = db.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("_id", user.get_id());
		values.put("user_name", user.getUser_name());
		values.put("user_pwd", user.getUser_pwd());
		values.put("head", user.getHead());
		values.put("isLogin", user.isLogin());
		sd.update("T_user", values, "_id=?", new String[]{_id+""});
		sd.close();
	}
	/**
	 * 修改 以_id为标准修改
	 */
	public void updateByMaxId(int _id,UserBean user){
		sd = db.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("_id", getMaxId());
		values.put("user_name", user.getUser_name());
		values.put("user_pwd", user.getUser_pwd());
		values.put("head", user.getHead());
		values.put("isLogin", user.isLogin());
		sd.update("T_user", values, "_id=?", new String[]{_id+""});
		sd.close();
	}
	
	
	/**
	 * 根据用户名和密码查询
	 */
	public UserBean findNameAndPwd(String name,String pwd){
		UserBean user = null;
		sd = db.getReadableDatabase();
		Cursor cursor = sd.rawQuery("select * from T_user where user_name = ? and user_pwd = ?",new String[]{name,pwd});
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("_id"));
			String userName = cursor.getString(cursor.getColumnIndex("user_name"));
			String userPwd = cursor.getString(cursor.getColumnIndex("user_pwd"));
			String head = cursor.getString(cursor.getColumnIndex("head"));
			int isLogin = cursor.getInt(cursor.getColumnIndex("isLogin"));
			user = new UserBean(id, userName, userPwd, isLogin, head);
		}
		sd.close();
		return user; 
	}
	/**
	 * 根据用户名和密码查询是否匹配
	 */
	public boolean findNameAndPwdIsOk(String name,String pwd){
		UserBean user = null;
		sd = db.getReadableDatabase();
		Cursor cursor = sd.rawQuery("select * from T_user where user_name = ? and user_pwd = ?",new String[]{name,pwd});
		while (cursor.moveToNext()) {
			String userName = cursor.getString(cursor.getColumnIndex("user_name"));
			String userPwd = cursor.getString(cursor.getColumnIndex("user_pwd"));
			if(userName.equals(name)&&userPwd.equals(pwd)){
				return true;//匹配
			}
		}
		sd.close();
		return false;//不匹配
	}
	/**
	 * 查询最大的id
	 * @return
	 */
	public int getMaxId(){
		sd = db.getReadableDatabase();
		Cursor c = sd.rawQuery("select max(_id) from T_user", null);
		if(c.moveToNext()){
			return c.getInt(0);
		}else{
			return 0;
		}
	}
	/**
	 * 查找所有
	 * @return
	 */
	public UserBean findAll(){
		sd = db.getReadableDatabase();
		Cursor cursor = sd.rawQuery("select * from T_user", null);
		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		String userName = cursor.getString(cursor.getColumnIndex("user_name"));
		String userPwd = cursor.getString(cursor.getColumnIndex("user_pwd"));
		String head = cursor.getString(cursor.getColumnIndex("head"));
		int isLogin = cursor.getInt(cursor.getColumnIndex("isLogin"));
		return new UserBean(userName, userPwd, isLogin, head);
	}
	
	
}
