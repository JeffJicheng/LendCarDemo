package com.yjc.adapter;

import java.util.List;

import com.yjc.activity.WaitShoppingsActivity;
import com.yjc.bean.FinishBean;
import com.yjc.zhishanglend.R;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Œ“µƒ ’≤ÿListView   ≈‰∆˜
 * @author Administrator
 *
 */
public class WSAdapter extends BaseAdapter {
	private WaitShoppingsActivity act;
	private List<FinishBean> datas;
	
	public WSAdapter(WaitShoppingsActivity act, List<FinishBean> datas) {
		super();
		this.act = act;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int arg0) {
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
			arg1 = act.getLayoutInflater().inflate(R.layout.item_sxq_lv, null);
			vh = new ViewHolder();
			vh.img = (ImageView) arg1.findViewById(R.id.item_sxq_iv);
			vh.name = (TextView) arg1.findViewById(R.id.item_sxq_name);
			vh.price = (TextView) arg1.findViewById(R.id.item_sxq_price);
			vh.numbers = (TextView) arg1.findViewById(R.id.item_sxq_numbers);
			arg1.setTag(vh);
		}else{
			vh = (ViewHolder) arg1.getTag();
		}
		vh.img.setImageResource(datas.get(arg0).getImg());
		vh.name.setText(datas.get(arg0).getName());
		vh.price.setText(datas.get(arg0).getSingleMoney());
		vh.numbers.setText(datas.get(arg0).getSingleNumbers());
		return arg1;
	}
	static class ViewHolder{
		ImageView img;
		TextView name,price,numbers;
	}
	

}
