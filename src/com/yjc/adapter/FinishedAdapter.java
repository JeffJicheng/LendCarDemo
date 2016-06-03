package com.yjc.adapter;

import java.util.List;

import com.yjc.activity.MyDingdanActivity;
import com.yjc.bean.FinishBean;
import com.yjc.zhishanglend.R;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * 订单号完成 ListView的适配器
 * @author yjc Jeff.Yao
 *
 */
public class FinishedAdapter extends BaseAdapter {
	private MyDingdanActivity act;
	private List<FinishBean> datas;
	
	public FinishedAdapter(MyDingdanActivity act, List<FinishBean> datas) {
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
			arg1 = act.getLayoutInflater().inflate(R.layout.item_finishdingdan_lv, null);
			vh = new ViewHolder();
			vh.dingdanNumber = (TextView) arg1.findViewById(R.id.item_finishdingdan_dingdan);
			vh.money = (TextView) arg1.findViewById(R.id.item_finishdingdan_money);
			vh.time = (TextView) arg1.findViewById(R.id.item_finishdingdan_time);
			vh.zhuangtai = (TextView) arg1.findViewById(R.id.item_finishdingdan_zhuangtai);
			arg1.setTag(vh);
		}else{
			vh = (ViewHolder) arg1.getTag();
		}
		vh.dingdanNumber.setText(datas.get(arg0).getDingdanNumber());
		vh.money.setText("￥"+datas.get(arg0).getMoney());
		vh.time.setText(datas.get(arg0).getTime());
		vh.zhuangtai.setText(datas.get(arg0).getZhuangtai());
		return arg1;
	}
	static class ViewHolder{
		TextView dingdanNumber;
		TextView money;
		TextView time;
		TextView zhuangtai;
	}

}
