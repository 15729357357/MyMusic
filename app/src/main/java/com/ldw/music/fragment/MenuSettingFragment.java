/**
 * Copyright (c) www.longdw.com
 */
package com.ldw.music.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.ldw.music.R;
import com.ldw.music.activity.IConstants;
import com.ldw.music.activity.MenuSettingActivity;
import com.ldw.music.storage.SPStorage;


public class MenuSettingFragment extends Fragment implements OnClickListener, IConstants {
	
	private LinearLayout mAdviceLayout, mAboutLayout;
	private CheckedTextView mChangeSongTv, mAutoLyricTv, mFilterSizeTv, mFilterTimeTv;
	private SPStorage mSp;
	
	private ImageButton mBackBtn;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.menu_setting_fragment, container, false);
		
		mSp = new SPStorage(getActivity());
		
		initView(view);
		
		return view;
	}

	private void initView(View view) {
		mAboutLayout = (LinearLayout) view.findViewById(R.id.setting_about_layout);
		mAdviceLayout = (LinearLayout) view.findViewById(R.id.setting_advice_layout);
		mAboutLayout.setOnClickListener(this);
		mAdviceLayout.setOnClickListener(this);
		
		mBackBtn = (ImageButton) view.findViewById(R.id.backBtn);
		mBackBtn.setOnClickListener(this);
		
		mChangeSongTv = (CheckedTextView) view.findViewById(R.id.shake_change_song);
		mAutoLyricTv = (CheckedTextView) view.findViewById(R.id.auto_download_lyric);
		mFilterSizeTv = (CheckedTextView) view.findViewById(R.id.filter_size);
		mFilterTimeTv = (CheckedTextView) view.findViewById(R.id.filter_time);
		
		mChangeSongTv.setChecked(mSp.getShake());
		mAutoLyricTv.setChecked(mSp.getAutoLyric());
		mFilterSizeTv.setChecked(mSp.getFilterSize());
		mFilterTimeTv.setChecked(mSp.getFilterTime());
		
		mChangeSongTv.setOnClickListener(this);
		mAutoLyricTv.setOnClickListener(this);
		mFilterSizeTv.setOnClickListener(this);
		mFilterTimeTv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.setting_about_layout:
			Builder dialog = new AlertDialog.Builder(getActivity());
			dialog.setTitle("关于我")
				.setMessage("学习使用 一位性格开朗，喜欢与人交朋友，想了解更多查看\n樊宁\ngithub:http://github.com/")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						 
					}
				}).create().show();

			break;
		case R.id.setting_advice_layout:
			Builder dialog1 = new AlertDialog.Builder(getActivity());
			dialog1.setTitle("提意见")
				.setMessage("内容丰富不够，功能还有待提升，\n需要再接再厉，加油！")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						 
					}
				}).create().show();
			break;
		case R.id.shake_change_song:
			mChangeSongTv.toggle();
			mSp.saveShake(mChangeSongTv.isChecked());
			Intent intent = new Intent(BROADCAST_SHAKE);
			intent.putExtra(SHAKE_ON_OFF, mChangeSongTv.isChecked());
			getActivity().sendBroadcast(intent);
			break;
		case R.id.auto_download_lyric:
			mAutoLyricTv.toggle();
			mSp.saveAutoLyric(mAutoLyricTv.isChecked());
			break;
		case R.id.filter_size:
			mFilterSizeTv.toggle();
			mSp.saveFilterSize(mFilterSizeTv.isChecked());
			break;
		case R.id.filter_time:
			mFilterTimeTv.toggle();
			mSp.saveFilterTime(mFilterTimeTv.isChecked());
			break;
		case R.id.backBtn:
			((MenuSettingActivity)getActivity()).mViewPager.setCurrentItem(0, true);
			break;
		}
	}
}
