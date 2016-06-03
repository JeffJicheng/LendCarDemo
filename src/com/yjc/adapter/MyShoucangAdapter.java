package com.yjc.adapter;

import java.util.List;

import com.yjc.activity.MyShoucangActivity;
import com.yjc.bean.MyShoucangBean;
import com.yjc.zhishanglend.R;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Œ“µƒ ’≤ÿListView  ≈‰∆˜
 * @author yjc Jeff.Yao
 *
 */
public class MyShoucangAdapter extends BaseAdapter {
	private MyShoucangActivity act;
	private List<MyShoucangBean> datas;
	
	public MyShoucangAdapter(MyShoucangActivity act, List<MyShoucangBean> datas) {
		super();
		this.act = act;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		if(datas == null || datas.size() == 0){
			return 0;
		}
		return datas.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return datas.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ViewHolder vh = null;
		if(arg1 == null){
			arg1 = act.getLayoutInflater().inflate(R.layout.item_myshoucang_lv, null);
			vh = new ViewHolder();
			vh.iv = (ImageView) arg1.findViewById(R.id.item_myshoucang_iv);
			vh.name = (TextView) arg1.findViewById(R.id.item_myshoucang_name);
			vh.price = (TextView) arg1.findViewById(R.id.item_myshoucang_price);
			arg1.setTag(vh);
		}else{
			vh = (ViewHolder) arg1.getTag();
		}
		vh.iv.setImageResource(datas.get(arg0).getImg());
		vh.name.setText(datas.get(arg0).getName());
		vh.price.setText(datas.get(arg0).getPrice());
		return arg1;
	}
	static class ViewHolder{
		ImageView iv;
		TextView name;
		TextView price;
	}
}
