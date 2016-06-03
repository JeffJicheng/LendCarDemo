package com.yjc.adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yjc.activity.ChankanActivity;
import com.yjc.bean.CarTypeSonBean;
import com.yjc.zhishanglend.R;
/**
 * 车型查看适配器
 * @author yjc Jeff.Yao
 *
 */
public class ChanKanAdapter extends BaseAdapter {
	private ChankanActivity act;
	private List<CarTypeSonBean> datas;
	
	public ChanKanAdapter(ChankanActivity act, List<CarTypeSonBean> datas) {
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
		ViewHolder vh = null;
		if(arg1 == null){
			arg1 = act.getLayoutInflater().inflate(R.layout.item_type_son, null);
			vh = new ViewHolder();
			vh.iv = (ImageView) arg1.findViewById(R.id.item_type_son_iv);
			vh.tv = (TextView) arg1.findViewById(R.id.item_type_son_tv);
			arg1.setTag(vh);
		}else{
			vh = (ViewHolder) arg1.getTag();
		}
		vh.iv.setImageResource(datas.get(arg0).getImg());
		vh.tv.setText(datas.get(arg0).getIntroduction());
		return arg1;
	}
	
	static class ViewHolder{
		ImageView iv;
		TextView tv;
	}
	
}
