package com.manuelpeinado.fadingactionbar.demo;

import java.util.zip.Inflater;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class NavigationDrawerActivity extends Activity implements

AdapterView.OnItemClickListener {

	private DrawerLayout mDrawerLayout;
	// private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	 private CharSequence mTitle;
	private String[] mCityNames;
	private int[] mCityImages;
	private LinearLayout left_drawer;
	private ListView lv_pos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigation_drawer);
		left_drawer = (LinearLayout) findViewById(R.id.left_drawer);// 左侧控件
		lv_pos = (ListView) findViewById(R.id.lv_pos);
		 mTitle = mDrawerTitle = getTitle();//这里获取---没搞懂，哪位大神看到了 麻烦回下贴
		mCityNames = getResources().getStringArray(R.array.drawer_items);// 城市数据，从XML文件中获取到的数组内容
		TypedArray typedArray = getResources().obtainTypedArray(
				R.array.city_images);
		mCityImages = new int[typedArray.length()];
		for (int i = 0; i < typedArray.length(); ++i) {
			mCityImages[i] = typedArray.getResourceId(i, 0);
		}
		typedArray.recycle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		// mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// mDrawerList.setAdapter(new ArrayAdapter<String>(this,
		// R.layout.drawer_list_item, mCityNames));
		lv_pos.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mCityNames));
		lv_pos.setOnItemClickListener(this);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		if (savedInstanceState == null) {// 当内容为空的时候默认启动的页面
			selectItem(0);
		}
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return mDrawerToggle.onOptionsItemSelected(item)
				|| super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		selectItem(position);
	}

	private void selectItem(int position) {// 选中
		Fragment fragment = new SampleFragment();
		Bundle args = new Bundle();
		args.putInt(SampleFragment.ARG_IMAGE_RES, mCityImages[position]);
		args.putInt(SampleFragment.ARG_ACTION_BG_RES, R.drawable.ab_background);
		fragment.setArguments(args);
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		lv_pos.setItemChecked(position, true);// Listview被选中时候设置的背景色
		setTitle(mCityNames[position]);
		mDrawerLayout.closeDrawer(left_drawer);
	}

	

	@Override
	public void setTitle(CharSequence title) {
		 mTitle = title;
		getActionBar().setTitle(mTitle);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		SubMenu subMenu = menu.addSubMenu("action item");
		subMenu.add(0, 1, 0, "柠檬-1:").setIcon(R.drawable.ic_location_map);;
		subMenu.add(0, 2, 0, "柠檬-2").setIcon(R.drawable.ic_location_map);
		subMenu.add(0, 3, 0, "柠檬-3").setIcon(R.drawable.ic_location_map);
		MenuItem menuItem = subMenu.getItem();
		menuItem.setIcon(R.drawable.ic_location_map);
		menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return super.onCreateOptionsMenu(menu);
	}
	
}
