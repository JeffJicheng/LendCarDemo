package com.yjc.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yjc.activity.TypeActivity;
import com.yjc.bean.CarTypeBean;
import com.yjc.bean.CarTypeSonBean;
import com.yjc.zhishanglend.R;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 分类
 * @author yjc Jeff.Yao
 *
 */
public class TypeAdapter extends BaseExpandableListAdapter {
	private List<CarTypeBean> datas;
	private TypeActivity act;
	private List<List<CarTypeSonBean>> son_datas;
	
	public TypeAdapter(List<CarTypeBean> datas, TypeActivity act,
			List<List<CarTypeSonBean>> son_datas) {
		super();
		this.datas = datas;
		this.act = act;
		this.son_datas = son_datas;
	}
	
	
	
	//得到子item需要关联的数据  子的具体的内容
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 	son_datas.get(groupPosition).get(childPosition);
	}
	//得到子item的ID
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}
	/**
	 * 
	 * 获得子的数量
	 */
	@Override
	public int getChildrenCount(int arg0) {
		if(arg0 >= son_datas.size()){
			Log.i("yjc", "arg0="+arg0);
			Toast.makeText(act, "无数据", Toast.LENGTH_SHORT).show();
			return 0;
		}
		return son_datas.get(arg0).size();
	}
	 //获取当前父item的数据
	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return datas.get(groupPosition);
	}
	
	//获得父的数量
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 父的布局
	 */
	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		// TODO Auto-generated method stub
		ViewHolder vh = null;
		if(arg2 == null){
			arg2 = act.getLayoutInflater().inflate(R.layout.item_type,null);
			vh = new ViewHolder();
			vh.iv = (ImageView) arg2.findViewById(R.id.item_type_iv);
			vh.tv = (TextView) arg2.findViewById(R.id.item_type_tv);
			arg2.setTag(vh);
		}else{
			vh = (ViewHolder) arg2.getTag();
		}
		vh.iv.setImageResource(datas.get(arg0).getImg());
		vh.tv.setText(datas.get(arg0).getName());
		return arg2;
	}
	
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolderSon vhs = null;
		if(convertView == null){
			convertView = act.getLayoutInflater().inflate(R.layout.item_type_son,null);
			vhs = new ViewHolderSon();
			vhs.iv1 = (ImageView) convertView.findViewById(R.id.item_type_son_iv);
			vhs.tv1 = (TextView) convertView.findViewById(R.id.item_type_son_tv);
			convertView.setTag(vhs);
		}else{
			vhs = (ViewHolderSon) convertView.getTag();
		}
		vhs.iv1.setImageResource(son_datas.get(groupPosition).get(childPosition).getImg());
		vhs.tv1.setText(son_datas.get(groupPosition).get(childPosition).getIntroduction());
		return convertView;
	}
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 当选择子节点的时候，被调用
	 */
	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}
	/**
	 * 父的ViewHolder  缓存机制  做优化处理
	 * @author Administrator
	 *
	 */
	static class ViewHolder{
		ImageView iv;
		TextView tv;
	}
	/**
	 * 子类的ViewHolder
	 * @author Administrator
	 *
	 */
	static class ViewHolderSon{
		ImageView iv1;
		TextView tv1;
	}
}
