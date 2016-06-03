package com.yjc.adapter;

import java.util.List;

import com.yjc.activity.ShoppingXQActivity;
import com.yjc.bean.ShoppingXQBean;
import com.yjc.zhishanglend.R;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * ¶©µ¥ÏêÇéµÄAdapter
 * @author yjc Jeff.Yao
 *
 */
public class SXQAdapter extends BaseAdapter {
	private ShoppingXQActivity act;
	private List<ShoppingXQBean> datas;
	
	public SXQAdapter(ShoppingXQActivity act, List<ShoppingXQBean> datas) {
		super();
		this.act = act;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
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
		if(datas == null || datas.size() == 0|| datas.equals("")){
			return 0;
		}
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
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
		vh.price.setText("£¤"+datas.get(arg0).getMoney());
		vh.numbers.setText("X "+datas.get(arg0).getNumbers());
		return arg1;
	}
	class ViewHolder{
		ImageView img;
		TextView name;
		TextView price;
		TextView numbers;
	}

}
