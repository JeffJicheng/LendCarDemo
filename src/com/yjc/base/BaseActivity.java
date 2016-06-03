package com.yjc.base;

import cn.pedant.SweetAlert.SweetAlertDialog;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.widget.Toast;

public class BaseActivity extends FragmentActivity {
	public void shortToast(String text) {
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
				.show();
	}

	public void centerToast(String text, int gravity, int duration) {
		Toast to = Toast.makeText(getApplicationContext(), text, duration);
		to.setGravity(gravity, 0, 0);
		to.show();
	}

	public void showSweetDialog(String title, String text,
			SweetAlertDialog.OnSweetClickListener positive,
			SweetAlertDialog.OnSweetClickListener cancel) {
		new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
				// �����ģʽ
				.setTitleText(title).setContentText(text).setConfirmText("ȷ��")
				.setCancelText("ȡ��").setConfirmClickListener(positive)
				.setCancelClickListener(cancel).show();
	}

	public void showSweetDialog(String title, String text) {
		SweetAlertDialog sw = new SweetAlertDialog(this,
				SweetAlertDialog.WARNING_TYPE);
		sw.setTitleText(title).setContentText(text).setConfirmText("ȷ��")
				.setCancelable(false);
		sw.show();
	}
	public void showSweetDialog(String title, String text,SweetAlertDialog.OnSweetClickListener positive) {
		SweetAlertDialog sw = new SweetAlertDialog(this,
				SweetAlertDialog.WARNING_TYPE);
		sw.setTitleText(title).setContentText(text).setConfirmText("ȷ��").setConfirmClickListener(positive)
				.setCancelable(false);
		sw.show();
	}
	public SweetAlertDialog showSweetDialogProgress(String text) {
		SweetAlertDialog pDialog = new SweetAlertDialog(this,
				SweetAlertDialog.PROGRESS_TYPE);
		pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
		pDialog.setTitleText(text);
		pDialog.setCancelable(false);
		pDialog.show();
		return pDialog;
	}

	public boolean judgeIsNull(String name, String pwd) {
		if (name == null || name.equals("")) {
			shortToast("�û�������Ϊ��");
			return false;
		}
		if (pwd == null || pwd.equals("")) {
			shortToast("���벻��Ϊ��");
			return false;
		}
		return true;
	}
}
