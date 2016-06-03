package com.yjc.adapter;

import java.util.List;

import com.yjc.activity.HomeActivity;
import com.yjc.bean.CarBrandBean;
import com.yjc.bean.CarTypeBean;
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
 * 类型品牌适配器
 * @author yjc Jeff.Yao
 *
 */
public class TypeBrandAdapter extends BaseExpandableListAdapter {
	private HomeActivity act;
	private List<CarBrandBean> datas;
	private List<List<CarTypeBean>> son_datas;
	
	public TypeBrandAdapter(HomeActivity act, List<CarBrandBean> datas,
			List<List<CarTypeBean>> son_datas) {
		super();
		this.act = act;
		this.datas = datas;
		this.son_datas = son_datas;
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return son_datas.get(arg0).get(arg1);
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return arg1;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
			ViewGroup arg4) {
		ViewSonHolder vhs = null;
		if(arg3 == null){
			arg3 = act.getLayoutInflater().inflate(R.layout.item_type_son, null);
			vhs = new ViewSonHolder();
			vhs.iv = (ImageView) arg3.findViewById(R.id.item_type_son_iv);
			vhs.tv = (TextView) arg3.findViewById(R.id.item_type_son_tv);
			arg3.setTag(vhs);
		}else{
			vhs = (ViewSonHolder) arg3.getTag();
		}
		vhs.iv.setImageResource(son_datas.get(arg0).get(arg1).getImg());
		vhs.tv.setText(son_datas.get(arg0).get(arg1).getName());
		return arg3;
	}

	@Override
	public int getChildrenCount(int arg0) {
		if(arg0 >= son_datas.size()){
			Log.i("yjc", "arg0="+arg0);
			Toast.makeText(act, "无数据", Toast.LENGTH_SHORT).show();
			return 0;
		}
		return son_datas.get(arg0).size();
	}

	@Override
	public Object getGroup(int arg0) {
		return datas.get(arg0);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		if(datas == null || datas.size() == 0 || datas.equals("")){
			return 0;
		}
		return datas.size();
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		ViewHolder vh = null;
		if(arg2 == null){
			arg2 = (ViewGroup) act.getLayoutInflater().inflate(R.layout.item_type, null);
			vh = new ViewHolder();
			vh.iv = (ImageView) arg2.findViewById(R.id.item_type_iv);
			vh.tv = (TextView) arg2.findViewById(R.id.item_type_tv);
			arg2.setTag(vh);
		}else{
			vh = (ViewHolder) arg2.getTag();
		}
		vh.iv.setImageResource(datas.get(arg0).getImg());
		vh.tv.setText(datas.get(arg0).getCarsName());
		return arg2;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}
	/**
	 * 父布局
	 * @author Administrator
	 *
	 */
	static class ViewHolder{
		ImageView iv;
		TextView tv;
	}
	/**
	 * 子布局
	 * @author Administrator
	 *
	 */
	static class ViewSonHolder{
		ImageView iv;
		TextView tv;
	}
	
	
}
