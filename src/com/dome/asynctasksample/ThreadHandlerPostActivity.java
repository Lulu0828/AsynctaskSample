package com.dome.asynctasksample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

/*使用post方法将Runnable对象放到Handler的线程队列中，该Runnable的执行其实并未单独开启线程，而是仍然在当前Activity的UI线程中执行，
 * Handler只是调用了Runnable对象的run方法。*/
public class ThreadHandlerPostActivity extends Activity{

	private ListView listView;
	
	private List<String> urlList;
	private LinkedList<HashMap<String, Object>> itemList;
	
	private ImageAdapter listItemAdapter;
	
	private Handler handler = new Handler();
	private ExecutorService executorService = Executors.newFixedThreadPool(10);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		
		listView = (ListView)findViewById(R.id.listview);

        urlList = new ArrayList<String>();
        urlList.add("http://www.baidu.com/img/baidu_sylogo1.gif");
        urlList.add("http://y2.ifengimg.com/2012/06/24/23063562.gif");
        urlList.add("http://himg2.huanqiu.com/statics/images/index/logo.png");
        
        itemList = new LinkedList<>();
        
        listItemAdapter = new ImageAdapter(this, itemList);
        listView.setAdapter(listItemAdapter);
        
        for (final String urlString : urlList) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						URL url = new URL(urlString);
						Drawable drawable = Drawable.createFromStream(url.openStream(), "src");
						final HashMap<String, Object> table = new HashMap<>();
						table.put("itemImage", drawable);
						handler.post(new Runnable() {
							//运行发现table中有三条数据，但是图片并没有显示出来
							@Override
							public void run() {
								// TODO Auto-generated method stub
								itemList.add(table);
								listItemAdapter.notifyDataSetChanged();
							}
						});
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}

}
