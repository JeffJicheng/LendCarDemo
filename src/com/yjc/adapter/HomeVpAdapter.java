package com.yjc.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mob.tools.gui.ViewPagerAdapter;
import com.yjc.activity.HomeActivity;
import com.yjc.zhishanglend.R;
/**
 * 首页的广告ViewPager适配器
 * @author yjc Jeff.Yao
 *
 */
public class HomeVpAdapter extends PagerAdapter {
	private List<View> pagers;
	

	public HomeVpAdapter(List<View> pagers) {
		super();
		this.pagers = pagers;
	}

	//数据的个数
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pagers.size();
	}

	//判断arg0是否是通过arg1来生成的
	//arg1是不是你要显示的view
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	//把你的pager装填入vp
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		View v = pagers.get(position);
		container.addView(v);
		return v;
	}
	//从vp删除某个pager
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(pagers.get(position));
	}
	
}
