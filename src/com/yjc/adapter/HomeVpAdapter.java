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
 * ��ҳ�Ĺ��ViewPager������
 * @author yjc Jeff.Yao
 *
 */
public class HomeVpAdapter extends PagerAdapter {
	private List<View> pagers;
	

	public HomeVpAdapter(List<View> pagers) {
		super();
		this.pagers = pagers;
	}

	//���ݵĸ���
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pagers.size();
	}

	//�ж�arg0�Ƿ���ͨ��arg1�����ɵ�
	//arg1�ǲ�����Ҫ��ʾ��view
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	//�����pagerװ����vp
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		View v = pagers.get(position);
		container.addView(v);
		return v;
	}
	//��vpɾ��ĳ��pager
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(pagers.get(position));
	}
	
}
