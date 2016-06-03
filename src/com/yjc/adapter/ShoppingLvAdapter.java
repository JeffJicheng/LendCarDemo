package com.yjc.adapter;

import java.util.List;

import com.lidroid.xutils.exception.DbException;
import com.yjc.activity.HomeActivity;
import com.yjc.bean.ShoppingBean;
import com.yjc.bean.ShoppingXQBean;
import com.yjc.fragment.ShoppingFragment;
import com.yjc.util.SystemUtils;
import com.yjc.zhishanglend.R;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 购物ListView 的适配器
 * @author yjc Jeff.Yao
 *
 */
public class ShoppingLvAdapter extends BaseAdapter implements OnCheckedChangeListener {
	private HomeActivity act;
	private List<ShoppingBean> datas;
	private ShoppingBean shopping;
	
	private int sum;
	
	public ShoppingLvAdapter(HomeActivity act, List<ShoppingBean> datas) {
		super();
		this.act = act;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(datas == null || datas.size() == 0 || datas.equals("")){
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
		// TODO Auto-generated method stub
		ViewHolder vh = null;
		if(arg1 == null){
			arg1 = act.getLayoutInflater().inflate(R.layout.item_shopping_lv, null);
			vh = new ViewHolder();
			vh.checkBox = (CheckBox) arg1.findViewById(R.id.shopping_checkbox);
			vh.car_iv = (ImageView) arg1.findViewById(R.id.shopping_iv);
			vh.car_name = (TextView) arg1.findViewById(R.id.shopping_name);
			vh.car_price = (TextView) arg1.findViewById(R.id.shopping_price);
			vh.car_num = (TextView) arg1.findViewById(R.id.shopping_num);
			arg1.setTag(vh);
		}else{
			vh = (ViewHolder) arg1.getTag();
		}
		vh.checkBox.setTag(datas.get(arg0));
		vh.checkBox.setChecked(datas.get(arg0).isSelected());
		vh.checkBox.setOnCheckedChangeListener(this);
		vh.car_iv.setImageResource(datas.get(arg0).getImg());
		vh.car_name.setText(datas.get(arg0).getCarName());
		vh.car_price.setText(datas.get(arg0).getPrice()+"");
		vh.car_num.setText(datas.get(arg0).getNums()+"");
		return arg1;
	}
	class ViewHolder{
		CheckBox checkBox;
		ImageView car_iv;
		TextView car_name;
		TextView car_price;
		TextView car_num;
	}
	
	//-----------------------------------------------
	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		shopping = (ShoppingBean) arg0.getTag();
		if(arg1){
			sum += shopping.getPrice() * shopping.getNums();
			ShoppingFragment.getMoney().setText("合计： ￥"+sum);
			shopping.setIsSelectedNum(1);
			Log.i("yjc", "onCheckedChanged");
		}else{
			sum -= shopping.getPrice() * shopping.getNums();
			ShoppingFragment.getMoney().setText("合计： ￥"+sum);
			shopping.setIsSelectedNum(0);
		}  
		shopping.setSelected(arg1);
	}

}
