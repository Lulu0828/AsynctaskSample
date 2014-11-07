package com.dome.asynctasksample;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

public class AsyncTastActivity extends Activity{

	private ListView listView;
	
	private List<String> urlList;
	private ArrayList<HashMap<String, Object>> itemList;
	
	private ImageAdapter listItemAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_item);
		
		listView = (ListView)findViewById(R.id.listview);

        urlList = new ArrayList<String>();
        urlList.add("http://www.baidu.com/img/baidu_sylogo1.gif");
        urlList.add("http://y2.ifengimg.com/2012/06/24/23063562.gif");
        urlList.add("http://himg2.huanqiu.com/statics/images/index/logo.png");
        
        itemList = new ArrayList<>();
        
        listItemAdapter = new ImageAdapter(this, itemList);
        listView.setAdapter(listItemAdapter);
        
        AsyncTask<List<String>, Integer, Hashtable<String, SoftReference<Drawable>>> task = new AsyncTask<List<String>, 
        		Integer, Hashtable<String,SoftReference<Drawable>>>() {

					@Override
					protected Hashtable<String, SoftReference<Drawable>> doInBackground(
							List<String>... params) {
						// TODO 自动生成的方法存根
						Hashtable<String, SoftReference<Drawable>> table = new Hashtable<String, SoftReference<Drawable>>();
						return null;
					}
        	
        };
	}

}
