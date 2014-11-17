package com.dome.asynctasksample;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

/*Handler为Android提供了一种异步消息处理机制，它包含两个队列，一个是线程列队，另一个是消息列队。使用post方法将线 程对象添加到线程队列中，
 * 使用sendMessage(Message message)将消息放入消息队列中。当向消息队列中发送消息后就立 即返回，而从消息队列中读取消息对象时会阻塞，
 * 继而回调Handler中public void handleMessage(Message msg)方法。因此 在创建Handler时应该使用匿名内部类重写该方法。
 * 如果想要这个流程一直执行的话，可以再run方法内部执行postDelay或者 post方法，再将该线程对象添加到消息队列中重复执行。
 * 想要停止线程，调用Handler对象的removeCallbacks(Runnable r)从 线程队列中移除线程对象，使线程停止执行。
 */
public class ThreadHandlerActivity extends Activity{

	private ListView listView;
	
	private List<String> urlList;
	private ArrayList<HashMap<String, Object>> itemList;
	
	private ImageAdapter listItemAdapter;
	
	private Handler handler;
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
        
        itemList = new ArrayList<>();
        
        listItemAdapter = new ImageAdapter(this, itemList);
        listView.setAdapter(listItemAdapter);
        
        handler = new Handler(){

			@SuppressWarnings("unchecked")
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				HashMap<String, Object> map = (HashMap<String, Object>)msg.obj;
				itemList.add(map);
				listItemAdapter.notifyDataSetChanged();
			}
        	
        };
        
        for (final String urlString : urlList) {
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						URL url = new URL(urlString);
						Drawable drawable = Drawable.createFromStream(url.openStream(), "src");
						HashMap<String, Object> table = new HashMap<>();
						table.put("ItemImage", drawable);
						Message msg = new Message();
						msg.obj = table;
						msg.setTarget(handler);
						handler.sendMessage(msg);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}

}
