package com.huewu.pla.sample;

import java.util.Arrays;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.huewu.pla.lib.internal.PLA_AdapterView;
import com.huewu.pla.sample.extra.PullToRefreshSampleActivity;
import com.huewu.pla.smaple.R;

public class SampleActivity extends Activity {

	private class MySimpleAdapter extends ArrayAdapter<String> {

		public MySimpleAdapter(Context context, int layoutRes) {
			super(context, layoutRes, android.R.id.text1);
		}
	}

	private PLA_AdapterView<ListAdapter> mAdapterView = null;
	private MySimpleAdapter mAdapter = null;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_sample);
		//mAdapterView = (PLA_AdapterView<Adapter>) findViewById(R.id.list);
		mAdapterView = (PLA_AdapterView<ListAdapter>) findViewById(R.id.list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, 1001, 0, "Load More Contents");
		menu.add(Menu.NONE, 1002, 0, "Launch Pull-To-Refresh Activity");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch(item.getItemId()){
		case 1001:
		{
			int startCount = mAdapter.getCount();
			for( int i = 0; i < 100; ++i){
				//generate 100 random items.

				StringBuilder builder = new StringBuilder();
				builder.append("Hello!![");
				builder.append(startCount + i);
				builder.append("] ");

				char[] chars = new char[mRand.nextInt(100)];
				Arrays.fill(chars, '1');
				builder.append(chars);
				mAdapter.add(builder.toString());
			}
		}
		break;
		case 1002:
		{
			Intent intent = new Intent(this, PullToRefreshSampleActivity.class);
			startActivity(intent);
		}
		break;
		}
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		initAdapter();
		mAdapterView.setAdapter(mAdapter);
		//mAdapterView.setAdapter(mAdapter);
	}

	private Random mRand = new Random();
	private void initAdapter() {
		mAdapter = new MySimpleAdapter(this, R.layout.item_sample);

		for( int i = 0; i < 100; ++i){
			//generate 100 random items.

			StringBuilder builder = new StringBuilder();
			builder.append("Hello!![");
			builder.append(i);
			builder.append("] ");

			char[] chars = new char[mRand.nextInt(100)];
			Arrays.fill(chars, '1');
			builder.append(chars);
			mAdapter.add(builder.toString());
		}

	}

}//end of class
