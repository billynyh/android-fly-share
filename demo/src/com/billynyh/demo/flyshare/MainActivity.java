package com.billynyh.demo.flyshare;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.content.Context;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.billynyh.flyshare.model.ActionItem;
import com.billynyh.flyshare.model.IntentActionItem;
import com.billynyh.flyshare.util.FlyShareUtil;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    ArrayList<ActionItem> mList;
    ItemAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        init();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


    private void init() {
        mList = new ArrayList<ActionItem>();

        ActionItem toastItem = new ToastItem("Item 1", getResources().getDrawable(R.drawable.ic_launcher));
        mList.add(toastItem);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        intent.setType("text/plain");
    
        PackageManager pm = getPackageManager();
        List<IntentActionItem> list = FlyShareUtil.fromIntentActivities(pm, intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (IntentActionItem item : list) {
            item.load(pm);
        }

        

        mList.addAll(list);


        mAdapter = new ItemAdapter();
        GridView gv = (GridView)findViewById(R.id.gridView);
        gv.setAdapter(mAdapter);
        gv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick (AdapterView parent, View view, int position, long id) {
                mList.get(position).doAction(getActivity());
            }
        });

        SlidingUpPanelLayout layout = (SlidingUpPanelLayout)findViewById(R.id.sliding_layout);
        layout.setDragView(findViewById(R.id.dragView));
    }

    private Activity getActivity() {
        return this;
    }

    private class ItemAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int pos, View convertView, ViewGroup container) {
			if (convertView == null) {
				convertView = new TextView(getActivity());
			}
            TextView tv = (TextView)convertView;
            ActionItem item = mList.get(pos);
            int p = getResources().getDimensionPixelOffset(R.dimen.item_padding);
            tv.setGravity(Gravity.CENTER);
            tv.setText(item.getLabel());
            tv.setPadding(p, p, p, p);
            tv.setCompoundDrawablesRelativeWithIntrinsicBounds(null, item.getIcon(), null, null);
            return tv;
		}
    }

    private class ToastItem extends ActionItem {
        private CharSequence title;
        private Drawable icon;

        public ToastItem(CharSequence title, Drawable icon) {
            this.title = title;
            this.icon = icon;
        }
        public CharSequence getLabel(){ return title; }
        public Drawable getIcon() { return icon; }

        public void doAction(Context context) {
            Toast.makeText(getActivity(), "Toast item " + title, Toast.LENGTH_LONG).show();
        }
    }

}
