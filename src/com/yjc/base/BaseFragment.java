package com.yjc.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;


public class BaseFragment extends Fragment {
	public View findviewbyid(int id){
		return getView().findViewById(id);
	}
	public void shortToast(Context context,String text){
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
}
